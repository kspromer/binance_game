webpackJsonp([40],{2053:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r={data:function(){return{visible:!1,dataForm:{uuid:0,code:"",expireTime:""},dataRule:{code:[{required:!0,message:"验证码不能为空",trigger:"blur"}],expireTime:[{required:!0,message:"过期时间不能为空",trigger:"blur"}]}}},methods:{init:function(e){var t=this;this.dataForm.uuid=e||0,this.visible=!0,this.$nextTick(function(){t.$refs.dataForm.resetFields(),t.dataForm.uuid&&t.$http({url:t.$http.adornUrl("/binancegame/accountcaptcha/info/"+t.dataForm.uuid),method:"get",params:t.$http.adornParams()}).then(function(e){var a=e.data;a&&0===a.code&&(t.dataForm.code=a.accountcaptcha.code,t.dataForm.expireTime=a.accountcaptcha.expireTime)})})},dataFormSubmit:function(){var e=this;this.$refs.dataForm.validate(function(t){t&&e.$http({url:e.$http.adornUrl("/binancegame/accountcaptcha/"+(e.dataForm.uuid?"update":"save")),method:"post",data:e.$http.adornData({uuid:e.dataForm.uuid||void 0,code:e.dataForm.code,expireTime:e.dataForm.expireTime})}).then(function(t){var a=t.data;a&&0===a.code?e.$message({message:"操作成功",type:"success",duration:1500,onClose:function(){e.visible=!1,e.$emit("refreshDataList")}}):e.$message.error(a.msg)})})}}},i={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-dialog",{attrs:{title:e.dataForm.id?"修改":"新增","close-on-click-modal":!1,visible:e.visible},on:{"update:visible":function(t){e.visible=t}}},[a("el-form",{ref:"dataForm",attrs:{model:e.dataForm,rules:e.dataRule,"label-width":"80px"},nativeOn:{keyup:function(t){if(!("button"in t)&&e._k(t.keyCode,"enter",13,t.key))return null;e.dataFormSubmit()}}},[a("el-form-item",{attrs:{label:"验证码",prop:"code"}},[a("el-input",{attrs:{placeholder:"验证码"},model:{value:e.dataForm.code,callback:function(t){e.$set(e.dataForm,"code",t)},expression:"dataForm.code"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"过期时间",prop:"expireTime"}},[a("el-input",{attrs:{placeholder:"过期时间"},model:{value:e.dataForm.expireTime,callback:function(t){e.$set(e.dataForm,"expireTime",t)},expression:"dataForm.expireTime"}})],1)],1),e._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.visible=!1}}},[e._v("取消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.dataFormSubmit()}}},[e._v("确定")])],1)],1)},staticRenderFns:[]},o=a("46Yf")(r,i,!1,null,null,null);t.default=o.exports}});