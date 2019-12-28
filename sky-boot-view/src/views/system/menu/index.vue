<template>
<div>
    <add :treeData="menuData" :addChildDialogVisible="btnOperation.addDialogVisible" @closeAddDialog="closeAddDialog" @reloadParentData="reloadParentData"/>
    <edit :treeData="menuData" :editChildDialogVisible="btnOperation.editDialogVisible" :editObj="editObj" @closeEditDialog="closeEditDialog" @reloadParentData="reloadParentData"></edit>
    <div class="page-header">
        <div class="page-header-search">
            <el-form :inline="true" :model="search" ref="searchForm" @submit.native.prevent>
                <el-form-item prop="menuName">
                    <el-input v-model="search.menuName" placeholder="菜单名称" size="small" @keydown.enter.native="query"/>
                </el-form-item>
            </el-form>
        </div>
        <div class="page-header-btn">
            <el-button type="primary" round size="small" @click="add" v-if="$skyPermission.hasPermission('menu_mgr_add')">添 加</el-button>
            <el-button type="primary" round size="small" @click="query">检 索</el-button>
            <el-button type="success" round size="small" @click="reset">重 置</el-button>
        </div>
    </div>
    <div class="page-main">
        <el-table :data="menuData" size="small" row-key="id"  :default-expand-all="true"  :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
            <el-table-column prop="menuName" label="菜单名称"/>
            <el-table-column prop="menuCode" label="菜单Code"/>
            <el-table-column prop="url" label="url"/>
            <el-table-column prop="menuOrBtn" label="类型">
                <template slot-scope="scope">
                  {{scope.row.menuOrBtn?'按钮':'菜单'}}
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button
                            type="primary"
                            size="mini"
                            @click="edit(scope.$index, scope.row)" v-if="$skyPermission.hasPermission('menu_mgr_edit')">编辑</el-button>
                    <el-button
                            size="mini"
                            type="danger"
                            @click="del(scope.$index, scope.row)" v-if="$skyPermission.hasPermission('menu_mgr_del')">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</div>
</template>

<script>
    import {getData,del as delMenu} from 'api/menu'
    import add from './add'
    import edit from './edit'
    export default {
        name: "index",
        components:{
            add,
            edit
        },
        data(){
            return {
                menuData:[],
                editObj:null,
                search:{
                    menuName:'',
                },
                btnOperation:{
                    addDialogVisible:false,
                    editDialogVisible:false,
                }
            }
        },
        methods:{
            reset(){
                this.$refs.searchForm.resetFields()
                this.loadTableData()
            },
            query(){
                this.loadTableData()
            },
            reloadParentData(){
                this.loadTableData()
            },
            loadTableData(){
                getData(this.search).then(res => {
                    if (res.data.code === 20000) {
                        this.menuData = res.data.data.rows
                    }
                })
            },
            closeAddDialog(){
                this.btnOperation.addDialogVisible = false
            },
            closeEditDialog(){
                this.btnOperation.editDialogVisible = false
            },
            add(){
                this.btnOperation.addDialogVisible = true
            },
            edit(index,row){
                this.btnOperation.editDialogVisible = true
                this.editObj = row
            },
            del(index,row){
                this.$confirm('是否删除该角色?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    delMenu(row.id).then(res => {
                        if(res.data.success){
                            this.$message.success(res.data.message)
                            this.loadTableData()
                        }
                    })
                }).catch(() => {

                });
            },
        },
        created() {
            this.loadTableData()
        }
    }
</script>

<style lang="less" scoped>
</style>
