<template>
    <el-dialog title="角色分配" :visible.sync="roleAssignDialogVisible" :close-on-click-modal="false">
        <el-checkbox-group v-model="roleIdArr">
            <el-checkbox  v-for="role in roleData" :key="role.id" :label="role.id">{{role.roleName}}</el-checkbox>
        </el-checkbox-group>
        <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="roleAssignFormSubmit">确 定</el-button>
            <el-button @click="closeDialog">取 消</el-button>
        </div>
    </el-dialog>
</template>

<script>
    import {getList} from 'api/role'
    import {roleAssign} from 'api/user'
    export default {
        name: "index",
        data(){
          return {
              roleData:[],
              treeProps:{
                  label: 'roleName'
              },
              roleIdArr:[]
          }
        },
        props:{
            roleAssignChildDialogVisible:Boolean,
            roleAssignRow:Object
        },
        computed:{
            roleAssignDialogVisible:{
                get(){
                    return this.$props.roleAssignChildDialogVisible
                },
                set(){
                    this.closeDialog()
                }
            }
        },
        methods:{
            closeDialog(){
                this.$emit('closeRoleAssignDialog')
            },
            roleAssignFormSubmit(){
                roleAssign({
                    id: this.$props.roleAssignRow.id,
                    roleIds:this.roleIdArr
                }).then(res => {
                    if(res.data.success){
                        this.$message.success(res.data.message)
                        this.closeDialog()
                        this.$emit('reloadParentData')
                    }
                })
            }
        },
        watch:{
            // 设置用户默认角色
            roleAssignRow(val){
                this.roleIdArr =[]
                let roles = val.roles
                if(roles.length > 0){
                    this.roleIdArr = roles.map(value => value.id)
                }
            }
        },
        created() {
            getList().then(res => {
                if(res.data.success){
                    this.roleData = res.data.data.items
                }
            })
        }
    }
</script>

<style scoped>

</style>
