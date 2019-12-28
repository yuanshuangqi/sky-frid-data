<template>
    <el-dialog title="新增订单" :visible.sync="addDialogVisible" :close-on-click-modal="false">
        <el-form label-position="right" :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="80px">
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="addFormSubmit">确 定</el-button>
            <el-button type="info" @click="resetAddForm">重 置</el-button>
            <el-button @click="closeDialog">取 消</el-button>
        </div>
    </el-dialog>
</template>

<script>
    import {add} from 'api/order'
    export default {
        name: "index",
        props:{
            addChildDialogVisible:Boolean,
        },
        data(){
            return {
                addForm:{
                },
                addFormRules:{
                }
            }
        },
        computed:{
            addDialogVisible:{
                get(){
                    return this.$props.addChildDialogVisible
                },
                set(){
                    this.closeDialog()
                }
            }
        },
        methods:{
            closeDialog(){
                this.$emit('closeAddDialog')
            },
            resetAddForm(){
                this.$refs.addFormRef.resetFields()
            },
            addFormSubmit(){
                add(this.addForm).then(res => {
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
        }
    }
</script>

<style lang="less" scoped>
</style>
