webpackJsonp([37],{pJ5h:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r={data:function(){return{visible:!1,dataForm:{id:0,accountId:"",money:"",point:"",issueNo:"",result:"",createTime:"",state:"",symbol:"",odds:""},dataRule:{accountId:[{required:!0,message:"玩家id不能为空",trigger:"blur"}],money:[{required:!0,message:"金额不能为空",trigger:"blur"}],point:[{required:!0,message:"点数不能为空",trigger:"blur"}],issueNo:[{required:!0,message:"期号不能为空",trigger:"blur"}],result:[{required:!0,message:"结果不能为空",trigger:"blur"}],createTime:[{required:!0,message:"创建时间不能为空",trigger:"blur"}],state:[{required:!0,message:"状态0->投注未结算1->投注已结算不能为空",trigger:"blur"}],symbol:[{required:!0,message:"交易对不能为空",trigger:"blur"}],odds:[{required:!0,message:"赔率不能为空",trigger:"blur"}]}}},methods:{init:function(e){var t=this;this.dataForm.id=e||0,this.visible=!0,this.$nextTick(function(){t.$refs.dataForm.resetFields(),t.dataForm.id&&t.$http({url:t.$http.adornUrl("/binancegame/betrecord/info/"+t.dataForm.id),method:"get",params:t.$http.adornParams()}).then(function(e){var a=e.data;a&&0===a.code&&(t.dataForm.accountId=a.betrecord.accountId,t.dataForm.money=a.betrecord.money,t.dataForm.point=a.betrecord.point,t.dataForm.issueNo=a.betrecord.issueNo,t.dataForm.result=a.betrecord.result,t.dataForm.createTime=a.betrecord.createTime,t.dataForm.state=a.betrecord.state,t.dataForm.symbol=a.betrecord.symbol,t.dataForm.odds=a.betrecord.odds)})})},dataFormSubmit:function(){var e=this;this.$refs.dataForm.validate(function(t){t&&e.$http({url:e.$http.adornUrl("/binancegame/betrecord/"+(e.dataForm.id?"update":"save")),method:"post",data:e.$http.adornData({id:e.dataForm.id||void 0,accountId:e.dataForm.accountId,money:e.dataForm.money,point:e.dataForm.point,issueNo:e.dataForm.issueNo,result:e.dataForm.result,createTime:e.dataForm.createTime,state:e.dataForm.state,symbol:e.dataForm.symbol,odds:e.dataForm.odds})}).then(function(t){var a=t.data;a&&0===a.code?e.$message({message:"操作成功",type:"success",duration:1500,onClose:function(){e.visible=!1,e.$emit("refreshDataList")}}):e.$message.error(a.msg)})})}}},o={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-dialog",{attrs:{title:e.dataForm.id?"修改":"新增","close-on-click-modal":!1,visible:e.visible},on:{"update:visible":function(t){e.visible=t}}},[a("el-form",{ref:"dataForm",attrs:{model:e.dataForm,rules:e.dataRule,"label-width":"80px"},nativeOn:{keyup:function(t){if(!("button"in t)&&e._k(t.keyCode,"enter",13,t.key))return null;e.dataFormSubmit()}}},[a("el-form-item",{attrs:{label:"玩家id",prop:"accountId"}},[a("el-input",{attrs:{placeholder:"玩家id"},model:{value:e.dataForm.accountId,callback:function(t){e.$set(e.dataForm,"accountId",t)},expression:"dataForm.accountId"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"金额",prop:"money"}},[a("el-input",{attrs:{placeholder:"金额"},model:{value:e.dataForm.money,callback:function(t){e.$set(e.dataForm,"money",t)},expression:"dataForm.money"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"点数",prop:"point"}},[a("el-input",{attrs:{placeholder:"点数"},model:{value:e.dataForm.point,callback:function(t){e.$set(e.dataForm,"point",t)},expression:"dataForm.point"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"期号",prop:"issueNo"}},[a("el-input",{attrs:{placeholder:"期号"},model:{value:e.dataForm.issueNo,callback:function(t){e.$set(e.dataForm,"issueNo",t)},expression:"dataForm.issueNo"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"结果",prop:"result"}},[a("el-input",{attrs:{placeholder:"结果"},model:{value:e.dataForm.result,callback:function(t){e.$set(e.dataForm,"result",t)},expression:"dataForm.result"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"创建时间",prop:"createTime"}},[a("el-input",{attrs:{placeholder:"创建时间"},model:{value:e.dataForm.createTime,callback:function(t){e.$set(e.dataForm,"createTime",t)},expression:"dataForm.createTime"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"状态0->投注未结算1->投注已结算",prop:"state"}},[a("el-input",{attrs:{placeholder:"状态0->投注未结算1->投注已结算"},model:{value:e.dataForm.state,callback:function(t){e.$set(e.dataForm,"state",t)},expression:"dataForm.state"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"交易对",prop:"symbol"}},[a("el-input",{attrs:{placeholder:"交易对"},model:{value:e.dataForm.symbol,callback:function(t){e.$set(e.dataForm,"symbol",t)},expression:"dataForm.symbol"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"赔率",prop:"odds"}},[a("el-input",{attrs:{placeholder:"赔率"},model:{value:e.dataForm.odds,callback:function(t){e.$set(e.dataForm,"odds",t)},expression:"dataForm.odds"}})],1)],1),e._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.visible=!1}}},[e._v("取消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.dataFormSubmit()}}},[e._v("确定")])],1)],1)},staticRenderFns:[]},s=a("46Yf")(r,o,!1,null,null,null);t.default=s.exports}});