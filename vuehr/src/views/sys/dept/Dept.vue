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
        v-loading="loading"
        :data="dept"
        style="width: 100%"
        max-height="700"
        row-key="deptId"
        lazy
        :load="load"
        :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
      >
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="deptId" fixed align="left" label="部门id" v-if="false" width="100"></el-table-column>
        <el-table-column prop="name" fixed align="left" label="部门名称" width="200"></el-table-column>
        <el-table-column prop="parentId" label="父id" v-if="false" align="left" width="100"></el-table-column>
        <el-table-column prop="createTime" label="创建时间" align="left" width="200"></el-table-column>
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
import {getRequest, postRequest, putRequest, deleteRequest } from '../../../utils/api';

export default {
  components: { eForm },
  name: 'Dept',
  data() {
    return {
      loading: false,
      isAdd: false,
      dept: [],
      parentId: '',
    }
  },
  mounted() {
    this.initData()
  },
  methods: {
    // 加载部门
    initData() {
      this.loading = false;
      let url = '/learn/system/dept/dept?parentId=' + this.parentId;
      this.getRequest(url).then((resp) => {
        if (resp) {
          this.dept = resp.data
        }
      })
    },
    // 加载树
    load(tree, treeNode, resolve) {
      let url = '/learn/system/dept/dept'
      if (tree) {
        url += '?parentId=' + tree.deptId
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
    //编辑部门
    handleEdit(scope) {
      this.$refs.form.dialog = true;
      this.$refs.form.form = scope;
    },
    //删除部门
    handleDelete(scope) {
      this.$confirm('确定删除吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.loading = true;
        this.dept = [];
        this.deleteRequest("/learn/system/dept/delDept/"+scope.deptId).then(res => {
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