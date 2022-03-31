<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="类型" prop="type">
      <el-input v-model="dataForm.type" placeholder="类型"></el-input>
    </el-form-item>
    <el-form-item label="详情" prop="description">
      <el-input v-model="dataForm.description" placeholder="详情"></el-input>
    </el-form-item>
    <el-form-item label="变动金额" prop="amount">
      <el-input v-model="dataForm.amount" placeholder="变动金额"></el-input>
    </el-form-item>
    <el-form-item label="用户id" prop="accountId">
      <el-input v-model="dataForm.accountId" placeholder="用户id"></el-input>
    </el-form-item>
    <el-form-item label="变动前" prop="beforeMoney">
      <el-input v-model="dataForm.beforeMoney" placeholder="变动前"></el-input>
    </el-form-item>
    <el-form-item label="变动后" prop="afterMoney">
      <el-input v-model="dataForm.afterMoney" placeholder="变动后"></el-input>
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
          createTime: '',
          type: '',
          description: '',
          amount: '',
          accountId: '',
          beforeMoney: '',
          afterMoney: ''
        },
        dataRule: {
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          type: [
            { required: true, message: '类型不能为空', trigger: 'blur' }
          ],
          description: [
            { required: true, message: '详情不能为空', trigger: 'blur' }
          ],
          amount: [
            { required: true, message: '变动金额不能为空', trigger: 'blur' }
          ],
          accountId: [
            { required: true, message: '用户id不能为空', trigger: 'blur' }
          ],
          beforeMoney: [
            { required: true, message: '变动前不能为空', trigger: 'blur' }
          ],
          afterMoney: [
            { required: true, message: '变动后不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/binancegame/moneychange/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.createTime = data.moneychange.createTime
                this.dataForm.type = data.moneychange.type
                this.dataForm.description = data.moneychange.description
                this.dataForm.amount = data.moneychange.amount
                this.dataForm.accountId = data.moneychange.accountId
                this.dataForm.beforeMoney = data.moneychange.beforeMoney
                this.dataForm.afterMoney = data.moneychange.afterMoney
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
              url: this.$http.adornUrl(`/binancegame/moneychange/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'createTime': this.dataForm.createTime,
                'type': this.dataForm.type,
                'description': this.dataForm.description,
                'amount': this.dataForm.amount,
                'accountId': this.dataForm.accountId,
                'beforeMoney': this.dataForm.beforeMoney,
                'afterMoney': this.dataForm.afterMoney
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
