<template>
  <div class="app-container">
    <!--新增编辑菜单弹框-->
    <eForm ref="form" :is-add="isAdd"/>
    <!--工具栏-->
    <div class="head-container">
      <!--搜索框-->
      <div>
        <el-input placeholder="请输入角色名称进行搜索" prefix-icon="el-icon-search"
                  clearable
                  @clear="initData"
                  style="width: 350px;margin-right: 10px" v-model="keyword">
        </el-input>
        <el-button icon="el-icon-search" type="primary" @click="initData">搜索</el-button>
      </div>
      <!--新增-->
      <div style="display: flex;justify-content: space-between">
          <el-button icon="el-icon-plus" type="primary" @click="add">新增</el-button>
      </div>
    </div>
    <!--表格渲染-->
    <el-row :gutter="15">
      <!--角色管理-->
      <el-col :xs="24" :sm="24" :md="16" :lg="16" :xl="17" style="margin-bottom: 10px">
        <el-card class="box-card" shadow="never">
          <div slot="header" class="clearfix">
            <span class="role-span">角色列表</span>
          </div>
          <el-table highlight-current-row :data="role" border style="width: 100%" max-height="700" row-key="id" @current-change="handleCurrentChange">
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
        </el-card>  
      </el-col> 
      <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="7">
        <el-card class="box-card" shadow="never">
          <div slot="header" class="clearfix">
            <el-tooltip class="item" effect="dark" content="选择指定角色分配菜单" placement="top">
            <span class="role-span">菜单分配</span>
            </el-tooltip>
            <el-button
              :disabled="!showButton"
              :loading="menuLoading"
              icon="el-icon-check"
              size="mini"
              style="float: right; padding: 6px 9px"
              type="primary"
              @click="saveMenu"
            >保存</el-button>
          </div>
          <el-tree
            ref="menu"
            :data="menus"
            :default-checked-keys="menuIds"
            :props="defaultProps"
            show-checkbox
            node-key="id"
            @check="menuChange"
          />
        </el-card>
      </el-col> 
    </el-row>      
  </div>
</template>

<script>
import eForm from './form';
import {Message} from 'element-ui';
import {getRequest, postRequest, postKeyValueRequest, putRequest, deleteRequest } from '../../../utils/api';

export default {
  components: { eForm },
  name: 'Role',
  data() {
    return {
      defaultProps: { children: 'children', label: 'name', isLeaf: 'isLeaf' },
      loading: false,
      isAdd: false,
      role: [],
      roleId: '',
      total: 0,
      page: 1,
      size: 10,
      showButton: false,
      menuLoading: false,
      menus: [], 
      menuIds: [],
      keyword: ''
    }
  },
  mounted() {
    this.initData();
    this.getMenuDatas();
  },
  methods: {
    //加载角色数据
    initData() {
      this.loading = true
      let url = '/learn/system/role/getRole?page=' + this.page + '&size=' + this.size + '&name=' + this.keyword;
      this.getRequest(url).then((resp) => {
        if (resp) {
          this.role = resp.data;
          this.total = resp.total;
        }
      })
    },
    //每页条数变换
    sizeChange(currentSize) {
        this.size = currentSize;
        this.initData();
    },
    //页码
    currentChange(currentPage) {
        this.page = currentPage;
        this.initData();
    },
    //新增角色
    add(){
      this.isAdd = true;
      this.$refs.form.dialog = true
    },
    //编辑角色
    handleEdit(scope) {
      this.$refs.form.dialog = true;
      this.$refs.form.form = scope;
    },
    //删除角色
    handleDelete(scope) {
      this.$confirm('确定删除吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteRequest("/learn/system/role/delRole/"+scope.id).then(res => {
          if (res) {
            this.initData();
          }
        })
      }).catch(e=>e)
    },
    //角色行选中事件
    handleCurrentChange(val) {
      //赋值对象
      const _this = this;
      this.roleId = val.id;
      let url = '/learn/system/role/getRoleMenu?roleId=' + val.id;
      this.getRequest(url).then((res) => {
        //清空菜单的选中       
        this.$refs.menu.setCheckedKeys([]);
        //初始化默认选中的菜单ids
        this.menuIds = [];
        if (res.data) {
          //选中当前角色的菜单id
          res.data.map(item => {
            _this.menuIds.push(item);
          })
        }
      });
      this.showButton = true;
    },
    //保存角色菜单
    saveMenu() {
      this.menuLoading = true;
      let url = '/learn/system/role/saveMenu';
      const parm = { 'roleId': this.roleId, 'menuIds': this.menuIds };
      this.postRequest(url,parm).then((res) => {
        this.menuLoading = false;
        Message.success({message: res.msg})
      });
    },
    //加载菜单树
    getMenuDatas() {
      let url = '/learn/system/getAllMenu'
      this.getRequest(url).then((res) => {
        if (res) {
          res.data.map(item =>{
            if (item.isLeaf === 0) {
              item.isLeaf = false
            } else {
              item.isLeaf = true
            }
          })
          this.menus = res.data
        }
      })
    },
    //选择菜单改变
    menuChange(menu) {
      //判断选中的菜单是否是子节点
      if (!menu.isLeaf) {
        menu.children.map(item =>{
          // 判断是否在 menuIds 中，如果存在则删除，否则添加
          const index = this.menuIds.indexOf(item.id);
          if (index !== -1) {
            this.menuIds.splice(index,1);
          } else {
            this.menuIds.push(item.id);
          }
        })
      } else {
        // 判断是否在 menuIds 中，如果存在则删除，否则添加
        const index = this.menuIds.indexOf(menu.id);
        if (index !== -1) {
          this.menuIds.splice(index,1);
        } else {
          this.menuIds.push(menu.id);
        }
      }
    },
  }
}
</script>

<style scoped>
.el-scrollbar .el-scrollbar__wrap {overflow-x: hidden;}
.el-tree>.el-tree-node{
min-width: 100%;
display:inline-block;
}
</style>