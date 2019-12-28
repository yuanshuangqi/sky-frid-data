<template>
    <el-dialog title="新增菜单" :visible.sync="addDialogVisible" :close-on-click-modal="false">
        <el-form label-position="right" :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="90px">
            <el-form-item label="菜单Code" prop="menuCode">
                <el-input v-model="addForm.menuCode"  autocomplete="off"/>
            </el-form-item>
            <el-form-item label="菜单名称" prop="menuName">
                <el-input v-model="addForm.menuName"   autocomplete="off"/>
            </el-form-item>
            <el-form-item label="url" prop="url">
                <el-input v-model="addForm.url" autocomplete="off"/>
            </el-form-item>
            <el-form-item label="类型" prop="resource">
                <el-radio-group v-model="addForm.menuOrBtn">
                    <el-radio :label="false">菜单</el-radio>
                    <el-radio :label="true">按钮</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="父级菜单" prop="parentId">
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
    import {add as addMenu} from 'api/menu'
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
                      label:'menuName',
                      checkStrictly:true
                  }
              },
              addForm:{
                  menuCode:'',
                  menuName:'',
                  url:'',
                  menuOrBtn:false,
                  parentId:0
              },
              addFormRules:{
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
                addMenu(this.addForm).then(res => {
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
