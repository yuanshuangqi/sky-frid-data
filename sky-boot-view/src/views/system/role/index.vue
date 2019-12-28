<template>
<div>
    <add :treeData="roleData" :addChildDialogVisible="btnOperation.addDialogVisible" @closeAddDialog="closeAddDialog" @reloadParentData="reloadParentData"/>
    <edit :treeData="roleData" :editChildDialogVisible="btnOperation.editDialogVisible" :editObj="editObj" @closeEditDialog="closeEditDialog" @reloadParentData="reloadParentData"></edit>
    <menu-assign :menuAssignChildDialogVisible="btnOperation.menuAssignDialogVisible"
                 @closeMenuAssignDialog="closeMenuAssignDialog"
                 :menuAssignRow="menuAssignRow" :menuTreeData="menuTreeData"
                 @reloadParentData="reloadParentData"/>
    <div class="page-header">
        <div class="page-header-search">
            <el-form :inline="true" :model="search" ref="searchForm" @submit.native.prevent>
                <el-form-item prop="roleName">
                    <el-input v-model="search.roleName" placeholder="角色名称" size="small"  @keydown.enter.native="query"/>
                </el-form-item>
            </el-form>
        </div>
        <div class="page-header-btn">
            <el-button type="primary" round size="small" @click="add" v-if="$skyPermission.hasPermission('role_mgr_add')">添 加</el-button>
            <el-button type="primary" round size="small" @click="query">检 索</el-button>
            <el-button type="success" round size="small" @click="reset">重 置</el-button>
        </div>
    </div>
    <div class="page-main">
        <el-table :data="roleData" size="small" row-key="id"  :default-expand-all="true"  :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
            <el-table-column prop="roleName" label="角色名称"/>
            <el-table-column prop="roleCode" label="角色Code"/>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button
                            type="primary"
                            size="mini"
                            @click="edit(scope.$index, scope.row)" v-if="$skyPermission.hasPermission('role_mgr_edit')">编辑</el-button>
                    <el-button
                            type="primary"
                            size="mini"
                            @click="menuAssign(scope.$index, scope.row)" v-if="$skyPermission.hasPermission('role_mgr_menu_assign')">权限分配
                    </el-button>
                    <el-button
                            size="mini"
                            type="danger"
                            @click="del(scope.$index, scope.row)" v-if="$skyPermission.hasPermission('role_mgr_del')">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</div>
</template>

<script>
    import {getRoleData,del as delRole} from 'api/role'
    import {getData} from 'api/menu'
    import add from './add'
    import edit from './edit'
    import menuAssign from './menuAssign'
    export default {
        name: "index",
        components:{
            add,
            edit,
            menuAssign
        },
        data(){
            return {
                roleData:[],
                menuTreeData:[],
                editObj:null,
                menuAssignRow:null,
                search:{
                    roleName:'',
                },
                btnOperation:{
                    addDialogVisible:false,
                    editDialogVisible:false,
                    menuAssignDialogVisible:false
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
                getRoleData(this.search).then(res => {
                    if (res.data.code === 20000) {
                        this.roleData = res.data.data.rows
                    }
                })
            },
            closeAddDialog(){
                this.btnOperation.addDialogVisible = false
            },
            closeEditDialog(){
                this.btnOperation.editDialogVisible = false
            },
            closeMenuAssignDialog(){
              this.btnOperation.menuAssignDialogVisible = false
            },
            add(){
                this.btnOperation.addDialogVisible = true
            },
            edit(index,row){
                this.btnOperation.editDialogVisible = true
                this.editObj = row
            },
            menuAssign(index, row){
                this.btnOperation.menuAssignDialogVisible = true
                this.menuAssignRow = row
            },
            del(index,row){
                this.$confirm('是否删除该角色?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    delRole(row.id).then(res => {
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
            Promise.all([ this.loadTableData(),getData()]).then(res =>{
                this.menuTreeData = res[1].data.data.rows
            })

        }
    }
</script>

<style lang="less" scoped>
</style>
