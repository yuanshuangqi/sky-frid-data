<template>
    <el-dialog title="新增字典" :visible.sync="addDialogVisible" :close-on-click-modal="false">
        <el-form :inline="true" label-position="right" :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="80px">
            <el-form-item label="字典名称" prop="dictName">
                <el-input v-model="addForm.dictName" :maxlength="20" autocomplete="off"/>
            </el-form-item>
            <el-form-item label="字典Code" prop="dictCode">
                <el-input v-model="addForm.dictCode" :maxlength="20" autocomplete="off"/>
            </el-form-item>
            <el-form-item label="" >
                <el-button type="primary" @click="addChildren">添 加</el-button>
            </el-form-item>
            <div v-for="item in addForm.children" :key="item.num">
                <el-form-item label="编号" >
                    <el-input v-model="item.dictNum" :maxlength="20" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="名称">
                    <el-input v-model="item.dictName" :maxlength="20" autocomplete="off"/>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="delChildren(item.num)">删 除</el-button>
                </el-form-item>
            </div>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="addFormSubmit">确 定</el-button>
            <el-button type="info" @click="resetAddForm">重 置</el-button>
            <el-button @click="closeDialog">取 消</el-button>
        </div>
    </el-dialog>
</template>

<script>
    import {add} from 'api/dict'
    export default {
        name: "index",
        props:{
            addChildDialogVisible:Boolean,
        },
        data(){
            return {
                addForm:{
                    dictName:'',
                    dictCode:'',
                    children:[
                    ]
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
            // 删除子字典
            delChildren(num){
                this.addForm.children =  this.addForm.children.filter(value => value.num !== num)
            },
            //增加子字典
            addChildren(){
                let children = this.addForm.children
                if(children.length === 0){
                    children.push({
                        num:0,
                        dictNum:'',
                        dictName:''
                    })
                }else{
                    let last = children[children.length - 1]
                    children.push({
                        num:last.num + 1,
                        dictNum:'',
                        dictName:''
                    })
                }
            },
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
