webpackJsonp([41,84],{ddVX:function(e,a,t){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var r={data:function(){return{dataForm:{key:""},dataList:[],pageIndex:1,pageSize:10,totalPage:0,dataListLoading:!1,dataListSelections:[],addOrUpdateVisible:!1}},components:{AddOrUpdate:t("tB1y").default},activated:function(){this.getDataList()},methods:{getDataList:function(){var e=this;this.dataListLoading=!0,this.$http({url:this.$http.adornUrl("/cellar/cellarmemberintegralchangerecorddb/list"),method:"get",params:this.$http.adornParams({page:this.pageIndex,limit:this.pageSize,key:this.dataForm.key})}).then(function(a){var t=a.data;t&&0===t.code?(e.dataList=t.page.list,e.totalPage=t.page.totalCount):(e.dataList=[],e.totalPage=0),e.dataListLoading=!1})},sizeChangeHandle:function(e){this.pageSize=e,this.pageIndex=1,this.getDataList()},currentChangeHandle:function(e){this.pageIndex=e,this.getDataList()},selectionChangeHandle:function(e){this.dataListSelections=e},addOrUpdateHandle:function(e){var a=this;this.addOrUpdateVisible=!0,this.$nextTick(function(){a.$refs.addOrUpdate.init(e)})},deleteHandle:function(e){var a=this,t=e?[e]:this.dataListSelections.map(function(e){return e.memberIntegralChangeRecordId});this.$confirm("确定对[id="+t.join(",")+"]进行["+(e?"删除":"批量删除")+"]操作?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){a.$http({url:a.$http.adornUrl("/cellar/cellarmemberintegralchangerecorddb/delete"),method:"post",data:a.$http.adornData(t,!1)}).then(function(e){var t=e.data;t&&0===t.code?a.$message({message:"操作成功",type:"success",duration:1500,onClose:function(){a.getDataList()}}):a.$message.error(t.msg)})})}}},n={render:function(){var e=this,a=e.$createElement,t=e._self._c||a;return t("div",{staticClass:"mod-config"},[t("el-form",{attrs:{inline:!0,model:e.dataForm},nativeOn:{keyup:function(a){if(!("button"in a)&&e._k(a.keyCode,"enter",13,a.key))return null;e.getDataList()}}},[t("el-form-item",[t("el-input",{attrs:{placeholder:"参数名",clearable:""},model:{value:e.dataForm.key,callback:function(a){e.$set(e.dataForm,"key",a)},expression:"dataForm.key"}})],1),e._v(" "),t("el-form-item",[t("el-button",{on:{click:function(a){e.getDataList()}}},[e._v("查询")]),e._v(" "),e.isAuth("cellar:cellarmemberintegralchangerecorddb:save")?t("el-button",{attrs:{type:"primary"},on:{click:function(a){e.addOrUpdateHandle()}}},[e._v("新增")]):e._e(),e._v(" "),e.isAuth("cellar:cellarmemberintegralchangerecorddb:delete")?t("el-button",{attrs:{type:"danger",disabled:e.dataListSelections.length<=0},on:{click:function(a){e.deleteHandle()}}},[e._v("批量删除")]):e._e()],1)],1),e._v(" "),t("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.dataListLoading,expression:"dataListLoading"}],staticStyle:{width:"100%"},attrs:{data:e.dataList,border:""},on:{"selection-change":e.selectionChangeHandle}},[t("el-table-column",{attrs:{type:"selection","header-align":"center",align:"center",width:"50"}}),e._v(" "),t("el-table-column",{attrs:{prop:"memberIntegralChangeRecordId","header-align":"center",align:"center",label:"会员积分变动记录id"}}),e._v(" "),t("el-table-column",{attrs:{prop:"memberId","header-align":"center",align:"center",label:"会员id"}}),e._v(" "),t("el-table-column",{attrs:{prop:"changeIntegral","header-align":"center",align:"center",label:"变动积分"}}),e._v(" "),t("el-table-column",{attrs:{prop:"beforeChange","header-align":"center",align:"center",label:"变动前积分"}}),e._v(" "),t("el-table-column",{attrs:{prop:"afterIntegral","header-align":"center",align:"center",label:"变动后积分"}}),e._v(" "),t("el-table-column",{attrs:{prop:"changeType","header-align":"center",align:"center",label:"变动类型"}}),e._v(" "),t("el-table-column",{attrs:{prop:"changeDesc","header-align":"center",align:"center",label:"变动描述"}}),e._v(" "),t("el-table-column",{attrs:{prop:"orderId","header-align":"center",align:"center",label:"订单id"}}),e._v(" "),t("el-table-column",{attrs:{prop:"orderNo","header-align":"center",align:"center",label:"订单编号"}}),e._v(" "),t("el-table-column",{attrs:{prop:"state","header-align":"center",align:"center",label:"状态"}}),e._v(" "),t("el-table-column",{attrs:{prop:"createTime","header-align":"center",align:"center",label:"创建时间"}}),e._v(" "),t("el-table-column",{attrs:{fixed:"right","header-align":"center",align:"center",width:"150",label:"操作"},scopedSlots:e._u([{key:"default",fn:function(a){return[t("el-button",{attrs:{type:"text",size:"small"},on:{click:function(t){e.addOrUpdateHandle(a.row.memberIntegralChangeRecordId)}}},[e._v("修改")]),e._v(" "),t("el-button",{attrs:{type:"text",size:"small"},on:{click:function(t){e.deleteHandle(a.row.memberIntegralChangeRecordId)}}},[e._v("删除")])]}}])})],1),e._v(" "),t("el-pagination",{attrs:{"current-page":e.pageIndex,"page-sizes":[10,20,50,100],"page-size":e.pageSize,total:e.totalPage,layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":e.sizeChangeHandle,"current-change":e.currentChangeHandle}}),e._v(" "),e.addOrUpdateVisible?t("add-or-update",{ref:"addOrUpdate",on:{refreshDataList:e.getDataList}}):e._e()],1)},staticRenderFns:[]},l=t("46Yf")(r,n,!1,null,null,null);a.default=l.exports},tB1y:function(e,a,t){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var r={data:function(){return{visible:!1,dataForm:{memberIntegralChangeRecordId:0,memberId:"",changeIntegral:"",beforeChange:"",afterIntegral:"",changeType:"",changeDesc:"",orderId:"",orderNo:"",state:"",createTime:""},dataRule:{memberId:[{required:!0,message:"会员id不能为空",trigger:"blur"}],changeIntegral:[{required:!0,message:"变动积分不能为空",trigger:"blur"}],beforeChange:[{required:!0,message:"变动前积分不能为空",trigger:"blur"}],afterIntegral:[{required:!0,message:"变动后积分不能为空",trigger:"blur"}],changeType:[{required:!0,message:"变动类型不能为空",trigger:"blur"}],changeDesc:[{required:!0,message:"变动描述不能为空",trigger:"blur"}],orderId:[{required:!0,message:"订单id不能为空",trigger:"blur"}],orderNo:[{required:!0,message:"订单编号不能为空",trigger:"blur"}],state:[{required:!0,message:"状态不能为空",trigger:"blur"}],createTime:[{required:!0,message:"创建时间不能为空",trigger:"blur"}]}}},methods:{init:function(e){var a=this;this.dataForm.memberIntegralChangeRecordId=e||0,this.visible=!0,this.$nextTick(function(){a.$refs.dataForm.resetFields(),a.dataForm.memberIntegralChangeRecordId&&a.$http({url:a.$http.adornUrl("/cellar/cellarmemberintegralchangerecorddb/info/"+a.dataForm.memberIntegralChangeRecordId),method:"get",params:a.$http.adornParams()}).then(function(e){var t=e.data;t&&0===t.code&&(a.dataForm.memberId=t.cellarmemberintegralchangerecorddb.memberId,a.dataForm.changeIntegral=t.cellarmemberintegralchangerecorddb.changeIntegral,a.dataForm.beforeChange=t.cellarmemberintegralchangerecorddb.beforeChange,a.dataForm.afterIntegral=t.cellarmemberintegralchangerecorddb.afterIntegral,a.dataForm.changeType=t.cellarmemberintegralchangerecorddb.changeType,a.dataForm.changeDesc=t.cellarmemberintegralchangerecorddb.changeDesc,a.dataForm.orderId=t.cellarmemberintegralchangerecorddb.orderId,a.dataForm.orderNo=t.cellarmemberintegralchangerecorddb.orderNo,a.dataForm.state=t.cellarmemberintegralchangerecorddb.state,a.dataForm.createTime=t.cellarmemberintegralchangerecorddb.createTime)})})},dataFormSubmit:function(){var e=this;this.$refs.dataForm.validate(function(a){a&&e.$http({url:e.$http.adornUrl("/cellar/cellarmemberintegralchangerecorddb/"+(e.dataForm.memberIntegralChangeRecordId?"update":"save")),method:"post",data:e.$http.adornData({memberIntegralChangeRecordId:e.dataForm.memberIntegralChangeRecordId||void 0,memberId:e.dataForm.memberId,changeIntegral:e.dataForm.changeIntegral,beforeChange:e.dataForm.beforeChange,afterIntegral:e.dataForm.afterIntegral,changeType:e.dataForm.changeType,changeDesc:e.dataForm.changeDesc,orderId:e.dataForm.orderId,orderNo:e.dataForm.orderNo,state:e.dataForm.state,createTime:e.dataForm.createTime})}).then(function(a){var t=a.data;t&&0===t.code?e.$message({message:"操作成功",type:"success",duration:1500,onClose:function(){e.visible=!1,e.$emit("refreshDataList")}}):e.$message.error(t.msg)})})}}},n={render:function(){var e=this,a=e.$createElement,t=e._self._c||a;return t("el-dialog",{attrs:{title:e.dataForm.id?"修改":"新增","close-on-click-modal":!1,visible:e.visible},on:{"update:visible":function(a){e.visible=a}}},[t("el-form",{ref:"dataForm",attrs:{model:e.dataForm,rules:e.dataRule,"label-width":"80px"},nativeOn:{keyup:function(a){if(!("button"in a)&&e._k(a.keyCode,"enter",13,a.key))return null;e.dataFormSubmit()}}},[t("el-form-item",{attrs:{label:"会员id",prop:"memberId"}},[t("el-input",{attrs:{placeholder:"会员id"},model:{value:e.dataForm.memberId,callback:function(a){e.$set(e.dataForm,"memberId",a)},expression:"dataForm.memberId"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"变动积分",prop:"changeIntegral"}},[t("el-input",{attrs:{placeholder:"变动积分"},model:{value:e.dataForm.changeIntegral,callback:function(a){e.$set(e.dataForm,"changeIntegral",a)},expression:"dataForm.changeIntegral"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"变动前积分",prop:"beforeChange"}},[t("el-input",{attrs:{placeholder:"变动前积分"},model:{value:e.dataForm.beforeChange,callback:function(a){e.$set(e.dataForm,"beforeChange",a)},expression:"dataForm.beforeChange"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"变动后积分",prop:"afterIntegral"}},[t("el-input",{attrs:{placeholder:"变动后积分"},model:{value:e.dataForm.afterIntegral,callback:function(a){e.$set(e.dataForm,"afterIntegral",a)},expression:"dataForm.afterIntegral"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"变动类型",prop:"changeType"}},[t("el-input",{attrs:{placeholder:"变动类型"},model:{value:e.dataForm.changeType,callback:function(a){e.$set(e.dataForm,"changeType",a)},expression:"dataForm.changeType"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"变动描述",prop:"changeDesc"}},[t("el-input",{attrs:{placeholder:"变动描述"},model:{value:e.dataForm.changeDesc,callback:function(a){e.$set(e.dataForm,"changeDesc",a)},expression:"dataForm.changeDesc"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"订单id",prop:"orderId"}},[t("el-input",{attrs:{placeholder:"订单id"},model:{value:e.dataForm.orderId,callback:function(a){e.$set(e.dataForm,"orderId",a)},expression:"dataForm.orderId"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"订单编号",prop:"orderNo"}},[t("el-input",{attrs:{placeholder:"订单编号"},model:{value:e.dataForm.orderNo,callback:function(a){e.$set(e.dataForm,"orderNo",a)},expression:"dataForm.orderNo"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"状态",prop:"state"}},[t("el-input",{attrs:{placeholder:"状态"},model:{value:e.dataForm.state,callback:function(a){e.$set(e.dataForm,"state",a)},expression:"dataForm.state"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"创建时间",prop:"createTime"}},[t("el-input",{attrs:{placeholder:"创建时间"},model:{value:e.dataForm.createTime,callback:function(a){e.$set(e.dataForm,"createTime",a)},expression:"dataForm.createTime"}})],1)],1),e._v(" "),t("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:function(a){e.visible=!1}}},[e._v("取消")]),e._v(" "),t("el-button",{attrs:{type:"primary"},on:{click:function(a){e.dataFormSubmit()}}},[e._v("确定")])],1)],1)},staticRenderFns:[]},l=t("46Yf")(r,n,!1,null,null,null);a.default=l.exports}});