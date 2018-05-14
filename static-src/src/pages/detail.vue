<template>
  <page :title="title">
    <page-header>
      {{ title }}
    </page-header>
    <page-content>
      <!-- Vue 组件的使用 -->
      <h1>View Data</h1>
      <list-item v-if="viewData"  :listData="viewData"></list-item>
    </page-content>
  </page>
</template>

<script>
import Solo from 'solojs'
// Vue 组件的引入
import ListItem from '../components/list-item'

export default {
  extends: Solo.Page,
  // Vue 组件的注册
  data() {
    return {
      title: '列表详情'
    }
  },
  components: {
    ListItem
  }
}
</script>

<style>
</style>
