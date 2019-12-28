<template>
    <el-dialog title="编辑角色" :visible.sync="editDialogVisible" :close-on-click-modal="false">
        <el-form label-position="right" :model="editForm" :rules="editFormRules" ref="addFormRef" label-width="90px">
            <el-form-item label="角色Code" prop="roleCode">
                <el-input v-model="editForm.roleCode" :maxlength="20" autocomplete="off"/>
            </el-form-item>
            <el-form-item label="角色名称" prop="roleName">
                <el-input v-model="editForm.roleName" :maxlength="10"  autocomplete="off"/>
            </el-form-item>
            <el-form-item label="父级角色" prop="parentId">
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
    import {edit} from 'api/role'
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
                        label:'roleName',
                        checkStrictly:true
                    }
                },
                editForm:{
                    id:'',
                    roleCode:'',
                    roleName:'',
                    parentId:0
                },
                editFormRules:{
                    roleCode:[
                        { required: true, message: '角色Code不能为空', trigger: 'blur' },
                    ],
                    roleName:[
                        { required: true, message: '角色名称不能为空', trigger: 'blur' },
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
                this.editForm.roleCode = newVal.roleCode
                this.editForm.roleName = newVal.roleName
                this.editForm.parentId = newVal.parentId
            },
            treeData(newVal){
                this.treeParams.data = newVal
            }
        }
    }
</script>

<style lang="less" scoped>
</style>
