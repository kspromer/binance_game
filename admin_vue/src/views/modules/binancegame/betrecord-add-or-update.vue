<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="玩家id" prop="accountId">
      <el-input v-model="dataForm.accountId" placeholder="玩家id"></el-input>
    </el-form-item>
    <el-form-item label="金额" prop="money">
      <el-input v-model="dataForm.money" placeholder="金额"></el-input>
    </el-form-item>
    <el-form-item label="点数" prop="point">
      <el-input v-model="dataForm.point" placeholder="点数"></el-input>
    </el-form-item>
    <el-form-item label="期号" prop="issueNo">
      <el-input v-model="dataForm.issueNo" placeholder="期号"></el-input>
    </el-form-item>
    <el-form-item label="结果" prop="result">
      <el-input v-model="dataForm.result" placeholder="结果"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="状态0->投注未结算1->投注已结算" prop="state">
      <el-input v-model="dataForm.state" placeholder="状态0->投注未结算1->投注已结算"></el-input>
    </el-form-item>
    <el-form-item label="交易对" prop="symbol">
      <el-input v-model="dataForm.symbol" placeholder="交易对"></el-input>
    </el-form-item>
    <el-form-item label="赔率" prop="odds">
      <el-input v-model="dataForm.odds" placeholder="赔率"></el-input>
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
          money: '',
          point: '',
          issueNo: '',
          result: '',
          createTime: '',
          state: '',
          symbol: '',
          odds: ''
        },
        dataRule: {
          accountId: [
            { required: true, message: '玩家id不能为空', trigger: 'blur' }
          ],
          money: [
            { required: true, message: '金额不能为空', trigger: 'blur' }
          ],
          point: [
            { required: true, message: '点数不能为空', trigger: 'blur' }
          ],
          issueNo: [
            { required: true, message: '期号不能为空', trigger: 'blur' }
          ],
          result: [
            { required: true, message: '结果不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          state: [
            { required: true, message: '状态0->投注未结算1->投注已结算不能为空', trigger: 'blur' }
          ],
          symbol: [
            { required: true, message: '交易对不能为空', trigger: 'blur' }
          ],
          odds: [
            { required: true, message: '赔率不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/binancegame/betrecord/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.accountId = data.betrecord.accountId
                this.dataForm.money = data.betrecord.money
                this.dataForm.point = data.betrecord.point
                this.dataForm.issueNo = data.betrecord.issueNo
                this.dataForm.result = data.betrecord.result
                this.dataForm.createTime = data.betrecord.createTime
                this.dataForm.state = data.betrecord.state
                this.dataForm.symbol = data.betrecord.symbol
                this.dataForm.odds = data.betrecord.odds
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
              url: this.$http.adornUrl(`/binancegame/betrecord/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'accountId': this.dataForm.accountId,
                'money': this.dataForm.money,
                'point': this.dataForm.point,
                'issueNo': this.dataForm.issueNo,
                'result': this.dataForm.result,
                'createTime': this.dataForm.createTime,
                'state': this.dataForm.state,
                'symbol': this.dataForm.symbol,
                'odds': this.dataForm.odds
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
