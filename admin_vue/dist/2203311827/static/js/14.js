webpackJsonp([14,33],{DSna:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n={data:function(){return{dataForm:{key:""},dataList:[],pageIndex:1,pageSize:10,totalPage:0,dataListLoading:!1,dataListSelections:[],addOrUpdateVisible:!1}},components:{AddOrUpdate:a("U/th").default},activated:function(){this.getDataList()},methods:{getDataList:function(){var e=this;this.dataListLoading=!0,this.$http({url:this.$http.adornUrl("/binancegame/moneychange/list"),method:"get",params:this.$http.adornParams({page:this.pageIndex,limit:this.pageSize,key:this.dataForm.key})}).then(function(t){var a=t.data;a&&0===a.code?(e.dataList=a.page.list,e.totalPage=a.page.totalCount):(e.dataList=[],e.totalPage=0),e.dataListLoading=!1})},sizeChangeHandle:function(e){this.pageSize=e,this.pageIndex=1,this.getDataList()},currentChangeHandle:function(e){this.pageIndex=e,this.getDataList()},selectionChangeHandle:function(e){this.dataListSelections=e},addOrUpdateHandle:function(e){var t=this;this.addOrUpdateVisible=!0,this.$nextTick(function(){t.$refs.addOrUpdate.init(e)})},deleteHandle:function(e){var t=this,a=e?[e]:this.dataListSelections.map(function(e){return e.id});this.$confirm("确定对[id="+a.join(",")+"]进行["+(e?"删除":"批量删除")+"]操作?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){t.$http({url:t.$http.adornUrl("/binancegame/moneychange/delete"),method:"post",data:t.$http.adornData(a,!1)}).then(function(e){var a=e.data;a&&0===a.code?t.$message({message:"操作成功",type:"success",duration:1500,onClose:function(){t.getDataList()}}):t.$message.error(a.msg)})})}}},r={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"mod-config"},[a("el-form",{attrs:{inline:!0,model:e.dataForm},nativeOn:{keyup:function(t){if(!("button"in t)&&e._k(t.keyCode,"enter",13,t.key))return null;e.getDataList()}}},[a("el-form-item",[a("el-input",{attrs:{placeholder:"参数名",clearable:""},model:{value:e.dataForm.key,callback:function(t){e.$set(e.dataForm,"key",t)},expression:"dataForm.key"}})],1),e._v(" "),a("el-form-item",[a("el-button",{on:{click:function(t){e.getDataList()}}},[e._v("查询")]),e._v(" "),e.isAuth("binancegame:moneychange:save")?a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.addOrUpdateHandle()}}},[e._v("新增")]):e._e(),e._v(" "),e.isAuth("binancegame:moneychange:delete")?a("el-button",{attrs:{type:"danger",disabled:e.dataListSelections.length<=0},on:{click:function(t){e.deleteHandle()}}},[e._v("批量删除")]):e._e()],1)],1),e._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.dataListLoading,expression:"dataListLoading"}],staticStyle:{width:"100%"},attrs:{data:e.dataList,border:""},on:{"selection-change":e.selectionChangeHandle}},[a("el-table-column",{attrs:{type:"selection","header-align":"center",align:"center",width:"50"}}),e._v(" "),a("el-table-column",{attrs:{prop:"id","header-align":"center",align:"center",label:""}}),e._v(" "),a("el-table-column",{attrs:{prop:"createTime","header-align":"center",align:"center",label:"创建时间"}}),e._v(" "),a("el-table-column",{attrs:{prop:"type","header-align":"center",align:"center",label:"类型"}}),e._v(" "),a("el-table-column",{attrs:{prop:"description","header-align":"center",align:"center",label:"详情"}}),e._v(" "),a("el-table-column",{attrs:{prop:"amount","header-align":"center",align:"center",label:"变动金额"}}),e._v(" "),a("el-table-column",{attrs:{prop:"accountId","header-align":"center",align:"center",label:"用户id"}}),e._v(" "),a("el-table-column",{attrs:{prop:"beforeMoney","header-align":"center",align:"center",label:"变动前"}}),e._v(" "),a("el-table-column",{attrs:{prop:"afterMoney","header-align":"center",align:"center",label:"变动后"}}),e._v(" "),a("el-table-column",{attrs:{fixed:"right","header-align":"center",align:"center",width:"150",label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){e.addOrUpdateHandle(t.row.id)}}},[e._v("修改")]),e._v(" "),a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){e.deleteHandle(t.row.id)}}},[e._v("删除")])]}}])})],1),e._v(" "),a("el-pagination",{attrs:{"current-page":e.pageIndex,"page-sizes":[10,20,50,100],"page-size":e.pageSize,total:e.totalPage,layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":e.sizeChangeHandle,"current-change":e.currentChangeHandle}}),e._v(" "),e.addOrUpdateVisible?a("add-or-update",{ref:"addOrUpdate",on:{refreshDataList:e.getDataList}}):e._e()],1)},staticRenderFns:[]},o=a("46Yf")(n,r,!1,null,null,null);t.default=o.exports},"U/th":function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n={data:function(){return{visible:!1,dataForm:{id:0,createTime:"",type:"",description:"",amount:"",accountId:"",beforeMoney:"",afterMoney:""},dataRule:{createTime:[{required:!0,message:"创建时间不能为空",trigger:"blur"}],type:[{required:!0,message:"类型不能为空",trigger:"blur"}],description:[{required:!0,message:"详情不能为空",trigger:"blur"}],amount:[{required:!0,message:"变动金额不能为空",trigger:"blur"}],accountId:[{required:!0,message:"用户id不能为空",trigger:"blur"}],beforeMoney:[{required:!0,message:"变动前不能为空",trigger:"blur"}],afterMoney:[{required:!0,message:"变动后不能为空",trigger:"blur"}]}}},methods:{init:function(e){var t=this;this.dataForm.id=e||0,this.visible=!0,this.$nextTick(function(){t.$refs.dataForm.resetFields(),t.dataForm.id&&t.$http({url:t.$http.adornUrl("/binancegame/moneychange/info/"+t.dataForm.id),method:"get",params:t.$http.adornParams()}).then(function(e){var a=e.data;a&&0===a.code&&(t.dataForm.createTime=a.moneychange.createTime,t.dataForm.type=a.moneychange.type,t.dataForm.description=a.moneychange.description,t.dataForm.amount=a.moneychange.amount,t.dataForm.accountId=a.moneychange.accountId,t.dataForm.beforeMoney=a.moneychange.beforeMoney,t.dataForm.afterMoney=a.moneychange.afterMoney)})})},dataFormSubmit:function(){var e=this;this.$refs.dataForm.validate(function(t){t&&e.$http({url:e.$http.adornUrl("/binancegame/moneychange/"+(e.dataForm.id?"update":"save")),method:"post",data:e.$http.adornData({id:e.dataForm.id||void 0,createTime:e.dataForm.createTime,type:e.dataForm.type,description:e.dataForm.description,amount:e.dataForm.amount,accountId:e.dataForm.accountId,beforeMoney:e.dataForm.beforeMoney,afterMoney:e.dataForm.afterMoney})}).then(function(t){var a=t.data;a&&0===a.code?e.$message({message:"操作成功",type:"success",duration:1500,onClose:function(){e.visible=!1,e.$emit("refreshDataList")}}):e.$message.error(a.msg)})})}}},r={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-dialog",{attrs:{title:e.dataForm.id?"修改":"新增","close-on-click-modal":!1,visible:e.visible},on:{"update:visible":function(t){e.visible=t}}},[a("el-form",{ref:"dataForm",attrs:{model:e.dataForm,rules:e.dataRule,"label-width":"80px"},nativeOn:{keyup:function(t){if(!("button"in t)&&e._k(t.keyCode,"enter",13,t.key))return null;e.dataFormSubmit()}}},[a("el-form-item",{attrs:{label:"创建时间",prop:"createTime"}},[a("el-input",{attrs:{placeholder:"创建时间"},model:{value:e.dataForm.createTime,callback:function(t){e.$set(e.dataForm,"createTime",t)},expression:"dataForm.createTime"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"类型",prop:"type"}},[a("el-input",{attrs:{placeholder:"类型"},model:{value:e.dataForm.type,callback:function(t){e.$set(e.dataForm,"type",t)},expression:"dataForm.type"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"详情",prop:"description"}},[a("el-input",{attrs:{placeholder:"详情"},model:{value:e.dataForm.description,callback:function(t){e.$set(e.dataForm,"description",t)},expression:"dataForm.description"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"变动金额",prop:"amount"}},[a("el-input",{attrs:{placeholder:"变动金额"},model:{value:e.dataForm.amount,callback:function(t){e.$set(e.dataForm,"amount",t)},expression:"dataForm.amount"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"用户id",prop:"accountId"}},[a("el-input",{attrs:{placeholder:"用户id"},model:{value:e.dataForm.accountId,callback:function(t){e.$set(e.dataForm,"accountId",t)},expression:"dataForm.accountId"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"变动前",prop:"beforeMoney"}},[a("el-input",{attrs:{placeholder:"变动前"},model:{value:e.dataForm.beforeMoney,callback:function(t){e.$set(e.dataForm,"beforeMoney",t)},expression:"dataForm.beforeMoney"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"变动后",prop:"afterMoney"}},[a("el-input",{attrs:{placeholder:"变动后"},model:{value:e.dataForm.afterMoney,callback:function(t){e.$set(e.dataForm,"afterMoney",t)},expression:"dataForm.afterMoney"}})],1)],1),e._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.visible=!1}}},[e._v("取消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.dataFormSubmit()}}},[e._v("确定")])],1)],1)},staticRenderFns:[]},o=a("46Yf")(n,r,!1,null,null,null);t.default=o.exports}});