webpackJsonp([47],{mj6C:function(e,a,t){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var r={data:function(){return{visible:!1,dataForm:{id:0,accountId:"",address:"",coinType:"",label:"",createTime:""},dataRule:{accountId:[{required:!0,message:"玩家id不能为空",trigger:"blur"}],address:[{required:!0,message:"地址不能为空",trigger:"blur"}],coinType:[{required:!0,message:"币种类不能为空",trigger:"blur"}],label:[{required:!0,message:"标签不能为空",trigger:"blur"}],createTime:[{required:!0,message:"创建时间不能为空",trigger:"blur"}]}}},methods:{init:function(e){var a=this;this.dataForm.id=e||0,this.visible=!0,this.$nextTick(function(){a.$refs.dataForm.resetFields(),a.dataForm.id&&a.$http({url:a.$http.adornUrl("/binancegame/accountaddress/info/"+a.dataForm.id),method:"get",params:a.$http.adornParams()}).then(function(e){var t=e.data;t&&0===t.code&&(a.dataForm.accountId=t.accountaddress.accountId,a.dataForm.address=t.accountaddress.address,a.dataForm.coinType=t.accountaddress.coinType,a.dataForm.label=t.accountaddress.label,a.dataForm.createTime=t.accountaddress.createTime)})})},dataFormSubmit:function(){var e=this;this.$refs.dataForm.validate(function(a){a&&e.$http({url:e.$http.adornUrl("/binancegame/accountaddress/"+(e.dataForm.id?"update":"save")),method:"post",data:e.$http.adornData({id:e.dataForm.id||void 0,accountId:e.dataForm.accountId,address:e.dataForm.address,coinType:e.dataForm.coinType,label:e.dataForm.label,createTime:e.dataForm.createTime})}).then(function(a){var t=a.data;t&&0===t.code?e.$message({message:"操作成功",type:"success",duration:1500,onClose:function(){e.visible=!1,e.$emit("refreshDataList")}}):e.$message.error(t.msg)})})}}},o={render:function(){var e=this,a=e.$createElement,t=e._self._c||a;return t("el-dialog",{attrs:{title:e.dataForm.id?"修改":"新增","close-on-click-modal":!1,visible:e.visible},on:{"update:visible":function(a){e.visible=a}}},[t("el-form",{ref:"dataForm",attrs:{model:e.dataForm,rules:e.dataRule,"label-width":"80px"},nativeOn:{keyup:function(a){if(!("button"in a)&&e._k(a.keyCode,"enter",13,a.key))return null;e.dataFormSubmit()}}},[t("el-form-item",{attrs:{label:"玩家id",prop:"accountId"}},[t("el-input",{attrs:{placeholder:"玩家id"},model:{value:e.dataForm.accountId,callback:function(a){e.$set(e.dataForm,"accountId",a)},expression:"dataForm.accountId"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"地址",prop:"address"}},[t("el-input",{attrs:{placeholder:"地址"},model:{value:e.dataForm.address,callback:function(a){e.$set(e.dataForm,"address",a)},expression:"dataForm.address"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"币种类",prop:"coinType"}},[t("el-input",{attrs:{placeholder:"币种类"},model:{value:e.dataForm.coinType,callback:function(a){e.$set(e.dataForm,"coinType",a)},expression:"dataForm.coinType"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"标签",prop:"label"}},[t("el-input",{attrs:{placeholder:"标签"},model:{value:e.dataForm.label,callback:function(a){e.$set(e.dataForm,"label",a)},expression:"dataForm.label"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"创建时间",prop:"createTime"}},[t("el-input",{attrs:{placeholder:"创建时间"},model:{value:e.dataForm.createTime,callback:function(a){e.$set(e.dataForm,"createTime",a)},expression:"dataForm.createTime"}})],1)],1),e._v(" "),t("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:function(a){e.visible=!1}}},[e._v("取消")]),e._v(" "),t("el-button",{attrs:{type:"primary"},on:{click:function(a){e.dataFormSubmit()}}},[e._v("确定")])],1)],1)},staticRenderFns:[]},d=t("46Yf")(r,o,!1,null,null,null);a.default=d.exports}});