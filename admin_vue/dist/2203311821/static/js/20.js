webpackJsonp([20,39],{"04Xx":function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r={data:function(){return{dataForm:{key:""},dataList:[],pageIndex:1,pageSize:10,totalPage:0,dataListLoading:!1,dataListSelections:[],addOrUpdateVisible:!1}},components:{AddOrUpdate:a("QGkC").default},activated:function(){this.getDataList()},methods:{getDataList:function(){var e=this;this.dataListLoading=!0,this.$http({url:this.$http.adornUrl("/binancegame/accountrechargeaddress/list"),method:"get",params:this.$http.adornParams({page:this.pageIndex,limit:this.pageSize,key:this.dataForm.key})}).then(function(t){var a=t.data;a&&0===a.code?(e.dataList=a.page.list,e.totalPage=a.page.totalCount):(e.dataList=[],e.totalPage=0),e.dataListLoading=!1})},sizeChangeHandle:function(e){this.pageSize=e,this.pageIndex=1,this.getDataList()},currentChangeHandle:function(e){this.pageIndex=e,this.getDataList()},selectionChangeHandle:function(e){this.dataListSelections=e},addOrUpdateHandle:function(e){var t=this;this.addOrUpdateVisible=!0,this.$nextTick(function(){t.$refs.addOrUpdate.init(e)})},deleteHandle:function(e){var t=this,a=e?[e]:this.dataListSelections.map(function(e){return e.id});this.$confirm("确定对[id="+a.join(",")+"]进行["+(e?"删除":"批量删除")+"]操作?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){t.$http({url:t.$http.adornUrl("/binancegame/accountrechargeaddress/delete"),method:"post",data:t.$http.adornData(a,!1)}).then(function(e){var a=e.data;a&&0===a.code?t.$message({message:"操作成功",type:"success",duration:1500,onClose:function(){t.getDataList()}}):t.$message.error(a.msg)})})}}},n={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"mod-config"},[a("el-form",{attrs:{inline:!0,model:e.dataForm},nativeOn:{keyup:function(t){if(!("button"in t)&&e._k(t.keyCode,"enter",13,t.key))return null;e.getDataList()}}},[a("el-form-item",[a("el-input",{attrs:{placeholder:"参数名",clearable:""},model:{value:e.dataForm.key,callback:function(t){e.$set(e.dataForm,"key",t)},expression:"dataForm.key"}})],1),e._v(" "),a("el-form-item",[a("el-button",{on:{click:function(t){e.getDataList()}}},[e._v("查询")]),e._v(" "),e.isAuth("binancegame:accountrechargeaddress:save")?a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.addOrUpdateHandle()}}},[e._v("新增")]):e._e(),e._v(" "),e.isAuth("binancegame:accountrechargeaddress:delete")?a("el-button",{attrs:{type:"danger",disabled:e.dataListSelections.length<=0},on:{click:function(t){e.deleteHandle()}}},[e._v("批量删除")]):e._e()],1)],1),e._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.dataListLoading,expression:"dataListLoading"}],staticStyle:{width:"100%"},attrs:{data:e.dataList,border:""},on:{"selection-change":e.selectionChangeHandle}},[a("el-table-column",{attrs:{type:"selection","header-align":"center",align:"center",width:"50"}}),e._v(" "),a("el-table-column",{attrs:{prop:"accountId","header-align":"center",align:"center",label:"玩家id"}}),e._v(" "),a("el-table-column",{attrs:{prop:"address","header-align":"center",align:"center",label:"地址"}}),e._v(" "),a("el-table-column",{attrs:{prop:"privateKey","header-align":"center",align:"center",label:"私钥"}}),e._v(" "),a("el-table-column",{attrs:{prop:"walletSerialNumber","header-align":"center",align:"center",label:"钱包序号"}}),e._v(" "),a("el-table-column",{attrs:{prop:"coinType","header-align":"center",align:"center",label:"币种类"}}),e._v(" "),a("el-table-column",{attrs:{prop:"createTime","header-align":"center",align:"center",label:"创建时间"}}),e._v(" "),a("el-table-column",{attrs:{fixed:"right","header-align":"center",align:"center",width:"150",label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){e.addOrUpdateHandle(t.row.id)}}},[e._v("修改")]),e._v(" "),a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){e.deleteHandle(t.row.id)}}},[e._v("删除")])]}}])})],1),e._v(" "),a("el-pagination",{attrs:{"current-page":e.pageIndex,"page-sizes":[10,20,50,100],"page-size":e.pageSize,total:e.totalPage,layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":e.sizeChangeHandle,"current-change":e.currentChangeHandle}}),e._v(" "),e.addOrUpdateVisible?a("add-or-update",{ref:"addOrUpdate",on:{refreshDataList:e.getDataList}}):e._e()],1)},staticRenderFns:[]},i=a("46Yf")(r,n,!1,null,null,null);t.default=i.exports},QGkC:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r={data:function(){return{visible:!1,dataForm:{id:0,accountId:"",address:"",privateKey:"",walletSerialNumber:"",coinType:"",createTime:""},dataRule:{accountId:[{required:!0,message:"玩家id不能为空",trigger:"blur"}],address:[{required:!0,message:"地址不能为空",trigger:"blur"}],privateKey:[{required:!0,message:"私钥不能为空",trigger:"blur"}],walletSerialNumber:[{required:!0,message:"钱包序号不能为空",trigger:"blur"}],coinType:[{required:!0,message:"币种类不能为空",trigger:"blur"}],createTime:[{required:!0,message:"创建时间不能为空",trigger:"blur"}]}}},methods:{init:function(e){var t=this;this.dataForm.id=e||0,this.visible=!0,this.$nextTick(function(){t.$refs.dataForm.resetFields(),t.dataForm.id&&t.$http({url:t.$http.adornUrl("/binancegame/accountrechargeaddress/info/"+t.dataForm.id),method:"get",params:t.$http.adornParams()}).then(function(e){var a=e.data;a&&0===a.code&&(t.dataForm.accountId=a.accountrechargeaddress.accountId,t.dataForm.address=a.accountrechargeaddress.address,t.dataForm.privateKey=a.accountrechargeaddress.privateKey,t.dataForm.walletSerialNumber=a.accountrechargeaddress.walletSerialNumber,t.dataForm.coinType=a.accountrechargeaddress.coinType,t.dataForm.createTime=a.accountrechargeaddress.createTime)})})},dataFormSubmit:function(){var e=this;this.$refs.dataForm.validate(function(t){t&&e.$http({url:e.$http.adornUrl("/binancegame/accountrechargeaddress/"+(e.dataForm.id?"update":"save")),method:"post",data:e.$http.adornData({id:e.dataForm.id||void 0,accountId:e.dataForm.accountId,address:e.dataForm.address,privateKey:e.dataForm.privateKey,walletSerialNumber:e.dataForm.walletSerialNumber,coinType:e.dataForm.coinType,createTime:e.dataForm.createTime})}).then(function(t){var a=t.data;a&&0===a.code?e.$message({message:"操作成功",type:"success",duration:1500,onClose:function(){e.visible=!1,e.$emit("refreshDataList")}}):e.$message.error(a.msg)})})}}},n={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-dialog",{attrs:{title:(e.dataForm.id,"新增充值地址"),"close-on-click-modal":!1,visible:e.visible},on:{"update:visible":function(t){e.visible=t}}},[a("el-form",{ref:"dataForm",attrs:{model:e.dataForm,rules:e.dataRule,"label-width":"80px"},nativeOn:{keyup:function(t){if(!("button"in t)&&e._k(t.keyCode,"enter",13,t.key))return null;e.dataFormSubmit()}}},[a("el-form-item",{attrs:{label:"私钥"}},[a("el-input",{attrs:{placeholder:"(私钥,不填写默认生成)"},model:{value:e.dataForm.privateKey,callback:function(t){e.$set(e.dataForm,"privateKey",t)},expression:"dataForm.privateKey"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"生成数量",prop:"walletSerialNumber"}},[a("el-input",{attrs:{placeholder:"生成数量"},model:{value:e.dataForm.walletSerialNumber,callback:function(t){e.$set(e.dataForm,"walletSerialNumber",t)},expression:"dataForm.walletSerialNumber"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"币种类",prop:"coinType"}},[a("el-radio-group",{model:{value:e.dataForm.coinType,callback:function(t){e.$set(e.dataForm,"coinType",t)},expression:"dataForm.coinType"}},[a("el-radio-button",{attrs:{label:"BEP20"}},[e._v("BEP20")]),e._v(" "),a("el-radio-button",{attrs:{label:"TRC20"}},[e._v("TRC20")])],1)],1)],1),e._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.visible=!1}}},[e._v("取消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.dataFormSubmit()}}},[e._v("确定")])],1)],1)},staticRenderFns:[]},i=a("46Yf")(r,n,!1,null,null,null);t.default=i.exports}});