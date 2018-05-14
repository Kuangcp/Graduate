<template>
  <div>
    <group gutter=0>
      <x-input v-model="searchText" placeholder="请输入要搜索的课题名">
        <x-button slot="right" @click.native="searchData" type="primary" mini>搜索</x-button>
      </x-input>
    </group>

    <group gutter=0 title="课题列表">
      <cell v-for="(item, index) in listData" :key="index" @click.native="showMsg(item)">
        <div slot="title">
          <span class="cell-content" v-html="item.name"></span>
        </div>
      </cell>
    </group>

    <x-dialog v-model="isShow" hide-on-blur>
      <div class="show-div">
       <div class="show-title" v-html="showData.name"></div>
       <scroller lock-x scrollbar-y height="275px" ref="scroller">
        <div class="show-content" v-html="showData.attention"></div>
       </scroller>
       <div>
         <flexbox class="show-btn">
           <flexbox-item>
            <x-button type="primary" @click.native="btnOk">选择</x-button>
           </flexbox-item>
           <flexbox-item>
            <x-button  type="primary" @click.native="btnCancel">取消</x-button>
           </flexbox-item>
         </flexbox>
       </div>
      </div>
    </x-dialog>

    <loading :show="isShowList" text="load..."></loading>

    <div v-transfer-dom>
      <confirm v-model="isShowConfirm"
      :close-on-confirm="false"
      title="提示"
      @on-confirm="onConfirm">
        <p style="text-align:center;">论题一旦选择则不可更改，请慎重！</p>
      </confirm>
    </div>
 <alert v-model="isShowAlert" title="提示">{{ alertMsg }}</alert>
  </div>
  
</template>

<script>
import {Group, Cell, XDialog, Scroller, Loading, XButton, Flexbox, FlexboxItem, Confirm, Alert, TransferDomDirective as TransferDom, XInput } from 'vux'

export default {
  name: 'book-item',
  directives: {
    TransferDom
  },
  data() {
    return {
      listData: [],
      isShow: false,
      isShowList: true,
      isShowConfirm: false,
      showData: {},
      searchText: ''
    }
  },
  methods: {

    async getData() {
      const uri = this.GLOBAL_PARAMS.baseUri + '/bus/student/listTopic';
      const listData = await this.$http.get(uri);
      this.listData = listData.body.data;
      console.log(this.listData)
      this.isShowList = false;
    },
    async searchData() {
      const uri = this.GLOBAL_PARAMS.baseUri + '/rest/topic/search?name=' + this.searchText;
      const listData = await this.$http.get(uri);
      this.listData = listData.body.data;
      console.log(this.listData)
      this.isShowList = false;
    },
    showMsg(item) {
      this.isShow = true;
      this.showData = item;
    },
    btnCancel() {
      this.isShow = false;
    },
    btnOk() {
      this.isShowConfirm = true;
    },
    async onConfirm() {
      const studentId = sessionStorage.getItem(this.GLOBAL_PARAMS.studentId);
      this.showData.studentId = studentId;
      // const uri = this.GLOBAL_PARAMS.baseUri + '/bus/student/topic/select';
      // const returnData = await this.$http.post(uri, this.showData);
      const uri = this.GLOBAL_PARAMS.baseUri + '/bus/student/topic/select?studentId='+studentId+'&topicId='+this.showData.topicId;
      let returnData;
      try{
         returnData = await this.$http.get(uri);
      }catch(error){

      }
      if (returnData && returnData.body.code === '0') {
        this.isShowAlert = true;
        this.alertMsg = '课题选择成功';
      } else if(returnData.body.code === '402'){
        this.isShowAlert = true;
        this.alertMsg = '您已经选择了一个课题';
      }else{
        this.isShowAlert = true;
        this.alertMsg = '由于网络异常，提交失败，请重试!';
      }
      this.isShow = false;
      this.getData();
      //  隐藏确认框
      this.isShowConfirm = false
    }
  },
  created() {
    this.getData()
  },
  components: {
    Group,
    Cell,
    XDialog,
    Scroller,
    Loading,
    XButton,
    Flexbox, 
    FlexboxItem,
    Confirm,
    XInput,
    Alert
  }
}
</script>

<style lang="less">
.cell-content {
    font-size: 13px;
    overflow: hidden;
    color: #999;
    text-overflow: ellipsis;
    line-height: 21px;
    text-align: left;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
}
.show-div {
  //width:256px;
  text-align: left;
}
.show-title {
  word-wrap: break-word;
  font-size: 18px;
  border-bottom: 1px solid #DEDEDE;
}
.show-content {
  word-wrap: break-word;
  font-size: 12px;
  padding: 2px;
}
.show-btn {
  button {
    
  }
}
</style>
