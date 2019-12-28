<template>
    <el-dialog title="权限分配" :visible.sync="menuAssignDialogVisible" :close-on-click-modal="false">
        <el-tree ref="menuTreeRef" :check-strictly="true" :data="menuTreeData" node-key="id" :props="treeProps" :default-expand-all="true"
                 :default-checked-keys="defaultCheckedKeys" :show-checkbox="true"></el-tree>
        <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="menuAssignFormSubmit">确 定</el-button>
            <el-button @click="closeDialog">取 消</el-button>
        </div>
    </el-dialog>
</template>

<script>
    import {getList} from 'api/menu'
    import {roleMenuAssign} from 'api/role'
    import ObjUtil from "../../../../util/ObjUtil";

    export default {
        name: "index",
        data() {
            return {
                menuData: [],
                treeProps: {
                    label: 'menuName'
                },

            }
        },
        props: {
            menuAssignChildDialogVisible: Boolean,
            menuAssignRow: Object,
            menuTreeData: Array
        },
        computed: {
            defaultCheckedKeys() {
                // let parentIds = this.$props.menuAssignRow.menus.map(e => e.parentId)
                //return this.$props.menuAssignRow.menus.filter(e => parentIds.indexOf(e.id) === -1).map(e => e.id)
                let row = this.$props.menuAssignRow
                if(ObjUtil.isNotEmpty(row)){
                    return row.menus.map(e => e.id)
                }else {
                    return [];
                }

            },
            menuAssignDialogVisible: {
                get() {
                    return this.$props.menuAssignChildDialogVisible
                },
                set() {
                    this.closeDialog()
                }
            }
        },
        methods: {
            closeDialog() {
                //离开页面，将所有选中的节点默认
                this.$refs.menuTreeRef.setCheckedNodes([])
                this.$emit('closeMenuAssignDialog')
            },
            menuAssignFormSubmit() {
                roleMenuAssign({
                    'roleId': this.$props.menuAssignRow.id,
                    'menuIds': this.$refs.menuTreeRef.getCheckedNodes(false, true).map(value => value.id)
                }).then(res => {
                    if (res.data.success) {
                        this.$message.success(res.data.message)
                        this.closeDialog()
                        this.$emit('reloadParentData')
                    }
                })
            }
        },
        watch: {
            menuAssignRow(val) {
                if(this.ObjUtil.isNotEmpty(val)){
                    this.defaultCheckedKeys = val.menus.map(value => value.id)
                }
        }
        },
        created() {
            getList().then(res => {
                if (res.data.success) {
                    this.roleData = res.data.data.items
                }
            })
        }
    }
</script>

<style scoped>

</style>
