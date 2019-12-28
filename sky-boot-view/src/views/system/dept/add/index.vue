<template>
    <el-dialog title="新增部门" :visible.sync="addDialogVisible" :close-on-click-modal="false">
        <el-form label-position="right" :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="90px">
            <el-form-item label="部门Code" prop="deptCode">
                <el-input v-model="addForm.deptCode" :maxlength="20" autocomplete="off"/>
            </el-form-item>
            <el-form-item label="部门名称" prop="deptName">
                <el-input v-model="addForm.deptName" :maxlength="10"  autocomplete="off"/>
            </el-form-item>
            <el-form-item label="父级部门" prop="parentId">
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
    import {add as addDept} from 'api/dept'
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
                      label:'deptName',
                      checkStrictly:true
                  }
              },
              addForm:{
                  deptCode:'',
                  deptName:'',
                  parentId:0
              },
              addFormRules:{
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
                addDept(this.addForm).then(res => {
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
