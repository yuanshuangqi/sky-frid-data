<template>
    <div>
        <add :addChildDialogVisible="btnOperation.addDialogVisible" :deptTreeData="deptTreeData"
             @closeAddDialog="closeAddDialog" @reloadParentData="reloadParentData"/>
        <edit :editChildDialogVisible="btnOperation.editDialogVisible" :editObj="editObj" :deptTreeData="deptTreeData"
              @closeEditDialog="closeEditDialog" @reloadParentData="reloadParentData"/>
        <roleAssign :roleAssignChildDialogVisible="btnOperation.roleAssignDialogVisible" :roleAssignRow="roleAssignRow"
                @closeRoleAssignDialog="closeRoleAssignDialog" @reloadParentData="reloadParentData"/>
        <div class="page-header">
            <div class="page-header-search">
                <el-form :inline="true" :model="search" ref="searchForm" @submit.native.prevent>
                    <el-form-item prop="cname">
                        <el-input v-model="search.cname" placeholder="中文名" size="small"  @keydown.enter.native="query"/>
                    </el-form-item>
                    <el-form-item prop="ename">
                        <el-input v-model="search.ename" placeholder="英文名" size="small"  @keydown.enter.native="query"/>
                    </el-form-item>
                    <el-form-item prop="account">
                        <el-input v-model="search.account" placeholder="账号" size="small"  @keydown.enter.native="query"/>
                    </el-form-item>
                    <el-form-item prop="email">
                        <el-input v-model="search.email" placeholder="邮箱" size="small"  @keydown.enter.native="query"/>
                    </el-form-item>
                    <el-form-item prop="address">
                        <el-input v-model="search.address" placeholder="住址" size="small" @keydown.enter.native="query"/>
                    </el-form-item>
                </el-form>
            </div>
            <div class="page-header-btn">
                <el-button type="primary" round size="small" @click="add" v-if="$skyPermission.hasPermission('wwwww')">添 加</el-button>
                <el-button type="primary" round size="small" @click="query">检 索</el-button>
                <el-button type="success" round size="small" @click="reset">重 置</el-button>
            </div>
        </div>
        <div class="page-main">
            <el-table :data="userData" size="small">
                <el-table-column prop="cname" label="中文名"/>
                <el-table-column prop="ename" label="英文名"/>
                <el-table-column prop="account" label="账号"/>
                <el-table-column prop="email" label="邮箱"/>
                <el-table-column prop="phone" label="电话号码"/>
                <el-table-column prop="address" label="住址"/>
                <el-table-column prop="dept.deptName" label="部门"/>
                <el-table-column label="角色">
                    <template slot-scope="scope">
                        {{userRoles(scope.row)}}
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="250">
                    <template slot-scope="scope">
                        <el-button
                                type="primary"
                                size="mini"
                                @click="edit(scope.$index, scope.row)" v-if="$skyPermission.hasPermission('user_mgr_edit')">编辑
                        </el-button>
                        <el-button
                                type="primary"
                                size="mini"
                                @click="roleAssign(scope.$index, scope.row)" v-if="$skyPermission.hasPermission('user_mgr_role_assign')">角色分配
                        </el-button>
                        <el-button
                                size="mini"
                                type="danger"
                                @click="del(scope.$index, scope.row)" v-if="$skyPermission.hasPermission('user_mgr_del')">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <div class="page-footer">
            <el-pagination
                    background
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page.sync="search.currentPage"
                    :page-sizes=search.pageSizes
                    :page-size=search.pageSize
                    layout="total,sizes, prev, pager, next"
                    :total="search.total">
            </el-pagination>
        </div>
    </div>
</template>

<script>
    import {getUserData, del as delUser} from 'api/user'
    import {getData as getDeptData} from "../../../api/dept";
    import add from './add'
    import edit from './edit'
    import roleAssign from './roleAssign'

    export default {
        name: "index",
        components: {
            add,
            edit,
            roleAssign
        },
        computed: {
            userRoles() {
                return row => {
                    return row.roles.map(value => value.roleName).join(';')
                }
            }
        },
        data() {
            return {
                userData: [],
                editObj: null,
                roleAssignRow:null,
                search: {
                    currentPage: 1,
                    pageSizes: [15, 50, 200, 500],
                    pageSize: 15,
                    total: 0,
                    cname: '',
                    ename: '',
                    account: '',
                    email: '',
                    address: ''
                },
                btnOperation: {
                    addDialogVisible: false,
                    editDialogVisible: false,
                    roleAssignDialogVisible: false
                },
                deptTreeData:[]
            }
        },
        methods: {
            reset() {
                this.$refs.searchForm.resetFields()
            },
            query() {
                this.loadUserData()
            },
            handleSizeChange(size) {
                this.search.pageSize = size
                this.loadUserData()
            },
            handleCurrentChange(page) {
                this.search.currentPage = page
                this.loadUserData()
            },
            loadUserData() {
                getUserData(this.search).then(res => {
                    if (res.data.code === 20000) {
                        this.search.total = res.data.data.total
                        this.userData = res.data.data.rows
                    }
                })
            },
            reloadParentData() {
                this.loadUserData()
            },
            add() {
                this.btnOperation.addDialogVisible = true
            },
            // 角色分配
            roleAssign(index, row) {
                this.btnOperation.roleAssignDialogVisible = true
                this.roleAssignRow = row
            },
            edit(index, row) {
                this.btnOperation.editDialogVisible = true
                this.editObj = row
            },
            del(index, row) {
                this.$confirm('是否删除用户?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    delUser(row.id).then(res => {
                        if (res.data.success) {
                            this.$message.success(res.data.message)
                            this.loadUserData()
                        }
                    })
                }).catch(() => {

                });
            },
            closeAddDialog() {
                this.btnOperation.addDialogVisible = false
            },
            closeEditDialog() {
                this.btnOperation.editDialogVisible = false
            },
            closeRoleAssignDialog(){
                this.btnOperation.roleAssignDialogVisible = false
            }

        },
        created() {
            Promise.all([this.loadUserData(),getDeptData()]).then(res=>{
                if (res[1].data.code === 20000) {
                    this.deptTreeData = res[1].data.data.rows
                }
            })

        }
    }
</script>

<style lang="less" scoped>
</style>
