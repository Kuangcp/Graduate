<template>
  <page>
    <page-content>
      <book-item v-if="isShow.book" :listData="listData"></book-item>
      <defanse-item v-if="isShow.defanse"></defanse-item>
      <reply-item v-if="isShow.reply"></reply-item>
      <user-item v-if="isShow.user"></user-item>
    </page-content>

    <page-footer style="z-index: 9999999">
      <flexbox>
        <flexbox-item>
          <a class="footer-icon book-btn" :class="{ 'item-select': isShow.book }" @click="setShow('book')">
            &nbsp;
          </a>
        </flexbox-item>
        <flexbox-item>
          <a class="footer-icon defanse-btn" :class="{ 'item-select': isShow.defanse }" @click="setShow('defanse')">
            &nbsp;
          </a>
        </flexbox-item>
        <flexbox-item>
          <a class="footer-icon reply-btn" :class="{ 'item-select': isShow.reply }" @click="setShow('reply')">
            &nbsp;
          </a>
        </flexbox-item>
        <flexbox-item>
          <a class="footer-icon user-btn" :class="{ 'item-select': isShow.user }" @click="setShow('user')">
            &nbsp;
          </a>
        </flexbox-item>
      </flexbox>
    </page-footer>
  </page>
</template>

<script>
import Solo from 'solojs'
import {Group, Cell, Flexbox, FlexboxItem, CheckerItem } from 'vux'
import BookItem from './../components/book-item'
import DefanseItem from './../components/defanse-item'
import ReplyItem from './../components/reply-item'
import UserItem from './../components/user-item'

export default {
  extends: Solo.Page,
  components: {
    Group,
    Cell,
    Flexbox, 
    FlexboxItem,
    BookItem,
    DefanseItem,
    ReplyItem,
    UserItem,
    CheckerItem
  },
  data() {
    return {
      isShow: {
        book: true,
        defanse: false,
        reply: false,
        user: false
      },
      listData: {
        title: '测试',
        content: 'dfd'
      }
    }
  },
  methods: {
    // 设置那个组件显示
   setShow(val) {
     //TODO 四个按钮一定会处理
     for(var t in this.isShow) {
       if(t === val) {
         this.isShow[t] = true
       } else {
         this.isShow[t] = false;
       }
     }
   }
  },
  created() {
    
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
  .book-btn {
    background-image: url('./../assets/book.png');
  }
  .defanse-btn {
    background-image: url('./../assets/defanse.png');
  }
  .reply-btn {
    background-image: url('./../assets/reply.png');
  }
  .user-btn {
    background-image: url('./../assets/user.png');
  }
  .item-select {
    background-color: #EFEFF4;
  }
</style>
