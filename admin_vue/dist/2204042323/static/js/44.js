webpackJsonp([44],{"tG/H":function(e,a,t){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var r={data:function(){return{visible:!1,dataForm:{id:0,device:"",createTime:"",lastLoginTime:"",username:"",password:"",phone:"",salt:"",roleName:"",inviteCode:"",upper:"",money:""},dataRule:{device:[{required:!0,message:"设备不能为空",trigger:"blur"}],createTime:[{required:!0,message:"创建时间不能为空",trigger:"blur"}],lastLoginTime:[{required:!0,message:"最后登录时间不能为空",trigger:"blur"}],username:[{required:!0,message:"用户名不能为空",trigger:"blur"}],password:[{required:!0,message:"密码不能为空",trigger:"blur"}],phone:[{required:!0,message:"手机号不能为空",trigger:"blur"}],salt:[{required:!0,message:"加盐不能为空",trigger:"blur"}],roleName:[{required:!0,message:"角色不能为空",trigger:"blur"}],inviteCode:[{required:!0,message:"邀请码不能为空",trigger:"blur"}],upper:[{required:!0,message:"上级邀请人不能为空",trigger:"blur"}],money:[{required:!0,message:"余额不能为空",trigger:"blur"}]}}},methods:{init:function(e){var a=this;this.dataForm.id=e||0,this.visible=!0,this.$nextTick(function(){a.$refs.dataForm.resetFields(),a.dataForm.id&&a.$http({url:a.$http.adornUrl("/binancegame/account/info/"+a.dataForm.id),method:"get",params:a.$http.adornParams()}).then(function(e){var t=e.data;t&&0===t.code&&(a.dataForm.device=t.account.device,a.dataForm.createTime=t.account.createTime,a.dataForm.lastLoginTime=t.account.lastLoginTime,a.dataForm.username=t.account.username,a.dataForm.password=t.account.password,a.dataForm.phone=t.account.phone,a.dataForm.salt=t.account.salt,a.dataForm.roleName=t.account.roleName,a.dataForm.inviteCode=t.account.inviteCode,a.dataForm.upper=t.account.upper,a.dataForm.money=t.account.money)})})},dataFormSubmit:function(){var e=this;this.$refs.dataForm.validate(function(a){a&&e.$http({url:e.$http.adornUrl("/binancegame/account/"+(e.dataForm.id?"update":"save")),method:"post",data:e.$http.adornData({id:e.dataForm.id||void 0,device:e.dataForm.device,createTime:e.dataForm.createTime,lastLoginTime:e.dataForm.lastLoginTime,username:e.dataForm.username,password:e.dataForm.password,phone:e.dataForm.phone,salt:e.dataForm.salt,roleName:e.dataForm.roleName,inviteCode:e.dataForm.inviteCode,upper:e.dataForm.upper,money:e.dataForm.money})}).then(function(a){var t=a.data;t&&0===t.code?e.$message({message:"操作成功",type:"success",duration:1500,onClose:function(){e.visible=!1,e.$emit("refreshDataList")}}):e.$message.error(t.msg)})})}}},o={render:function(){var e=this,a=e.$createElement,t=e._self._c||a;return t("el-dialog",{attrs:{title:e.dataForm.id?"修改":"新增","close-on-click-modal":!1,visible:e.visible},on:{"update:visible":function(a){e.visible=a}}},[t("el-form",{ref:"dataForm",attrs:{model:e.dataForm,rules:e.dataRule,"label-width":"80px"},nativeOn:{keyup:function(a){if(!("button"in a)&&e._k(a.keyCode,"enter",13,a.key))return null;e.dataFormSubmit()}}},[t("el-form-item",{attrs:{label:"用户名",prop:"username"}},[t("el-input",{attrs:{placeholder:"用户名"},model:{value:e.dataForm.username,callback:function(a){e.$set(e.dataForm,"username",a)},expression:"dataForm.username"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"密码",prop:"password"}},[t("el-input",{attrs:{placeholder:"密码"},model:{value:e.dataForm.password,callback:function(a){e.$set(e.dataForm,"password",a)},expression:"dataForm.password"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"邀请码",prop:"inviteCode"}},[t("el-input",{attrs:{placeholder:"邀请码"},model:{value:e.dataForm.inviteCode,callback:function(a){e.$set(e.dataForm,"inviteCode",a)},expression:"dataForm.inviteCode"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"余额",prop:"money"}},[t("el-input",{attrs:{placeholder:"余额"},model:{value:e.dataForm.money,callback:function(a){e.$set(e.dataForm,"money",a)},expression:"dataForm.money"}})],1)],1),e._v(" "),t("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:function(a){e.visible=!1}}},[e._v("取消")]),e._v(" "),t("el-button",{attrs:{type:"primary"},on:{click:function(a){e.dataFormSubmit()}}},[e._v("确定")])],1)],1)},staticRenderFns:[]},i=t("46Yf")(r,o,!1,null,null,null);a.default=i.exports}});