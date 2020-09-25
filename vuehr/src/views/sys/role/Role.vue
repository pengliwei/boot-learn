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
      <el-table :data="role" border style="width: 100%" max-height="700" row-key="id">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="id" fixed align="left" label="角色id" v-if="false" width="100"></el-table-column>
        <el-table-column prop="name" fixed align="left" label="角色名称" width="200"></el-table-column>
        <el-table-column prop="description" label="描述" align="left" width="200"></el-table-column>
        <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
      </el-table>
      <div style="display: flex;justify-content: flex-end">
        <el-pagination
            background
            @current-change="currentChange"
            @size-change="sizeChange"
            layout="sizes, prev, pager, next, jumper, ->, total, slot"
            :total="total">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import eForm from './form'

export default {
  components: { eForm },
  name: 'Role',
  data() {
    return {
      loading: false,
      isAdd: false,
      role: [],
      total: 0,
      page: 1,
      size: 10,
    }
  },
  mounted() {
    this.initData()
  },
  methods: {
    initData() {
      this.loading = true
       let url = '/system/role/getRoleList?page=' + this.page + '&size=' + this.size;
      this.getRequest(url).then((resp) => {
        if (resp) {
          this.role = resp.data;
          this.total = resp.total;
        }
      })
    },
    sizeChange(currentSize) {
        this.size = currentSize;
        this.initData();
    },
    currentChange(currentPage) {
        this.page = currentPage;
        this.initData();
    },
    add(){
      this.isAdd = true;
      this.$refs.form.dialog = true
    },
    handleEdit(scope) {
      this.$refs.form.dialog = true;
      this.$refs.form.form = scope;
    },
    handleDelete(scope) {
      this.$confirm('确定删除吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.postRequest("/system/role/delRole" + scope.id).then(res => {
          if (res) {
            this.initData();
          }
        })
      }).catch(e=>e)
    }
  },
}
</script>

<style scoped>
</style>