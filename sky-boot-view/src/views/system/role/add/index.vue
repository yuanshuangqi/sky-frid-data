<template>
    <el-dialog title="新增角色" :visible.sync="addDialogVisible" :close-on-click-modal="false">
        <el-form label-position="right" :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="90px">
            <el-form-item label="角色Code" prop="roleCode">
                <el-input v-model="addForm.roleCode" :maxlength="20" autocomplete="off"/>
            </el-form-item>
            <el-form-item label="角色名称" prop="roleName">
                <el-input v-model="addForm.roleName" :maxlength="10"  autocomplete="off"/>
            </el-form-item>
            <el-form-item label="父级角色" prop="parentId">
                <el-tree-select :styles="elSelectStyle" v-model="addForm.parentId" :selectParams="selectParams" :treeParams="treeParams" />
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
    import {add as addRole} from 'api/role'
    export default {
        name: "index",
        props:{
            addChildDialogVisible:Boolean,
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
              addForm:{
                  roleCode:'',
                  roleName:'',
                  parentId:0
              },
              addFormRules:{
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
            // 关闭对话框
            closeDialog(){
                this.$emit('closeAddDialog')
            },
            // 重置新增表单
            resetAddForm(){
                this.$refs.addFormRef.resetFields()
            },
            // 新增
            addFormSubmit(){
                addRole(this.addForm).then(res => {
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
            treeData(newVal){
                this.treeParams.data = newVal
            }
        }
    }
</script>

<style lang="less" scoped>
</style>
