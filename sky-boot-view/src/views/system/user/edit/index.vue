<template>
    <el-dialog title="编辑用户" :visible.sync="editDialogVisible" :close-on-click-modal="false">
        <el-form label-position="right" :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="80px">
            <el-form-item label="账号" prop="account">
                <el-input v-model="editForm.account" :maxlength="20" autocomplete="off"/>
            </el-form-item>
            <el-form-item label="中文名" prop="cname">
                <el-input v-model="editForm.cname" :maxlength="10"  autocomplete="off"/>
            </el-form-item>
            <el-form-item label="英文名" prop="ename">
                <el-input v-model="editForm.ename" :maxlength="20" autocomplete="off"/>
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
                <el-input v-model="editForm.email" :maxlength="20" autocomplete="off"/>
            </el-form-item>
            <el-form-item label="电话号码" prop="phone">
                <el-input v-model="editForm.phone"  autocomplete="off"/>
            </el-form-item>
            <el-form-item label="住址" prop="address">
                <el-input v-model="editForm.address" autocomplete="off"/>
            </el-form-item>
            <el-form-item label="部门" prop="dept.id">
                <el-tree-select :styles="elSelectStyle" v-model="editForm.dept.id" :selectParams="selectParams" :treeParams="treeParams" />
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
    import {edit} from 'api/user'
    export default {
        name: "index",
        props:{
            editChildDialogVisible:Boolean,
            editObj:Object,
            deptTreeData:Array
        },
        data(){
            return {
                editForm:{
                    id:'',
                    account:'',
                    cname:'',
                    ename:'',
                    email:'',
                    phone:'',
                    address:'',
                    dept:{
                        id:''
                    }
                },
                editFormRules:{
                    account:[
                        { required: true, message: '账号不能为空', trigger: 'blur' },
                    ],
                    cname:[
                        { required: true, message: '中文名不能为空', trigger: 'blur' },
                    ]
                },
                elSelectStyle:{
                    width: '100%'
                },
                selectParams: {
                    clearable: true,
                },
                treeParams: {
                    'check-strictly': true,
                    'default-expand-all': true,
                    'expand-on-click-node': false,
                    data: [],
                    props: {
                        value: 'id',
                        label: 'deptName',
                        checkStrictly: true
                    }
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
                this.editForm.account = newVal.account
                this.editForm.cname = newVal.cname
                this.editForm.ename = newVal.ename
                this.editForm.email = newVal.email
                this.editForm.phone = newVal.phone
                this.editForm.address = newVal.address
            },
            deptTreeData(val){
                this.treeParams.data = val
            }
        }
    }
</script>

<style lang="less" scoped>
</style>
