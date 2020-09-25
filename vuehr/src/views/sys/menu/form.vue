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
        <!-- <SelectTree
          :props="props1"
          :options="optionData"
          :value="id"
          :clearable="isClearable"
          :accordion="isAccordion"
          @getValue="getValue($event)"
        /> -->
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
      form: {
        loading: false,
        content: '',
      },
    }
  },

  methods: {
    cancel() {
      this.resetForm()
    },
  }
}
</script>
<style scoped>
</style>
