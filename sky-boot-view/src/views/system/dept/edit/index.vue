<template>
    <el-dialog title="编辑部门" :visible.sync="editDialogVisible" :close-on-click-modal="false">
        <el-form label-position="right" :model="editForm" :rules="editFormRules" ref="addFormRef" label-width="90px">
            <el-form-item label="部门Code" prop="deptCode">
                <el-input v-model="editForm.deptCode" :maxlength="20" autocomplete="off"/>
            </el-form-item>
            <el-form-item label="部门名称" prop="deptName">
                <el-input v-model="editForm.deptName" :maxlength="10"  autocomplete="off"/>
            </el-form-item>
            <el-form-item label="父级部门" prop="parentId">
                <el-tree-select :styles="elSelectStyle" v-model="editForm.parentId" :selectParams="selectParams" :treeParams="treeParams" />
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
    import {edit} from 'api/dept'
    export default {
        name: "index",
        props:{
            editChildDialogVisible:Boolean,
            editObj:Object,
            treeData:Array
        },
        data(){
            return {
                elSelectStyle:{
                  width: '100%'
                },
                selectParams: {
                    clearable: true,
                },
                treeParams:{
                    'check-strictly': true,
                    'default-expand-all': true,
                    'expand-on-click-node': false,
                    data:[],
                    props:{
                        value:'id',
                        label:'deptName',
                        checkStrictly:true
                    }
                },
                editForm:{
                    id:'',
                    deptCode:'',
                    deptName:'',
                    parentId:0
                },
                editFormRules:{
                    deptCode:[
                        { required: true, message: '部门Code不能为空', trigger: 'blur' },
                    ],
                    deptName:[
                        { required: true, message: '部门名称不能为空', trigger: 'blur' },
                    ]
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
                this.editForm.deptCode = newVal.deptCode
                this.editForm.deptName = newVal.deptName
                this.editForm.parentId = newVal.parentId
                this.editForm.url = newVal.url
            },
            treeData(newVal){
                this.treeParams.data = newVal
            }
        }
    }
</script>

<style lang="less" scoped>
</style>
