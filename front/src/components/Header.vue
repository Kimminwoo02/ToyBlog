<template>
  <el-header class="header">
    <el-menu mode="horizontal" router>
      <el-menu-item index="/">Home</el-menu-item>
      <el-menu-item index="/write">글 작성</el-menu-item>
      <el-menu-item index="/home" v-if="cookie">로그아웃</el-menu-item>
      <el-menu-item index="/logIn" v-else>로그인</el-menu-item>
      <el-menu-item index="/signIn">회원가입</el-menu-item>

    </el-menu>
  </el-header>
</template>

<script setup lang ="ts">
import {computed} from "vue";


</script>

<style scoped>
.header{
  padding: 0;
  height: 60px;
}
.flex-grow{
  flex-grow: 2;
}
</style>

