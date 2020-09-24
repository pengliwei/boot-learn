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
      <el-form-item label="路由地址" prop="path">
        <el-input v-model="form.path" style="width: 350px;" />
      </el-form-item>
      <el-form-item label="组件名称" prop="component">
        <el-input v-model="form.component" style="width: 350px;" />
      </el-form-item>
      <!-- <el-tree lazy :props="defaultProps" @node-click="loadNode"></el-tree> -->
      <el-form-item label="上级类目" prop="id">
        <!-- 调用树形下拉框组件 -->
        <SelectTree
          :props="props1"
          :options="optionData"
          :value="id"
          :clearable="isClearable"
          :accordion="isAccordion"
          @getValue="getValue($event)"
        />
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="text" @click="cancel">取消</el-button>
      <!-- <el-button :loading="loading" type="primary" @click="doSubmit">确认</el-button> -->
    </div>
  </el-dialog>
</template>

<script>
import SelectTree from '@/components/util/treeSelect.vue'

export default {
  components: { SelectTree },
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
      isClearable: true, // 可清空（可选）
      isAccordion: true, // 可收起（可选）
      id: 2, // 初始ID（可选）
      props1: {
        // 配置项（必选）
        value: 'id',
        label: 'name',
        children: ()=>[],
      },
      form: {
        loading: false,
        commodity_id: '',
        level: 1,
        nick: '',
        icon_url: '',
        content: '',
      },
    }
  },
  computed: {
      /* 转树形数据 */
      // optionData() {
      //   let url = '/system/menu1'
      //   this.getRequest(url).then((resp) => {
      //     if (resp) {
      //       console.log(resp.data)
      //       let a = resp.data
      //       return a.filter((father) => {
      //         // 循环所有项，并添加children属性
      //         let branchArr = a.filter(
      //           (child) => father.id == child.parentId
      //         ) // 返回每一项的子级数组
      //         branchArr.length > 0 ? (father.children = branchArr) : '' //给父级添加一个children属性，并赋值
      //         return father.parentId == 1 //返回第一层
      //       })
      //     }
      //   })
      // },
    },
  methods: {
    cancel() {
      this.resetForm()
    },
    // 取值
    getValue(value){
      this.id = value
      console.log(this.id);
    } 
  }
}
</script>
<style scoped>
</style>
