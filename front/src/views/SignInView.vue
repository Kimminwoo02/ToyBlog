<script setup lang="ts">
import {ref, reactive} from "vue";

import axios from 'axios';
import {useRouter} from "vue-router"

const name = ref("")
const email = ref("")
const password = ref("")
const router = useRouter();

const signIn = function () {
  axios.post("api/auth/signup", {
    name: name.value,
    email: email.value,
    password: password.value,

  })
      .then(() => {
        alert("회원가입이 완료되었습니다!")
        router.replace({name: "home"});
      })
};

const form = reactive({
  name: '',
})

</script>

<template>
  <div class="d-flex justify-content-center">

  <div class="w-50 ">

  <el-form :model="form" class="mt-5"  label-width="120px" size="large" >
    <el-form-item label="이메일">
      <el-input v-model="email"/>
    </el-form-item>
    <el-form-item label="이름">
      <el-input v-model="name"/>
    </el-form-item>
    <el-form-item label="비밀번호">
      <el-input v-model="password" type="password" autocomplete="off"/>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="signIn">Create</el-button>
      <el-button>Cancel</el-button>
    </el-form-item>
  </el-form>
  </div>
  </div>
</template>

<style>

</style>