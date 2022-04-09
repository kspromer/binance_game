webpackJsonp([25],{INS3:function(t,a,e){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var r=e("E4LH"),o={data:function(){var t=this;return{visible:!1,roleList:[],dataForm:{id:0,userName:"",password:"",comfirmPassword:"",salt:"",email:"",mobile:"",roleIdList:[],status:1,storeId:""},dataRule:{userName:[{required:!0,message:"用户名不能为空",trigger:"blur"}],password:[{validator:function(a,e,r){t.dataForm.id||/\S/.test(e)?r():r(new Error("密码不能为空"))},trigger:"blur"}],comfirmPassword:[{validator:function(a,e,r){t.dataForm.id||/\S/.test(e)?t.dataForm.password!==e?r(new Error("确认密码与密码输入不一致")):r():r(new Error("确认密码不能为空"))},trigger:"blur"}],email:[{required:!0,message:"邮箱不能为空",trigger:"blur"},{validator:function(t,a,e){Object(r.a)(a)?e():e(new Error("邮箱格式错误"))},trigger:"blur"}],mobile:[{required:!0,message:"手机号不能为空",trigger:"blur"},{validator:function(t,a,e){Object(r.b)(a)?e():e(new Error("手机号格式错误"))},trigger:"blur"}]}}},methods:{disOrModinit:function(t){var a=this;this.dataForm.storeId=t||0,this.$http({url:this.$http.adornUrl("/sys/role/select"),method:"get",params:this.$http.adornParams()}).then(function(t){var e=t.data;a.roleList=e&&0===e.code?e.list:[]}).then(function(){a.visible=!0,a.$nextTick(function(){a.$refs.dataForm.resetFields()})}).then(function(){a.dataForm.storeId&&a.$http({url:a.$http.adornUrl("/sys/user/infoByStoreId/"+a.dataForm.storeId),method:"get",params:a.$http.adornParams()}).then(function(t){var e=t.data;e&&0===e.code&&null!=e.user&&(a.dataForm.userName=e.user.username,a.dataForm.salt=e.user.salt,a.dataForm.email=e.user.email,a.dataForm.mobile=e.user.mobile,a.dataForm.roleIdList=e.user.roleIdList,a.dataForm.status=e.user.status)})})},init:function(t){var a=this;this.dataForm.id=t||0,this.$http({url:this.$http.adornUrl("/sys/role/select"),method:"get",params:this.$http.adornParams()}).then(function(t){var e=t.data;a.roleList=e&&0===e.code?e.list:[]}).then(function(){a.visible=!0,a.$nextTick(function(){a.$refs.dataForm.resetFields()})}).then(function(){a.dataForm.id&&a.$http({url:a.$http.adornUrl("/sys/user/info/"+a.dataForm.id),method:"get",params:a.$http.adornParams()}).then(function(t){var e=t.data;e&&0===e.code&&(a.dataForm.userName=e.user.username,a.dataForm.salt=e.user.salt,a.dataForm.email=e.user.email,a.dataForm.mobile=e.user.mobile,a.dataForm.roleIdList=e.user.roleIdList,a.dataForm.status=e.user.status)})})},dataFormSubmit:function(){var t=this;this.$refs.dataForm.validate(function(a){a&&t.$http({url:t.$http.adornUrl("/sys/user/"+(t.dataForm.id?"update":"save")),method:"post",data:t.$http.adornData({userId:t.dataForm.id||void 0,username:t.dataForm.userName,password:t.dataForm.password,salt:t.dataForm.salt,email:t.dataForm.email,mobile:t.dataForm.mobile,status:t.dataForm.status,storeId:t.dataForm.storeId,roleIdList:t.dataForm.roleIdList})}).then(function(a){var e=a.data;e&&0===e.code?t.$message({message:"操作成功",type:"success",duration:1500,onClose:function(){t.visible=!1,t.$emit("refreshDataList")}}):t.$message.error(e.msg)})})}}},s={render:function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("el-dialog",{attrs:{title:t.dataForm.id?"修改":"新增","close-on-click-modal":!1,visible:t.visible},on:{"update:visible":function(a){t.visible=a}}},[e("el-form",{ref:"dataForm",attrs:{model:t.dataForm,rules:t.dataRule,"label-width":"80px"},nativeOn:{keyup:function(a){if(!("button"in a)&&t._k(a.keyCode,"enter",13,a.key))return null;t.dataFormSubmit()}}},[e("el-form-item",{attrs:{label:"用户名",prop:"userName"}},[e("el-input",{attrs:{placeholder:"登录帐号"},model:{value:t.dataForm.userName,callback:function(a){t.$set(t.dataForm,"userName",a)},expression:"dataForm.userName"}})],1),t._v(" "),e("el-form-item",{class:{"is-required":!t.dataForm.id},attrs:{label:"密码",prop:"password"}},[e("el-input",{attrs:{type:"password",placeholder:"密码"},model:{value:t.dataForm.password,callback:function(a){t.$set(t.dataForm,"password",a)},expression:"dataForm.password"}})],1),t._v(" "),e("el-form-item",{class:{"is-required":!t.dataForm.id},attrs:{label:"确认密码",prop:"comfirmPassword"}},[e("el-input",{attrs:{type:"password",placeholder:"确认密码"},model:{value:t.dataForm.comfirmPassword,callback:function(a){t.$set(t.dataForm,"comfirmPassword",a)},expression:"dataForm.comfirmPassword"}})],1),t._v(" "),e("el-form-item",{attrs:{label:"邮箱",prop:"email"}},[e("el-input",{attrs:{placeholder:"邮箱"},model:{value:t.dataForm.email,callback:function(a){t.$set(t.dataForm,"email",a)},expression:"dataForm.email"}})],1),t._v(" "),e("el-form-item",{attrs:{label:"手机号",prop:"mobile"}},[e("el-input",{attrs:{placeholder:"手机号"},model:{value:t.dataForm.mobile,callback:function(a){t.$set(t.dataForm,"mobile",a)},expression:"dataForm.mobile"}})],1),t._v(" "),e("el-form-item",{attrs:{label:"角色",size:"mini",prop:"roleIdList"}},[e("el-checkbox-group",{model:{value:t.dataForm.roleIdList,callback:function(a){t.$set(t.dataForm,"roleIdList",a)},expression:"dataForm.roleIdList"}},t._l(t.roleList,function(a){return e("el-checkbox",{key:a.roleId,attrs:{label:a.roleId}},[t._v(t._s(a.roleName))])}))],1),t._v(" "),e("el-form-item",{attrs:{label:"状态",size:"mini",prop:"status"}},[e("el-radio-group",{model:{value:t.dataForm.status,callback:function(a){t.$set(t.dataForm,"status",a)},expression:"dataForm.status"}},[e("el-radio",{attrs:{label:0}},[t._v("禁用")]),t._v(" "),e("el-radio",{attrs:{label:1}},[t._v("正常")])],1)],1)],1),t._v(" "),e("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[e("el-button",{on:{click:function(a){t.visible=!1}}},[t._v("取消")]),t._v(" "),e("el-button",{attrs:{type:"primary"},on:{click:function(a){t.dataFormSubmit()}}},[t._v("确定")])],1)],1)},staticRenderFns:[]},i=e("46Yf")(o,s,!1,null,null,null);a.default=i.exports}});