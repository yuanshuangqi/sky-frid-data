package com.ylj.sky.boot.manager.model.menu.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ylj.sky.boot.common.constants.ResultCodeEnum;
import com.ylj.sky.boot.common.vo.R;
import com.ylj.sky.boot.manager.factory.PageFactory;
import com.ylj.sky.boot.manager.model.menu.entity.Menu;
import com.ylj.sky.boot.manager.model.menu.entity.QMenu;
import com.ylj.sky.boot.manager.model.menu.query.MenuDynamicQueryFactory;
import com.ylj.sky.boot.manager.model.menu.repository.MenuRepository;
import com.ylj.sky.boot.manager.model.menu.service.IMenuService;
import com.ylj.sky.boot.manager.model.menu.vo.MenuSearch;
import com.ylj.sky.boot.manager.model.role.entity.Role;
import com.ylj.sky.boot.manager.util.menu.MenuUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements IMenuService {
    @Resource
    private MenuRepository menuRepository;
    @Resource
    private JPAQueryFactory queryFactory;


    @Override
    public R addMenu(Menu menu) {
        menuRepository.save(menu);
        return R.setResult(ResultCodeEnum.ADD_SUCCESS);
    }

    @Override
    public R delMenuById(Long id) {
        Optional<Menu> menuOptional = menuRepository.findById(id);
        if(menuOptional.isPresent()){
            List<Role> roles = menuOptional.get().getRoles();
            if(CollUtil.isNotEmpty(roles)){
              roles.forEach(role -> role.getMenus().remove(menuOptional.get()));
            }
            List<Menu> children = menuRepository.findByParentIdEquals(id);
            if(CollUtil.isNotEmpty(children)){
                for (Menu child : children) {
                    List<Role> childRoles = child.getRoles();
                    if(CollUtil.isNotEmpty(childRoles)){
                        childRoles.forEach(role -> role.getMenus().remove(child));
                    }
                }
                menuRepository.deleteByParentIdEquals(id);
            }
            menuRepository.delete(menuOptional.get());
        }
        return R.setResult(ResultCodeEnum.DEL_SUCCESS);
    }

    @Override
    public R editMenu(Menu menu) {
        //父级菜单不能是自身
        if(menu.getId().equals(menu.getParentId())){
            return R.setResult(ResultCodeEnum.MENU_NO_SELF);
        }
        List<Menu> children = menuRepository.findByParentIdEquals(menu.getId());
        if(CollUtil.isNotEmpty(children)){
            //父级菜单不能是子菜单
            if(children.stream().anyMatch(e->e.getId().equals(menu.getParentId()))){
                return R.setResult(ResultCodeEnum.MENU_NO_CHILDREN);
            }
        }
        Optional<Menu> menuOptional = menuRepository.findById(menu.getId());
        if(menuOptional.isPresent()){
            menu.setRoles(menuOptional.get().getRoles());
            menu.setGmtCreate(menuOptional.get().getGmtCreate());
        }
        menuRepository.save(menu);
        return R.setResult(ResultCodeEnum.EDIT_SUCCESS);
    }

    @Override
    public R getMenuData(MenuSearch menuSearch) {
        QMenu qMenu = QMenu.menu;
        QueryResults<Menu> menuQueryResults = queryFactory.selectFrom(qMenu).where(MenuDynamicQueryFactory.createDynamicQuery(menuSearch)).fetchResults();
        //获取所有菜单
        List<Menu> allMenus = menuQueryResults.getResults();
        //获取所有父级菜单
        List<Menu> parentMenus = allMenus.stream().filter(e -> e.getParentId() == 0L).collect(Collectors.toList());
        if(CollUtil.isNotEmpty(parentMenus)){
            //递归封装子角色
            for (Menu menu : parentMenus) {
                //此方法为加载所有菜单和按钮权限，故需要加载相应的按钮权限
                menu.setChildren(MenuUtil.getChildrenWithBtn(menu,allMenus));
            }
            return R.ok().data(PageFactory.createPageMap(parentMenus));
        }else {
            return R.ok().data(PageFactory.createPageMap(allMenus));
        }

    }
    @Override
    public R getList() {
        return R.ok().data(menuRepository.findAll());
    }
}
