<template>
  <el-dialog
    :title="!dataForm.id ? '新增充值地址' : '新增充值地址'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="私钥" prop="privateKey">
      <el-input v-model="dataForm.privateKey" placeholder="私钥"></el-input>
    </el-form-item>
    <el-form-item label="生成数量" prop="walletSerialNumber">
      <el-input v-model="dataForm.walletSerialNumber" placeholder="生成数量"></el-input>
    </el-form-item>
    <el-form-item label="币种类" prop="coinType">
      <el-radio-group v-model="dataForm.coinType">
        <el-radio-button label="BEP20">BEP20</el-radio-button>
        <el-radio-button label="TRC20">TRC20</el-radio-button>
      </el-radio-group>
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
          privateKey: '',
          walletSerialNumber: '',
          coinType: '',
          createTime: ''
        },
        dataRule: {
          accountId: [
            { required: true, message: '玩家id不能为空', trigger: 'blur' }
          ],
          address: [
            { required: true, message: '地址不能为空', trigger: 'blur' }
          ],
          privateKey: [
            { required: true, message: '私钥不能为空', trigger: 'blur' }
          ],
          walletSerialNumber: [
            { required: true, message: '钱包序号不能为空', trigger: 'blur' }
          ],
          coinType: [
            { required: true, message: '币种类不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/binancegame/accountrechargeaddress/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.accountId = data.accountrechargeaddress.accountId
                this.dataForm.address = data.accountrechargeaddress.address
                this.dataForm.privateKey = data.accountrechargeaddress.privateKey
                this.dataForm.walletSerialNumber = data.accountrechargeaddress.walletSerialNumber
                this.dataForm.coinType = data.accountrechargeaddress.coinType
                this.dataForm.createTime = data.accountrechargeaddress.createTime
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
              url: this.$http.adornUrl(`/binancegame/accountrechargeaddress/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'accountId': this.dataForm.accountId,
                'address': this.dataForm.address,
                'privateKey': this.dataForm.privateKey,
                'walletSerialNumber': this.dataForm.walletSerialNumber,
                'coinType': this.dataForm.coinType,
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
