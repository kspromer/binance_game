<template>
  <el-dialog
    :title="!dataForm.id ? '加分' : '加分'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">

<!--    <el-form-item label="用户名" prop="username">-->
<!--      <el-input v-model="dataForm.username" placeholder="用户名"></el-input>-->
<!--    </el-form-item>-->
<!--    <el-form-item label="密码" prop="password">-->
<!--      <el-input v-model="dataForm.password" placeholder="密码"></el-input>-->
<!--    </el-form-item>-->

<!--    <el-form-item label="邀请码" prop="inviteCode">-->
<!--      <el-input v-model="dataForm.inviteCode" placeholder="邀请码"></el-input>-->
<!--    </el-form-item>-->

    <el-form-item label="余额" prop="addMoney">
      <el-input v-model="dataForm.addMoney" placeholder="余额"></el-input>
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
          device: '',
          createTime: '',
          lastLoginTime: '',
          username: '',
          password: '',
          phone: '',
          salt: '',
          roleName: '',
          inviteCode: '',
          upper: '',
          money: '',
          addMoney: '',
        },
        dataRule: {
          device: [
            { required: true, message: '设备不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          lastLoginTime: [
            { required: true, message: '最后登录时间不能为空', trigger: 'blur' }
          ],
          username: [
            { required: true, message: '用户名不能为空', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '密码不能为空', trigger: 'blur' }
          ],
          phone: [
            { required: true, message: '手机号不能为空', trigger: 'blur' }
          ],
          salt: [
            { required: true, message: '加盐不能为空', trigger: 'blur' }
          ],
          roleName: [
            { required: true, message: '角色不能为空', trigger: 'blur' }
          ],
          inviteCode: [
            { required: true, message: '邀请码不能为空', trigger: 'blur' }
          ],
          upper: [
            { required: true, message: '上级邀请人不能为空', trigger: 'blur' }
          ],
          money: [
            { required: true, message: '余额不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/binancegame/account/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.device = data.account.device
                this.dataForm.createTime = data.account.createTime
                this.dataForm.lastLoginTime = data.account.lastLoginTime
                this.dataForm.username = data.account.username
                this.dataForm.password = data.account.password
                this.dataForm.phone = data.account.phone
                this.dataForm.salt = data.account.salt
                this.dataForm.roleName = data.account.roleName
                this.dataForm.inviteCode = data.account.inviteCode
                this.dataForm.upper = data.account.upper
                this.dataForm.money = data.account.money
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
              url: this.$http.adornUrl(`/binancegame/account/${!this.dataForm.id ? 'addMoney' : 'addMoney'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'addMoney': this.dataForm.addMoney
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
