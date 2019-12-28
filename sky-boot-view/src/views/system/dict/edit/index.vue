<template>
    <el-dialog title="编辑字典" :visible.sync="editDialogVisible" :close-on-click-modal="false">
        <el-form :inline="true" label-position="right" :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="80px">
            <el-form-item label="字典名称" prop="dictName">
                <el-input v-model="editForm.dictName" :maxlength="20" autocomplete="off"/>
            </el-form-item>
            <el-form-item label="字典Code" prop="dictCode">
                <el-input v-model="editForm.dictCode" :maxlength="20" autocomplete="off"/>
            </el-form-item>
            <el-form-item label="" >
                <el-button type="primary" @click="addChildren">添 加</el-button>
            </el-form-item>
            <div v-for="item in editForm.children" :key="item.id">
                <el-form-item label="编号" >
                    <el-input v-model="item.dictNum" :maxlength="20" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="名称">
                    <el-input v-model="item.dictName" :maxlength="20" autocomplete="off"/>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="delChildren(item.id)">删 除</el-button>
                </el-form-item>
            </div>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="editFormSubmit">确 定</el-button>
            <el-button type="info" @click="resetAddForm">重 置</el-button>
            <el-button @click="closeDialog">取 消</el-button>
        </div>
    </el-dialog>
</template>

<script>
    import {edit} from 'api/dict'
    export default {
        name: "index",
        props:{
            editChildDialogVisible:Boolean,
            editObj:Object
        },
        data(){
            return {
                editForm:{
                    id:'',
                    dictName:'',
                    dictCode:'',
                    children:[
                    ]
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
            // 删除子字典
            delChildren(id){
                this.editForm.children =  this.editForm.children.filter(value => value.id !== id)
            },
            //增加子字典
            addChildren(){
                let children = this.editForm.children
                if(children.length === 0){
                    children.push({
                        id:0,
                        dictNum:'',
                        dictName:''
                    })
                }else{
                    let last = children[children.length - 1]
                    children.push({
                        id:last.id + 1,
                        dictNum:'',
                        dictName:''
                    })
                }
            },
            closeDialog(){
                this.$emit('closeEditDialog')
            },
            resetAddForm(){
                this.$refs.editFormRef.resetFields()
            },
            editFormSubmit(){
                edit(this.editForm).then(res => {
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
                this.editForm.id = newVal.id
                this.editForm.dictName = newVal.dictName
                this.editForm.deptCode = newVal.deptCode
                this.editForm.children = newVal.children
            }
        }
    }
</script>

<style lang="less" scoped>
</style>
