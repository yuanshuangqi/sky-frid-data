<template>
    <div>
        <add :addChildDialogVisible="btnOperation.addDialogVisible"
             @closeAddDialog="closeAddDialog" @reloadParentData="reloadParentData"/>
        <edit :editChildDialogVisible="btnOperation.editDialogVisible" :editObj="editObj"
              @closeEditDialog="closeEditDialog" @reloadParentData="reloadParentData"/>
        <div class="page-header">
            <div class="page-header-search">
                <el-form :inline="true" :model="search" ref="searchForm" @submit.native.prevent>
                        <el-form-item prop="dictName">
                            <el-input v-model="search.dictName" placeholder="字典名称" size="small" @keydown.enter.native="query"/>
                        </el-form-item>
                </el-form>
            </div>
            <div class="page-header-btn">
                <el-button type="primary" round size="small" @click="add">添 加</el-button>
                <el-button type="primary" round size="small" @click="query">检 索</el-button>
                <el-button type="success" round size="small" @click="reset">重 置</el-button>
            </div>
        </div>
        <div class="page-main">
            <el-table :data="dictData" size="small">
                <el-table-column label="字典名称" prop="dictName"/>
                <el-table-column label="字典code" prop="dictCode"/>
                <el-table-column label="明细">
                    <template slot-scope="scope">
                        {{dictDetail(scope.row)}}
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="250">
                    <template slot-scope="scope">
                        <el-button
                                type="primary"
                                size="mini"
                                @click="edit(scope.$index, scope.row)">编辑
                        </el-button>
                        <el-button
                                size="mini"
                                type="danger"
                                @click="del(scope.$index, scope.row)">删除
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
    import {getData as getDictData, del as delDict} from 'api/dict'
    import add from './add'
    import edit from './edit'

    export default {
        name: "index",
        components: {
            add,
            edit
        },
        computed: {
            // 计算字典明细
            dictDetail(){
                return (row)=>{
                    return row.children.map(value=>`${value.dictNum}:${value.dictName}`).join(';')
                }
            }
        },
        data() {
            return {
                dictData: [],
                editObj: null,
                search: {
                    currentPage: 1,
                    pageSizes: [15, 50, 200, 500],
                    pageSize: 15,
                    total: 0,
                    dictName:''
                },
                btnOperation: {
                    addDialogVisible: false,
                    editDialogVisible: false,
                }
            }
        },
        methods: {
            searchFunction(){
            },
            reset() {
                this.$refs.searchForm.resetFields()
                this.loadDictData()
            },
            query() {
                this.loadDictData()
            },
            handleSizeChange(size) {
                this.search.pageSize = size
                this.loadDictData()
            },
            handleCurrentChange(page) {
                this.search.currentPage = page
                this.loadDictData()
            },
            loadDictData() {
                getDictData(this.search).then(res => {
                    if (res.data.code === 20000) {
                        this.search.total = res.data.data.total
                        this.dictData = res.data.data.rows
                    }
                })
            },
            reloadParentData() {
                this.loadDictData()
            },
            add() {
                this.btnOperation.addDialogVisible = true
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
                    delDict(row.id).then(res => {
                        if (res.data.success) {
                            this.$message.success(res.data.message)
                            this.loadDictData()
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
            }

        },
        created() {
            this.loadDictData()
        }
    }
</script>

<style lang="less" scoped>
</style>
