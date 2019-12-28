<template  >
    <div>
        <template v-for="menu in menus">
            <template v-if="!menu.menuOrBtn">
                <el-menu-item :index="menu.url" v-if="menu.children === undefined || menu.children === null || menu.children.length === 0" :key="menu.id">
                    <i class="el-icon-menu"></i>
                    <span :style="activeStyle" slot="title">{{menu.menuName}}</span>
                </el-menu-item>
                <el-submenu :index="menu.url" :key="menu.id" v-else>
                    <template slot="title">
                        <i class="el-icon-menu"></i>
                        <span  slot="title" :style="activeStyle">{{menu.menuName}}</span>
                    </template>
                    <index :menus="menu.children"/>
                </el-submenu>
            </template>
        </template>
    </div>
</template>

<script>
    export default {
        name: "index",
        props:{
            menus:Array,
            isCollapse:Boolean
        },
        computed:{
            activeStyle(){
                return this.$props.isCollapse?{display:'none'}:{display:'inline'}
            }
        }
    }
</script>

<style scoped>
    .el-menu .el-menu-item{
        background-color: #1f2d3d !important;
    }
    .el-menu .el-menu-item:hover {
        background-color: #001528 !important;
        color: #fff;
    }
</style>
