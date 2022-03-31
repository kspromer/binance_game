<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="玩家id" prop="accountId">
      <el-input v-model="dataForm.accountId" placeholder="玩家id"></el-input>
    </el-form-item>
    <el-form-item label="地址" prop="address">
      <el-input v-model="dataForm.address" placeholder="地址"></el-input>
    </el-form-item>
    <el-form-item label="币种类" prop="coinType">
      <el-input v-model="dataForm.coinType" placeholder="币种类"></el-input>
    </el-form-item>
    <el-form-item label="标签" prop="label">
      <el-input v-model="dataForm.label" placeholder="标签"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          accountId: '',
          address: '',
          coinType: '',
          label: '',
          createTime: ''
        },
        dataRule: {
          accountId: [
            { required: true, message: '玩家id不能为空', trigger: 'blur' }
          ],
          address: [
            { required: true, message: '地址不能为空', trigger: 'blur' }
          ],
          coinType: [
            { required: true, message: '币种类不能为空', trigger: 'blur' }
          ],
          label: [
            { required: true, message: '标签不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/binancegame/accountaddress/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.accountId = data.accountaddress.accountId
                this.dataForm.address = data.accountaddress.address
                this.dataForm.coinType = data.accountaddress.coinType
                this.dataForm.label = data.accountaddress.label
                this.dataForm.createTime = data.accountaddress.createTime
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/binancegame/accountaddress/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'accountId': this.dataForm.accountId,
                'address': this.dataForm.address,
                'coinType': this.dataForm.coinType,
                'label': this.dataForm.label,
                'createTime': this.dataForm.createTime
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
