webpackJsonp([87],{x5jR:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r={data:function(){return{visible:!1,dataForm:{memberDynamicId:0,createTime:"",state:"",writtenContent:"",imageContent:"",thumbNumber:"",commentNumber:"",memberId:"",nickname:"",headPortrait:""},dataRule:{createTime:[{required:!0,message:"创建时间不能为空",trigger:"blur"}],state:[{required:!0,message:"状态不能为空",trigger:"blur"}],writtenContent:[{required:!0,message:"文字内容不能为空",trigger:"blur"}],imageContent:[{required:!0,message:"图片内容不能为空",trigger:"blur"}],thumbNumber:[{required:!0,message:"点赞数量不能为空",trigger:"blur"}],commentNumber:[{required:!0,message:"评论数量不能为空",trigger:"blur"}],memberId:[{required:!0,message:"会员id不能为空",trigger:"blur"}],nickname:[{required:!0,message:"会员名称不能为空",trigger:"blur"}],headPortrait:[{required:!0,message:"会员头像不能为空",trigger:"blur"}]}}},methods:{init:function(e){var t=this;this.dataForm.memberDynamicId=e||0,this.visible=!0,this.$nextTick(function(){t.$refs.dataForm.resetFields(),t.dataForm.memberDynamicId&&t.$http({url:t.$http.adornUrl("/cellar/cellarmemberdynamicdb/info/"+t.dataForm.memberDynamicId),method:"get",params:t.$http.adornParams()}).then(function(e){var a=e.data;a&&0===a.code&&(t.dataForm.createTime=a.cellarmemberdynamicdb.createTime,t.dataForm.state=a.cellarmemberdynamicdb.state,t.dataForm.writtenContent=a.cellarmemberdynamicdb.writtenContent,t.dataForm.imageContent=a.cellarmemberdynamicdb.imageContent,t.dataForm.thumbNumber=a.cellarmemberdynamicdb.thumbNumber,t.dataForm.commentNumber=a.cellarmemberdynamicdb.commentNumber,t.dataForm.memberId=a.cellarmemberdynamicdb.memberId,t.dataForm.nickname=a.cellarmemberdynamicdb.nickname,t.dataForm.headPortrait=a.cellarmemberdynamicdb.headPortrait)})})},dataFormSubmit:function(){var e=this;this.$refs.dataForm.validate(function(t){t&&e.$http({url:e.$http.adornUrl("/cellar/cellarmemberdynamicdb/"+(e.dataForm.memberDynamicId?"update":"save")),method:"post",data:e.$http.adornData({memberDynamicId:e.dataForm.memberDynamicId||void 0,createTime:e.dataForm.createTime,state:e.dataForm.state,writtenContent:e.dataForm.writtenContent,imageContent:e.dataForm.imageContent,thumbNumber:e.dataForm.thumbNumber,commentNumber:e.dataForm.commentNumber,memberId:e.dataForm.memberId,nickname:e.dataForm.nickname,headPortrait:e.dataForm.headPortrait})}).then(function(t){var a=t.data;a&&0===a.code?e.$message({message:"操作成功",type:"success",duration:1500,onClose:function(){e.visible=!1,e.$emit("refreshDataList")}}):e.$message.error(a.msg)})})}}},m={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-dialog",{attrs:{title:e.dataForm.id?"修改":"新增","close-on-click-modal":!1,visible:e.visible},on:{"update:visible":function(t){e.visible=t}}},[a("el-form",{ref:"dataForm",attrs:{model:e.dataForm,rules:e.dataRule,"label-width":"80px"},nativeOn:{keyup:function(t){if(!("button"in t)&&e._k(t.keyCode,"enter",13,t.key))return null;e.dataFormSubmit()}}},[a("el-form-item",{attrs:{label:"创建时间",prop:"createTime"}},[a("el-input",{attrs:{placeholder:"创建时间"},model:{value:e.dataForm.createTime,callback:function(t){e.$set(e.dataForm,"createTime",t)},expression:"dataForm.createTime"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"状态",prop:"state"}},[a("el-input",{attrs:{placeholder:"状态"},model:{value:e.dataForm.state,callback:function(t){e.$set(e.dataForm,"state",t)},expression:"dataForm.state"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"文字内容",prop:"writtenContent"}},[a("el-input",{attrs:{placeholder:"文字内容"},model:{value:e.dataForm.writtenContent,callback:function(t){e.$set(e.dataForm,"writtenContent",t)},expression:"dataForm.writtenContent"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"图片内容",prop:"imageContent"}},[a("el-input",{attrs:{placeholder:"图片内容"},model:{value:e.dataForm.imageContent,callback:function(t){e.$set(e.dataForm,"imageContent",t)},expression:"dataForm.imageContent"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"点赞数量",prop:"thumbNumber"}},[a("el-input",{attrs:{placeholder:"点赞数量"},model:{value:e.dataForm.thumbNumber,callback:function(t){e.$set(e.dataForm,"thumbNumber",t)},expression:"dataForm.thumbNumber"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"评论数量",prop:"commentNumber"}},[a("el-input",{attrs:{placeholder:"评论数量"},model:{value:e.dataForm.commentNumber,callback:function(t){e.$set(e.dataForm,"commentNumber",t)},expression:"dataForm.commentNumber"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"会员id",prop:"memberId"}},[a("el-input",{attrs:{placeholder:"会员id"},model:{value:e.dataForm.memberId,callback:function(t){e.$set(e.dataForm,"memberId",t)},expression:"dataForm.memberId"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"会员名称",prop:"nickname"}},[a("el-input",{attrs:{placeholder:"会员名称"},model:{value:e.dataForm.nickname,callback:function(t){e.$set(e.dataForm,"nickname",t)},expression:"dataForm.nickname"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"会员头像",prop:"headPortrait"}},[a("el-input",{attrs:{placeholder:"会员头像"},model:{value:e.dataForm.headPortrait,callback:function(t){e.$set(e.dataForm,"headPortrait",t)},expression:"dataForm.headPortrait"}})],1)],1),e._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.visible=!1}}},[e._v("取消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.dataFormSubmit()}}},[e._v("确定")])],1)],1)},staticRenderFns:[]},o=a("46Yf")(r,m,!1,null,null,null);t.default=o.exports}});