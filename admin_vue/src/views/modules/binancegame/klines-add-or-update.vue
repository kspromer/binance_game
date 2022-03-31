<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="期号：YYYYMMDDHHmm" prop="issueNo">
      <el-input v-model="dataForm.issueNo" placeholder="期号：YYYYMMDDHHmm"></el-input>
    </el-form-item>
    <el-form-item label="交易对" prop="symbol">
      <el-input v-model="dataForm.symbol" placeholder="交易对"></el-input>
    </el-form-item>
    <el-form-item label="收盘价" prop="close">
      <el-input v-model="dataForm.close" placeholder="收盘价"></el-input>
    </el-form-item>
    <el-form-item label="状态：0->下注 1->结算 2->关闭" prop="state">
      <el-input v-model="dataForm.state" placeholder="状态：0->下注 1->结算 2->关闭"></el-input>
    </el-form-item>
    <el-form-item label="结果" prop="result">
      <el-input v-model="dataForm.result" placeholder="结果"></el-input>
    </el-form-item>
    <el-form-item label="开始时间" prop="openTime">
      <el-input v-model="dataForm.openTime" placeholder="开始时间"></el-input>
    </el-form-item>
    <el-form-item label="结束时间" prop="closeTime">
      <el-input v-model="dataForm.closeTime" placeholder="结束时间"></el-input>
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
          issueNo: '',
          symbol: '',
          close: '',
          state: '',
          result: '',
          openTime: '',
          closeTime: '',
          id: 0,
        },
        dataRule: {
          issueNo: [
            { required: true, message: '期号：YYYYMMDDHHmm不能为空', trigger: 'blur' }
          ],
          symbol: [
            { required: true, message: '交易对不能为空', trigger: 'blur' }
          ],
          close: [
            { required: true, message: '收盘价不能为空', trigger: 'blur' }
          ],
          state: [
            { required: true, message: '状态：0->下注 1->结算 2->关闭不能为空', trigger: 'blur' }
          ],
          result: [
            { required: true, message: '结果不能为空', trigger: 'blur' }
          ],
          openTime: [
            { required: true, message: '开始时间不能为空', trigger: 'blur' }
          ],
          closeTime: [
            { required: true, message: '结束时间不能为空', trigger: 'blur' }
          ],
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
              url: this.$http.adornUrl(`/binancegame/klines/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.issueNo = data.klines.issueNo
                this.dataForm.symbol = data.klines.symbol
                this.dataForm.close = data.klines.close
                this.dataForm.state = data.klines.state
                this.dataForm.result = data.klines.result
                this.dataForm.openTime = data.klines.openTime
                this.dataForm.closeTime = data.klines.closeTime
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
              url: this.$http.adornUrl(`/binancegame/klines/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'issueNo': this.dataForm.issueNo,
                'symbol': this.dataForm.symbol,
                'close': this.dataForm.close,
                'state': this.dataForm.state,
                'result': this.dataForm.result,
                'openTime': this.dataForm.openTime,
                'closeTime': this.dataForm.closeTime,
                'id': this.dataForm.id || undefined,
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
