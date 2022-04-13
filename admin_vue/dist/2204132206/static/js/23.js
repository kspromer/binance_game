webpackJsonp([23,45],{"04Xx":function(e,a,t){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var r={data:function(){return{dataForm:{accountId:""},dataList:[],pageIndex:1,pageSize:10,totalPage:0,dataListLoading:!1,dataListSelections:[],addOrUpdateVisible:!1}},components:{AddOrUpdate:t("QGkC").default},activated:function(){this.getDataList()},methods:{getDataList:function(){var e=this;this.dataListLoading=!0,this.$http({url:this.$http.adornUrl("/binancegame/accountrechargeaddress/list"),method:"get",params:this.$http.adornParams({page:this.pageIndex,limit:this.pageSize,accountId:this.dataForm.accountId})}).then(function(a){var t=a.data;t&&0===t.code?(e.dataList=t.page.list,e.totalPage=t.page.totalCount):(e.dataList=[],e.totalPage=0),e.dataListLoading=!1})},sizeChangeHandle:function(e){this.pageSize=e,this.pageIndex=1,this.getDataList()},currentChangeHandle:function(e){this.pageIndex=e,this.getDataList()},selectionChangeHandle:function(e){this.dataListSelections=e},addOrUpdateHandle:function(e){var a=this;this.addOrUpdateVisible=!0,this.$nextTick(function(){a.$refs.addOrUpdate.init(e)})},deleteHandle:function(e){var a=this,t=e?[e]:this.dataListSelections.map(function(e){return e.id});this.$confirm("确定对[id="+t.join(",")+"]进行["+(e?"删除":"批量删除")+"]操作?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){a.$http({url:a.$http.adornUrl("/binancegame/accountrechargeaddress/delete"),method:"post",data:a.$http.adornData(t,!1)}).then(function(e){var t=e.data;t&&0===t.code?a.$message({message:"操作成功",type:"success",duration:1500,onClose:function(){a.getDataList()}}):a.$message.error(t.msg)})})}}},n={render:function(){var e=this,a=e.$createElement,t=e._self._c||a;return t("div",{staticClass:"mod-config"},[t("el-form",{attrs:{inline:!0,model:e.dataForm},nativeOn:{keyup:function(a){if(!("button"in a)&&e._k(a.keyCode,"enter",13,a.key))return null;e.getDataList()}}},[t("el-form-item",[t("el-input",{attrs:{placeholder:"accountId",clearable:""},model:{value:e.dataForm.accountId,callback:function(a){e.$set(e.dataForm,"accountId",a)},expression:"dataForm.accountId"}})],1),e._v(" "),t("el-form-item",[t("el-button",{on:{click:function(a){e.getDataList()}}},[e._v("查询")]),e._v(" "),e.isAuth("binancegame:accountrechargeaddress:save")?t("el-button",{attrs:{type:"primary"},on:{click:function(a){e.addOrUpdateHandle()}}},[e._v("新增")]):e._e()],1)],1),e._v(" "),t("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.dataListLoading,expression:"dataListLoading"}],staticStyle:{width:"100%"},attrs:{data:e.dataList,border:""},on:{"selection-change":e.selectionChangeHandle}},[t("el-table-column",{attrs:{type:"selection","header-align":"center",align:"center",width:"50"}}),e._v(" "),t("el-table-column",{attrs:{prop:"accountId","header-align":"center",align:"center",label:"玩家id"}}),e._v(" "),t("el-table-column",{attrs:{prop:"address","header-align":"center",align:"center",label:"地址"}}),e._v(" "),t("el-table-column",{attrs:{prop:"privateKey","header-align":"center",align:"center",label:"私钥"}}),e._v(" "),t("el-table-column",{attrs:{prop:"walletSerialNumber","header-align":"center",align:"center",label:"钱包序号"}}),e._v(" "),t("el-table-column",{attrs:{prop:"coinType","header-align":"center",align:"center",label:"币种类"}}),e._v(" "),t("el-table-column",{attrs:{prop:"createTime","header-align":"center",align:"center",label:"创建时间"}})],1),e._v(" "),t("el-pagination",{attrs:{"current-page":e.pageIndex,"page-sizes":[10,20,50,100],"page-size":e.pageSize,total:e.totalPage,layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":e.sizeChangeHandle,"current-change":e.currentChangeHandle}}),e._v(" "),e.addOrUpdateVisible?t("add-or-update",{ref:"addOrUpdate",on:{refreshDataList:e.getDataList}}):e._e()],1)},staticRenderFns:[]},i=t("46Yf")(r,n,!1,null,null,null);a.default=i.exports},QGkC:function(e,a,t){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var r={data:function(){return{visible:!1,dataForm:{id:0,accountId:"",address:"",privateKey:"",walletSerialNumber:"",coinType:"",createTime:""},dataRule:{accountId:[{required:!0,message:"玩家id不能为空",trigger:"blur"}],address:[{required:!0,message:"地址不能为空",trigger:"blur"}],privateKey:[{required:!0,message:"私钥不能为空",trigger:"blur"}],walletSerialNumber:[{required:!0,message:"钱包序号不能为空",trigger:"blur"}],coinType:[{required:!0,message:"币种类不能为空",trigger:"blur"}],createTime:[{required:!0,message:"创建时间不能为空",trigger:"blur"}]}}},methods:{init:function(e){var a=this;this.dataForm.id=e||0,this.visible=!0,this.$nextTick(function(){a.$refs.dataForm.resetFields(),a.dataForm.id&&a.$http({url:a.$http.adornUrl("/binancegame/accountrechargeaddress/info/"+a.dataForm.id),method:"get",params:a.$http.adornParams()}).then(function(e){var t=e.data;t&&0===t.code&&(a.dataForm.accountId=t.accountrechargeaddress.accountId,a.dataForm.address=t.accountrechargeaddress.address,a.dataForm.privateKey=t.accountrechargeaddress.privateKey,a.dataForm.walletSerialNumber=t.accountrechargeaddress.walletSerialNumber,a.dataForm.coinType=t.accountrechargeaddress.coinType,a.dataForm.createTime=t.accountrechargeaddress.createTime)})})},dataFormSubmit:function(){var e=this;this.$refs.dataForm.validate(function(a){a&&e.$http({url:e.$http.adornUrl("/binancegame/accountrechargeaddress/"+(e.dataForm.id?"update":"save")),method:"post",data:e.$http.adornData({id:e.dataForm.id||void 0,accountId:e.dataForm.accountId,address:e.dataForm.address,privateKey:e.dataForm.privateKey,walletSerialNumber:e.dataForm.walletSerialNumber,coinType:e.dataForm.coinType,createTime:e.dataForm.createTime})}).then(function(a){var t=a.data;t&&0===t.code?e.$message({message:"操作成功",type:"success",duration:1500,onClose:function(){e.visible=!1,e.$emit("refreshDataList")}}):e.$message.error(t.msg)})})}}},n={render:function(){var e=this,a=e.$createElement,t=e._self._c||a;return t("el-dialog",{attrs:{title:(e.dataForm.id,"新增充值地址"),"close-on-click-modal":!1,visible:e.visible},on:{"update:visible":function(a){e.visible=a}}},[t("el-form",{ref:"dataForm",attrs:{model:e.dataForm,rules:e.dataRule,"label-width":"80px"},nativeOn:{keyup:function(a){if(!("button"in a)&&e._k(a.keyCode,"enter",13,a.key))return null;e.dataFormSubmit()}}},[t("el-form-item",{attrs:{label:"私钥"}},[t("el-input",{attrs:{placeholder:"(私钥,不填写默认生成)"},model:{value:e.dataForm.privateKey,callback:function(a){e.$set(e.dataForm,"privateKey",a)},expression:"dataForm.privateKey"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"生成数量",prop:"walletSerialNumber"}},[t("el-input",{attrs:{placeholder:"生成数量"},model:{value:e.dataForm.walletSerialNumber,callback:function(a){e.$set(e.dataForm,"walletSerialNumber",a)},expression:"dataForm.walletSerialNumber"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"币种类",prop:"coinType"}},[t("el-radio-group",{model:{value:e.dataForm.coinType,callback:function(a){e.$set(e.dataForm,"coinType",a)},expression:"dataForm.coinType"}},[t("el-radio-button",{attrs:{label:"BEP20"}},[e._v("BEP20")]),e._v(" "),t("el-radio-button",{attrs:{label:"TRC20"}},[e._v("TRC20")])],1)],1)],1),e._v(" "),t("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:function(a){e.visible=!1}}},[e._v("取消")]),e._v(" "),t("el-button",{attrs:{type:"primary"},on:{click:function(a){e.dataFormSubmit()}}},[e._v("确定")])],1)],1)},staticRenderFns:[]},i=t("46Yf")(r,n,!1,null,null,null);a.default=i.exports}});