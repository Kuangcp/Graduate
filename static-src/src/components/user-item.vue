<template>
  <div>
    <group gutter=0>
      <div class="user-bg-img">
        <x-img 
        :src="user.img" @click.native="selectImg" class="user-img"></x-img>
      </div>
    <input @change="fileChange($event)" type="file" id="upload_file" ref="img_input" style="display: none"/> 
    

    <cell>
      <div slot="icon" class="msg-img">
        <img src="./../assets/user-id.png" class="msg-img"/>
      </div>
      <div slot="title">
        <span>&nbsp;学号：</span><span>{{ userData.studentNo }}</span>
      </div>
    </cell>
    <cell>
      <div slot="icon" class="msg-img">
        <img src="./../assets/user-1.png" class="msg-img"/>
      </div>
      <div slot="title">
        <span>&nbsp;姓名：</span><span>{{ userData.username }}</span>
      </div>
    </cell>
    <cell>
      <div slot="icon" class="msg-img">
        <img src="./../assets/email.png" class="msg-img"/>
      </div>
      <div slot="title">
        <span>&nbsp;邮箱：</span><span>{{ userData.email }}</span>
      </div>
    </cell>
    <cell>
      <div slot="icon" class="msg-img">
        <img src="./../assets/level.png" class="msg-img"/>
      </div>
      <div slot="title">
        <span>&nbsp;届数：</span><span>{{ userData.year }}</span>
      </div>
    </cell>
    
    </group>
  </div>
</template>

<script>
import { Group, XImg, Cell } from 'vux'

export default {
  name: 'user-item',
  data() {
    return {
      user: {
        img: 'https://o5omsejde.qnssl.com/demo/test1.jpg',
        id: '454654654',
        name: '哼唧',
        email: '的',
        level: '2018届'
      },
      userData: {},
      loginId: ''
    }
  },
  methods: {
    selectImg() {
      this.$refs.img_input.click();
      const userImg = sessionStorage.getItem('user-img');
      if (userImg) {
        this.userImg = userImg;
      }
    },
    fileChange(el) {
      if (!el.target.files[0].size) return;
      var reader = new FileReader();
      reader.readAsDataURL(el.target.files[0]);
      var user = this.user;
      reader.onload = function(el){
        var s = el.target.result;
        sessionStorage.setItem('user-img', s);
        user.img = s;
      }
      this.setUserImg();
    },
    setUserImg() {
      const userImg = sessionStorage.getItem('user-img');
      if (userImg) {
        this.userImg = userImg;
      }
    },
    async getUserData() {
      this.loginId = sessionStorage.getItem(this.GLOBAL_PARAMS.studentId);
      const uri = this.GLOBAL_PARAMS.baseUri + '/rest/student/' + this.loginId;
      const userData = await this.$http.get(uri);
      this.userData = userData.body.data;
    }
  },
  created() {
    this.setUserImg();
    this.getUserData();
  },
  components: {
    Group,
    XImg,
    Cell
  }
}
</script>

<style lang="less">
@from-color: #eeeeee;
@end-color: orangered;

.user-bg-img {
  position: relative;
  height: 30vh;

  background-image: url('./../assets/user-bg.jpg');
  background-size: 10vw*10vh;

}
.user-img {
  position: absolute;
  width: 30vw;
  height: 30vw;
  border-radius: 50%;
  bottom: 5vw;
  margin-left: 35vw;
}
.msg-img {
  height: 20px;
  width: 20px;
}
</style>