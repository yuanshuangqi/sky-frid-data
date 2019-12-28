<template>
    <div>
        <add :addChildDialogVisible="btnOperation.addDialogVisible"
             @closeAddDialog="closeAddDialog" @reloadParentData="reloadParentData"/>
        <edit :editChildDialogVisible="btnOperation.editDialogVisible" :editObj="editObj"
              @closeEditDialog="closeEditDialog" @reloadParentData="reloadParentData"/>
        <div class="page-header">
            <div class="page-header-search">
                <el-form :inline="true" :model="search" ref="searchForm">

                </el-form>
            </div>
            <div class="page-header-btn">
                <el-button type="primary" round size="small" @click="add">添 加</el-button>
                <el-button type="primary" round size="small" @click="query">检 索</el-button>
                <el-button type="success" round size="small" @click="reset">重 置</el-button>
            </div>
        </div>
        <div class="page-main">
            <el-table :data="${entity?lower_case}Data" size="small">
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
    import {getData as get${entity}Data, del as del${entity}} from 'api/${entity?lower_case}'
    import add from './add'
    import edit from './edit'

    export default {
        name: "index",
        components: {
            add,
            edit
        },
        computed: {},
        data() {
            return {
                ${entity?lower_case}Data: [],
                editObj: null,
                search: {
                    currentPage: 1,
                    pageSizes: [15, 50, 200, 500],
                    pageSize: 15,
                    total: 0
                },
                btnOperation: {
                    addDialogVisible: false,
                    editDialogVisible: false,
                }
            }
        },
        methods: {
            reset() {
                this.$refs.searchForm.resetFields()
            },
            query() {
                this.load${entity}Data()
            },
            handleSizeChange(size) {
                this.search.pageSize = size
                this.load${entity}Data()
            },
            handleCurrentChange(page) {
                this.search.currentPage = page
                this.load${entity}Data()
            },
            load${entity}Data() {
                get${entity}Data(this.search).then(res => {
                    if (res.data.code === 20000) {
                        this.search.total = res.data.data.total
                        this.${entity?lower_case}Data = res.data.data.rows
                    }
                })
            },
            reloadParentData() {
                this.load${entity}Data()
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
                    del${entity}(row.id).then(res => {
                        if (res.data.success) {
                            this.$message.success(res.data.message)
                            this.load${entity}Data()
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
            this.load${entity}Data()
        }
    }
</script>

<style lang="less" scoped>
</style>
