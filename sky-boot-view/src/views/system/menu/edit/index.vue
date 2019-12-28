<template>
    <el-dialog title="编辑菜单" :visible.sync="editDialogVisible" :close-on-click-modal="false">
        <el-form label-position="right" :model="editForm" :rules="editFormRules" ref="addFormRef" label-width="90px">
            <el-form-item label="菜单Code" prop="menuCode">
                <el-input v-model="editForm.menuCode" :maxlength="20" autocomplete="off"/>
            </el-form-item>
            <el-form-item label="菜单名称" prop="menuName">
                <el-input v-model="editForm.menuName" :maxlength="10"  autocomplete="off"/>
            </el-form-item>
            <el-form-item label="url" prop="url">
                <el-input v-model="editForm.url" :maxlength="10"  autocomplete="off"/>
            </el-form-item>
            <el-form-item label="类型" prop="resource">
                <el-radio-group v-model="editForm.menuOrBtn">
                    <el-radio :label="false">菜单</el-radio>
                    <el-radio :label="true">按钮</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="父级菜单" prop="parentId">
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
    import {edit} from 'api/menu'
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
                        label:'menuName',
                        checkStrictly:true
                    }
                },
                editForm:{
                    id:'',
                    menuCode:'',
                    menuName:'',
                    url:'',
                    menuOrBtn:false,
                    parentId:0
                },
                editFormRules:{
                    menuCode:[
                        { required: true, message: '菜单Code不能为空', trigger: 'blur' },
                    ],
                    menuName:[
                        { required: true, message: '菜单名称不能为空', trigger: 'blur' },
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
                this.editForm.menuCode = newVal.menuCode
                this.editForm.menuName = newVal.menuName
                this.editForm.parentId = newVal.parentId
                this.editForm.url = newVal.url
                this.editForm.menuOrBtn = newVal.menuOrBtn
            },
            treeData(newVal){
                this.treeParams.data = newVal
            }
        }
    }
</script>

<style lang="less" scoped>
</style>
