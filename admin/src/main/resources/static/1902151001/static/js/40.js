webpackJsonp([40],{X0Sz:function(a,t,e){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r={data:function(){return{visible:!1,dataForm:{id:0,accountId:"",money:"",address:"",coinType:"",label:"",status:1,createTime:""},dataRule:{accountId:[{required:!0,message:"玩家id不能为空",trigger:"blur"}],money:[{required:!0,message:"提现金额不能为空",trigger:"blur"}],address:[{required:!0,message:"地址不能为空",trigger:"blur"}],coinType:[{required:!0,message:"币种类不能为空",trigger:"blur"}],label:[{required:!0,message:"标签不能为空",trigger:"blur"}],status:[{required:!0,message:"提现状态不能为空",trigger:"blur"}],createTime:[{required:!0,message:"创建时间不能为空",trigger:"blur"}]}}},methods:{init:function(a){var t=this;this.dataForm.id=a||0,this.visible=!0,this.$nextTick(function(){t.$refs.dataForm.resetFields(),t.dataForm.id&&t.$http({url:t.$http.adornUrl("/binancegame/accountwithdrawal/info/"+t.dataForm.id),method:"get",params:t.$http.adornParams()}).then(function(a){var e=a.data;e&&0===e.code&&(t.dataForm.accountId=e.accountWithdrawal.accountId,t.dataForm.money=e.accountWithdrawal.money,t.dataForm.address=e.accountWithdrawal.address,t.dataForm.coinType=e.accountWithdrawal.coinType,t.dataForm.label=e.accountWithdrawal.label,t.dataForm.status=e.accountWithdrawal.status,t.dataForm.createTime=e.accountWithdrawal.createTime)})})},dataFormSubmit:function(){var a=this;this.$refs.dataForm.validate(function(t){t&&a.$http({url:a.$http.adornUrl("/binancegame/accountwithdrawal/"+(a.dataForm.id,"audit")),method:"post",data:a.$http.adornData({id:a.dataForm.id||void 0,accountId:a.dataForm.accountId,money:a.dataForm.money,address:a.dataForm.address,coinType:a.dataForm.coinType,label:a.dataForm.label,status:a.dataForm.status,createTime:a.dataForm.createTime})}).then(function(t){var e=t.data;e&&0===e.code?a.$message({message:"操作成功",type:"success",duration:1500,onClose:function(){a.visible=!1,a.$emit("refreshDataList")}}):a.$message.error(e.msg)})})}}},o={render:function(){var a=this,t=a.$createElement,e=a._self._c||t;return e("el-dialog",{attrs:{title:a.dataForm.id?"修改":"新增","close-on-click-modal":!1,visible:a.visible},on:{"update:visible":function(t){a.visible=t}}},[e("el-form",{ref:"dataForm",attrs:{model:a.dataForm,rules:a.dataRule,"label-width":"80px"},nativeOn:{keyup:function(t){if(!("button"in t)&&a._k(t.keyCode,"enter",13,t.key))return null;a.dataFormSubmit()}}},[e("el-form-item",{attrs:{label:"玩家id",prop:"accountId"}},[e("el-input",{attrs:{placeholder:"玩家id"},model:{value:a.dataForm.accountId,callback:function(t){a.$set(a.dataForm,"accountId",t)},expression:"dataForm.accountId"}})],1),a._v(" "),e("el-form-item",{attrs:{label:"提现金额",prop:"money"}},[e("el-input",{attrs:{placeholder:"提现金额"},model:{value:a.dataForm.money,callback:function(t){a.$set(a.dataForm,"money",t)},expression:"dataForm.money"}})],1),a._v(" "),e("el-form-item",{attrs:{label:"地址",prop:"address"}},[e("el-input",{attrs:{placeholder:"地址"},model:{value:a.dataForm.address,callback:function(t){a.$set(a.dataForm,"address",t)},expression:"dataForm.address"}})],1),a._v(" "),e("el-form-item",{attrs:{label:"币种类",prop:"coinType"}},[e("el-input",{attrs:{placeholder:"币种类"},model:{value:a.dataForm.coinType,callback:function(t){a.$set(a.dataForm,"coinType",t)},expression:"dataForm.coinType"}})],1),a._v(" "),e("el-form-item",{attrs:{label:"提现状态",prop:"status"}},[e("el-radio",{attrs:{label:"1"},model:{value:a.dataForm.status,callback:function(t){a.$set(a.dataForm,"status",t)},expression:"dataForm.status"}},[a._v("审核通过")]),a._v(" "),e("el-radio",{attrs:{label:"2"},model:{value:a.dataForm.status,callback:function(t){a.$set(a.dataForm,"status",t)},expression:"dataForm.status"}},[a._v("审核拒绝")])],1)],1),a._v(" "),e("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[e("el-button",{on:{click:function(t){a.visible=!1}}},[a._v("取消")]),a._v(" "),e("el-button",{attrs:{type:"primary"},on:{click:function(t){a.dataFormSubmit()}}},[a._v("确定")])],1)],1)},staticRenderFns:[]},d=e("46Yf")(r,o,!1,null,null,null);t.default=d.exports}});