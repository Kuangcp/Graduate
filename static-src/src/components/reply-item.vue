<template>
  <div>
    <group gutter=0>
      <div slot="title" class="group-title">反馈</div>
      <x-textarea :max="200" v-model="msg.content"
        placeholder="请输入反馈内容...">
      </x-textarea>
    </group>

    <group gutter=0>
      <div slot="title" class="group-title">联系方式</div>
      <x-input v-model="msg.contact" placeholder="联系方式选填，便于我们与你联系"></x-input>
    </group>

    <div style="padding: 15px;">
      <x-button @click.native="submitMsg" type="primary">提交</x-button>
    </div>
    <alert v-model="isShowAlert" title="提示">{{ alertMsg }}</alert>
  </div>
</template>

<script>
import { XTextarea, Group, XButton, XInput, Alert } from 'vux'

export default {
  name: 'reply-item',
  data() {
    return {
      msg: {
        content: '',
        studentId: '',
        contact: ''
      },
      isShowAlert: false,
      alertMsg: ''
    }
  },
  methods: {
    async submitMsg() {
      const uri = this.GLOBAL_PARAMS.baseUri + '/bus/student/feedback?studentId='+this.msg.studentId+'&content='+this.msg.content+'&contact='+this.msg.contact;
      let returnData;
      try {
        returnData = await this.$http.get(uri);
      } catch (error) {
        
      }
      if (returnData && returnData.body.code === '0') {
        this.isShowAlert = true;
        this.alertMsg = '您的反馈已提交，我们将会尽快处理';
        this.msg.contact = '';
        this.msg.content = '';
      } else {
        this.isShowAlert = true;
        this.alertMsg = '由于网络异常，提交失败，请重试!';
      }
    },
    getLoginId() {
      this.msg.studentId = sessionStorage.getItem(this.GLOBAL_PARAMS.studentId);
    }
  },
  created() {
    this.getLoginId();
  },
  components: {
    XTextarea,
    Group,
    XButton,
    XInput,
    Alert
  }
}
</script>

<style lang="less">
.group-title {
  color: #999; 
  font-size:12px;
  padding: 2px 15px; 
}
</style>