<script setup lang="ts">
import {ref, reactive} from "vue";

import axios from 'axios';
import {useRouter} from "vue-router"

const name = ref("")
const email = ref("")
const password = ref("")
const router = useRouter();

const signIn = function () {
  const data = {
    email: email.value,
    password: password.value
  }
  const config = {
    headers: {
      "Content-Type": "application/x-www-form-urlencoded"
    }
  }
  axios.post("/api/login", data, config)
      .then(() => {
        router.replace({name:"home"});
      })

};

const form = reactive({
  name: '',
});

</script>

<template>
  <div class="d-flex justify-content-center">

  <div class="w-50 ">

  <el-form :model="form" class="mt-5" label-width="120px" size="large" >
    <el-form-item label="이메일">
      <el-input v-model="email"/>
    </el-form-item>
    <el-form-item label="비밀번호">
      <el-input v-model="password" type="password" autocomplete="off"/>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="signIn">로그인</el-button>

    </el-form-item>
  </el-form>
  </div>
  </div>
</template>

<style>

</style>