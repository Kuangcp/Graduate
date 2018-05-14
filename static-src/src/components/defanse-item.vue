<template>
  <div>
    <group gutter=0>
      <flexbox>
        <flexbox-item>
          <a class="footer-icon place-btn" @click="showInfo">
            &nbsp;
          </a>
        </flexbox-item>
        <flexbox-item>
          <a class="footer-icon two-btn" @click="showGrate">
            &nbsp;
          </a>
        </flexbox-item>
        <flexbox-item>
          <a class="footer-icon teacher-btn" @click="showTeacher">
            &nbsp;
          </a>
        </flexbox-item>
      </flexbox>

      <flexbox>
        <flexbox-item>
          <a class="footer-icon all-btn" @click="showAll">
            &nbsp;
          </a>
        </flexbox-item>
        <flexbox-item>
          <a class="footer-icon" >
            &nbsp;
          </a>
        </flexbox-item>
        <flexbox-item>
          <a class="footer-icon" >
            &nbsp;
          </a>
        </flexbox-item>
      </flexbox>
    </group>

    <x-dialog v-model="isShow" hide-on-blur>
      <div class="show-div-1">
        <div v-show="isShowScore"> 
          <div class="score-div"></div>
          <div class="score-div"><span>导师评分：</span><span>{{ showMsg.guideScore }}</span></div>
          <div class="score-div"><span>答辩评分：</span><span>{{ showMsg.judgeScore }}</span></div>
          <div class="score-div"></div>
        </div>
       
       <div v-show="isShowPlace"> 
          <div class="score-div"></div>
          <div class="score-div"><span>答辩地点：</span><span>{{ showMsg.place }}</span></div>
          <div class="score-div"><span>答辩日期：</span><span>{{ showMsg.date }}</span></div>
          <div class="score-div"><span>答辩时间：</span><span>{{ showMsg.time }}</span></div>
          <div class="score-div"><span>答辩批次：</span><span>第 {{ showMsg.batch }} 次答辩</span></div>
          <div class="score-div"><span>答辩团队：</span><span>{{ showMsg.team }}</span></div>
          <div class="score-div"></div>
        </div>

        <div v-show="isShowTeacher">
          <div class="score-div"></div>
          <div class="score-div"><span>指导老师：</span><span>{{ showMsg.username }}</span></div>
          <div class="score-div"><span>电子邮箱:</span><span>{{ showMsg.email }}</span></div>
          <div class="score-div"><span>手机号码:</span><span>{{ showMsg.phone }}</span></div>
          <div class="score-div"><span>专业:</span><span>{{ showMsg.major }}</span></div>
          <div class="score-div"></div>
        </div>
      </div>
    </x-dialog>

    <alert v-model="isShowAlert" title="提示">{{ alertMsg }}</alert>

  </div>
</template>

<script>
import { Group, Flexbox, FlexboxItem, XDialog, Scroller, Alert } from 'vux'

export default {
  name: 'defanse-item',
  data() {
    return {
      isShow: false,
      isShowAlert: false,
      showData: '',
      loginId: '',
      showMsg: {},
      isShowScore: false,
      isShowPlace: false,
      isShowTeacher: false,
      alertMsg: '',
    }
  },
  http: {
    headers : {
      token: 'sdfsdfds'
    }
  },
  methods: {
    // 显示通知
    async showInfo() {
      this.isShowScore = false;
      this.isShowTeacher = false;
      const uri = this.GLOBAL_PARAMS.baseUri + '/bus/student/query/place/'+this.loginId;
      const returnData = await this.$http.get(uri);
      const msg = returnData.body;
      if (msg.code === '0') {
        this.showMsg = msg.data;
        this.isShowPlace = true;
        this.isShow = true;
      } else {
        this.isShowAlert = true;
        this.alertMsg = '你尚未参加答辩';
      }
    },
    // 显示成绩
    async showGrate() {
      this.isShowPlace = false;
      this.isShowTeacher = false;
      const uri = this.GLOBAL_PARAMS.baseUri + '/bus/student/query/score/' + this.loginId;
      const returnData = await this.$http.get(uri);
      const msg = returnData.body;
      if (msg.code === '0') {
        this.showMsg = msg.data;
        this.isShowScore = true;
        this.isShow = true;
      } else {
        this.isShowAlert = true;
        this.alertMsg = '你尚未参加答辩';
      }
    },
    // 县桑全部
    showAll() {
      this.isShowAlert = true;
      this.alertMsg = '程序员正在加紧开发...';
    },
    getLoginId() {
      this.loginId = sessionStorage.getItem(this.GLOBAL_PARAMS.studentId);
    },
    // 显示老师信息
    async showTeacher() {
      this.isShowPlace = false;
      this.isShowScore = false;
      const uri = this.GLOBAL_PARAMS.baseUri + '/bus/student/query/teacher/' + this.loginId;
      const returnData = await this.$http.get(uri);
      const msg = returnData.body;
      if (msg.code === '0') {
        this.showMsg = msg.data;
        this.isShowTeacher = true;
        this.isShow = true;
      } else {
        this.isShowAlert = true;
        this.alertMsg = '你尚未选择课题';
      }
    }
  },
  created() {
    this.getLoginId();
  },
  components: {
    Group,
    Flexbox,
    FlexboxItem,
    XDialog,
    Scroller,
    Alert
  }
}
</script>

<style lang="less">
.footer-icon {
    display: block;
    height: 100%;
    background-repeat: no-repeat;
    background-size: 30px 30px;
    background-position: 50%;
    cursor: pointer;
    line-height: 45px;
  }
.place-btn {
    background-image: url('./../assets/place.png');
  }
.two-btn {
  background-image: url('./../assets/defanse_2.png');
}
.all-btn {
  background-image: url('./../assets/all.png');
}
.teacher-btn {
  background-image: url('./../assets/teacher.png');
}
.show-div-1 {
  text-align: left;
  padding-left: 20px;
}
.show-content {
  word-wrap: break-word;
  font-size: 12px;
  padding: 2px;
}
.score-div {
  height: 30px;
}
</style>