<template>
    <el-dialog title="新增用户" :visible.sync="addDialogVisible" :close-on-click-modal="false">
        <el-form label-position="right" :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="80px">
            <el-form-item label="账号" prop="account">
                <el-input v-model="addForm.account" :maxlength="20" autocomplete="off"/>
            </el-form-item>
            <el-form-item label="中文名" prop="cname">
                <el-input v-model="addForm.cname" :maxlength="10"  autocomplete="off"/>
            </el-form-item>
            <el-form-item label="英文名" prop="ename">
                <el-input v-model="addForm.ename" :maxlength="20" autocomplete="off"/>
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
                <el-input v-model="addForm.email" :maxlength="20" autocomplete="off"/>
            </el-form-item>
            <el-form-item label="电话号码" prop="phone">
                <el-input v-model="addForm.phone"  autocomplete="off"/>
            </el-form-item>
            <el-form-item label="住址" prop="address">
                <el-input v-model="addForm.address" autocomplete="off"/>
            </el-form-item>
            <el-form-item label="部门" prop="dept.id">
                <el-tree-select :styles="elSelectStyle" v-model="addForm.dept.id" :selectParams="selectParams" :treeParams="treeParams" />
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="addFormSubmit">确 定</el-button>
            <el-button type="info" @click="resetAddForm">重 置</el-button>
            <el-button @click="closeDialog">取 消</el-button>
        </div>
    </el-dialog>
</template>

<script>
    import {addUser} from 'api/user'
    export default {
        name: "index",
        props:{
            addChildDialogVisible:Boolean,
            deptTreeData:Array
        },
        data(){
          return {
              addForm:{
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
              addFormRules:{
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
                addUser(this.addForm).then(res => {
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
        deptTreeData(val){
            this.treeParams.data = val
        }
        }
    }
</script>

<style lang="less" scoped>
</style>
