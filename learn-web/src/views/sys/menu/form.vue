<template>
  <el-dialog
    :append-to-body="true"
    :visible.sync="dialog"
    :title="isAdd ? '新增菜单' : '编辑菜单'"
    width="500px"
  >
    <el-form ref="form" :model="form" size="small" label-width="80px">
      <el-form-item label="菜单名称" prop="name">
        <el-input v-model="form.name" style="width: 350px;" />
      </el-form-item>
      <el-form-item label="菜单url" prop="url">
        <el-input v-model="form.url" style="width: 350px;" />
      </el-form-item>
      <el-form-item label="路由地址" prop="path">
        <el-input v-model="form.path" style="width: 350px;" />
      </el-form-item>
      <el-form-item label="组件名称" prop="component">
        <el-input v-model="form.component" style="width: 350px;" />
      </el-form-item>
      <!-- <el-tree lazy :props="defaultProps" @node-click="loadNode"></el-tree> -->
      <el-form-item label="上级菜单" prop="id">
        <treeselect
          style="width: 350px;"
          v-model="form.parentId"
          :options="menuOptions"
          :normalizer="normalizer"
          :show-count="true"
          placeholder="选择上级菜单"
        />
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="text" @click="cancel">取消</el-button>
      <el-button type="primary" @click="doSubmit">确认</el-button>
    </div>
  </el-dialog>
</template>

<script>
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  components: { Treeselect },
  props: {
    isAdd: {
      type: Boolean,
      required: true,
    },
  },
  data() {
    return {
      dialog: false,
      menu: [],
      form: { },
      // 菜单树选项
      menuOptions: [],
    }
  },
  mounted() {
    this.getTreeselect();
  },
  methods: {
    /** 查询菜单下拉树结构 */
    getTreeselect() {
      let url = '/learn/system/getAllMenu'
      this.getRequest(url).then((res) => {
        if (res) {
          this.menuOptions = [];
          const menu = {id: 0, name: '主菜单', chirldren: [] };
          menu.children = this.handleTree(res.data, 'id');
          this.menuOptions.push(menu);
        }
      })
    },
    cancel() {
      this.resetForm();
      this.dialog = false;
    },
    // 保存
    doSubmit() {
      this.postRequest('/learn/system/menu/addMenu',this.form).then(res => {
        if(res){
          this.resetForm();
          this.dialog = false;
          this.$parent.initData();
        }
      }) 
    },
    /** 转换菜单数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.id,
        label: node.name,
        children: node.children
      };
    },
    /**
     * 构造树型结构数据
     * @param {*} data 数据源
     * @param {*} id id字段 默认 'id'
     * @param {*} parentId 父节点字段 默认 'parentId'
     * @param {*} children 孩子节点字段 默认 'children'
     * @param {*} rootId 根Id 默认 0
     */
    handleTree(data, id, parentId, children, rootId) {
      id = id || 'id';
      parentId = parentId || 'parentId';
      children = children || 'children';
      rootId = rootId || Math.min.apply(Math, data.map(item => { return item[parentId] })) || 0;
      //对源数据深度克隆
      const cloneData = JSON.parse(JSON.stringify(data));
      //循环所有项
      const treeData = cloneData.filter(father => {
        let branchArr = cloneData.filter(child => {
          //返回每一项的子级数组
          return father[id] === child[parentId]
        });
        branchArr.length > 0 ? father.children = branchArr : '';
        //返回第一层
        return father[parentId] === rootId;
      });
      return treeData != '' ? treeData : data;
    },
    resetForm() {
      this.$refs['form'].resetFields()
    }
  }  
}
</script>
<style scoped>
</style>
