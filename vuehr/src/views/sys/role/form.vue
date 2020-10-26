<template>
  <el-dialog
    :append-to-body="true"
    :visible.sync="dialog"
    :title="isAdd ? '新增角色' : '编辑角色'"
    width="500px"
  >
    <el-form ref="form" :model="form" size="small" label-width="80px">
      <el-form-item label="角色名称" prop="name">
        <el-input v-model="form.name" style="width: 350px;" />
      </el-form-item>
      <el-form-item label="角色描述" prop="description">
        <el-input v-model="form.description" style="width: 350px;" />
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="text" @click="cancel">取消</el-button>
      <el-button :loading="loading" type="primary" @click="doSubmit">确认</el-button>
    </div>
  </el-dialog>
</template>

<script>

export default {
  components: {},
  props: {
    isAdd: {
      type: Boolean,
      required: true,
    },
    loading: 'false'
  },
  data() {
    return {
      dialog: false,
      role: [],
      form: {
        id: '',
        name: '',
        description: ''  
      },
    }
  },
  methods: {
    cancel() {
      this.resetForm();
       this.dialog = false;
    },
    doSubmit() {
        this.postRequest('/system/role/addRole',this.form).then(res => {
          if(res){
            this.resetForm();
            this.dialog = false;
            this.$parent.initData();
          }
        })  
    },
    resetForm() {
        this.$refs['form'].resetFields()
    }
  }
}
</script>
<style scoped>
</style>
