<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="玩家id" prop="accountId">
      <el-input v-model="dataForm.accountId" placeholder="玩家id"></el-input>
    </el-form-item>
    <el-form-item label="转账玩家id" prop="toAccountId">
      <el-input v-model="dataForm.toAccountId" placeholder="转账玩家id"></el-input>
    </el-form-item>
    <el-form-item label="用户名" prop="toUsername">
      <el-input v-model="dataForm.toUsername" placeholder="用户名"></el-input>
    </el-form-item>
    <el-form-item label="提现金额" prop="money">
      <el-input v-model="dataForm.money" placeholder="提现金额"></el-input>
    </el-form-item>
    <el-form-item label="备注" prop="note">
      <el-input v-model="dataForm.note" placeholder="备注"></el-input>
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
          toAccountId: '',
          toUsername: '',
          money: '',
          note: '',
          createTime: ''
        },
        dataRule: {
          accountId: [
            { required: true, message: '玩家id不能为空', trigger: 'blur' }
          ],
          toAccountId: [
            { required: true, message: '转账玩家id不能为空', trigger: 'blur' }
          ],
          toUsername: [
            { required: true, message: '用户名不能为空', trigger: 'blur' }
          ],
          money: [
            { required: true, message: '提现金额不能为空', trigger: 'blur' }
          ],
          note: [
            { required: true, message: '备注不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/binancegame/accounttranslation/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.accountId = data.accounttranslation.accountId
                this.dataForm.toAccountId = data.accounttranslation.toAccountId
                this.dataForm.toUsername = data.accounttranslation.toUsername
                this.dataForm.money = data.accounttranslation.money
                this.dataForm.note = data.accounttranslation.note
                this.dataForm.createTime = data.accounttranslation.createTime
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
              url: this.$http.adornUrl(`/binancegame/accounttranslation/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'accountId': this.dataForm.accountId,
                'toAccountId': this.dataForm.toAccountId,
                'toUsername': this.dataForm.toUsername,
                'money': this.dataForm.money,
                'note': this.dataForm.note,
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
