<template>
  <div class="app-container">
    <!--新增编辑菜单弹框-->
    <eForm ref="form" :is-add="isAdd"/>
    <!--工具栏-->
    <div class="head-container">
      <div style="display: flex;justify-content: space-between">
            <el-button icon="el-icon-plus" type="primary" @click="add">新增</el-button>
        </div>
    </div>
    <!--表格渲染-->
    <div style="margin-top: 10px">
      <el-table
        :data="menu"
        style="width: 100%"
        max-height="700"
        row-key="id"
        lazy
        :load="load"
        :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
      >
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="id" fixed align="left" label="菜单id" v-if="false" width="100"></el-table-column>
        <el-table-column prop="name" fixed align="left" label="菜单名称" width="200"></el-table-column>
        <el-table-column prop="url" label="菜单url" align="left" width="200"></el-table-column>
        <el-table-column prop="path" label="菜单路由" align="left" width="150"></el-table-column>
        <el-table-column prop="parentid" label="父id" v-if="false" align="left" width="100"></el-table-column>
        <el-table-column prop="component" label="目录名称" align="left" width="100"></el-table-column>
        <el-table-column label="操作" width="200">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import eForm from './form';
import {getRequest, postRequest, postKeyValueRequest, putRequest, deleteRequest } from '../../../utils/api';

export default {
  components: { eForm },
  name: 'Menu',
  data() {
    return {
      loading: false,
      isAdd: false,
      menu: []
    }
  },
  mounted() {
    this.initData()
  },
  methods: {
    // 加载菜单
    initData() {
      this.loading = true
      let url = '/learn/system/menu'
      this.getRequest(url).then((resp) => {
        if (resp) {
          this.menu = resp.data
        }
      })
    },
    // 加载树
    load(tree, treeNode, resolve) {
      this.loading = true
      let url = '/learn/system/menu'
      if (tree) {
        url += '?parentId=' + tree.id
      }
      setTimeout(() => {
        this.getRequest(url).then((resp) => {
          if (resp) {
            if (resolve) {
              resolve(resp.data)
            }
          }
        })
      }, 100)
    },
    // 新增
    add(){
      this.isAdd = true;
      this.$refs.form.dialog = true
    },
    //编辑菜单
    handleEdit(scope) {
      this.$refs.form.dialog = true;
      this.$refs.form.form = scope;
    },
    //删除菜单
    handleDelete(scope) {
      this.$confirm('确定删除吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteRequest("/learn/system/menu/delMenu/"+scope.id).then(res => {
          if (res) {
            this.initData();
          }
        })
      }).catch(e=>e)
    },
  },
}
</script>

<style scoped>
</style>