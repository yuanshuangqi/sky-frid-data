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
                <el-button type="primary" round size="small" @click="resumeAll">全部开始</el-button>
                <el-button type="primary" round size="small" @click="pauseAll">全部暂停</el-button>
            </div>
        </div>
        <div class="page-main">
            <el-table :data="quartzData" size="small">
                <el-table-column label="任务名称" prop="description"/>
                <el-table-column label="任务状态" prop="triggerState"/>
                <el-table-column label="cron表达式" prop="trigger.cronExpression"/>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button
                                type="primary"
                                size="mini"
                                @click="executeImmediately(scope.$index, scope.row)">立即执行
                        </el-button>
                        <el-button
                                type="primary"
                                size="mini"
                                @click="start(scope.$index, scope.row)">开始
                        </el-button>
                        <el-button
                                type="primary"
                                size="mini"
                                @click="pause(scope.$index, scope.row)">暂停
                        </el-button>
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
    </div>
</template>

<script>
    import {getData as getQuartzData, del as delTask,start,pause,resumeAll,pauseAll,executeImmediately} from 'api/quartz'
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
                quartzData: [],
                editObj: null,
                search: {
                },
                btnOperation: {
                    addDialogVisible: false,
                    editDialogVisible: false,
                }
            }
        },
        methods: {
            // 立即执行
            executeImmediately(index,row){
                executeImmediately(row.trigger.jobKey).then((res)=>{
                    let result = res.data
                    if(result.success){
                        this.$message.success(result.message)
                        this.loadQuartzData()
                    }
                })
            },
            // 暂停全部任务
            pauseAll(){
                pauseAll().then((res)=>{
                    let result = res.data
                    if(result.success){
                        this.$message.success(result.message)
                        this.loadQuartzData()
                    }
                })
            },
            // 恢复全部任务
            resumeAll(){
                resumeAll().then((res)=>{
                    let result = res.data
                    if(result.success){
                        this.$message.success(result.message)
                        this.loadQuartzData()
                    }
                })
            },
            // 开始/恢复 任务
            start(index,row){
                start(row.trigger.jobKey).then((res)=>{
                    let result = res.data
                    if(result.success){
                        this.$message.success(result.message)
                        this.loadQuartzData()
                    }
                })
            },
            // 暂停任务
            pause(index,row){
                pause(row.trigger.jobKey).then((res)=>{
                    let result = res.data
                    if(result.success){
                        this.$message.success(result.message)
                        this.loadQuartzData()
                    }
                })
            },
            reset() {
                this.$refs.searchForm.resetFields()
            },
            query() {
                this.loadQuartzData()
            },
            loadQuartzData() {
                getQuartzData(this.search).then(res => {
                    if (res.data.code === 20000) {
                        this.quartzData = res.data.data.items
                    }
                })
            },
            reloadParentData() {
                this.loadQuartzData()
            },
            add() {
                this.btnOperation.addDialogVisible = true
            },
            edit(index, row) {
                this.btnOperation.editDialogVisible = true
                this.editObj = row
            },
            //删除任务
            del(index, row) {
                this.$confirm('是否删除任务?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    delTask(row.trigger.jobKey).then((res)=>{
                        let result = res.data
                        if(result.success){
                            this.$message.success(result.message)
                            this.loadQuartzData()
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
            this.loadQuartzData()
        }
    }
</script>

<style lang="less" scoped>
</style>
