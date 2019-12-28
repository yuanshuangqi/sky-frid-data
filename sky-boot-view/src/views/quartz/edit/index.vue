<template>
    <el-dialog title="编辑定时任务" :visible.sync="editDialogVisible" :close-on-click-modal="false">
        <el-form label-position="right" :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="100px">
        <el-form-item label="cron表达式">
            <el-input v-model="editForm.cron" auto-complete="off"/>
        </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="editFormSubmit">确 定</el-button>
            <el-button type="info" @click="resetAddForm">重 置</el-button>
            <el-button @click="closeDialog">取 消</el-button>
        </div>
    </el-dialog>
</template>

<script>
    import {modifyCron} from 'api/quartz'
    export default {
        name: "index",
        props:{
            editChildDialogVisible:Boolean,
            editObj:Object
        },
        data(){
            return {
                editForm:{
                    name:'',
                    group:'',
                    cron:'',
                },
                editFormRules:{
                }
            }
        },
        computed:{
            editDialogVisible:{
                get(){
                    return this.$props.editChildDialogVisible
                },
                set(){
                    this.closeDialog()
                }

            }
        },
        methods:{
            closeDialog(){
                this.$emit('closeEditDialog')
            },
            resetAddForm(){
                this.$refs.editFormRef.resetFields()
            },
            editFormSubmit(){
                modifyCron(this.editForm).then(res => {
                    let result = res.data
                    if(result.success){
                        this.$message.success(result.message)
                        this.closeDialog()
                        this.$emit('reloadParentData')
                    }
                })
            }
        },
        watch:{
            editObj(newVal){
                this.editForm.name = newVal.trigger.jobKey.name
                this.editForm.group = newVal.trigger.jobKey.group
                this.editForm.cron = newVal.trigger.cronExpression
            }
        }
    }
</script>

<style lang="less" scoped>
</style>
