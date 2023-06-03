<script setup lang="ts">
import axios from 'axios';
import {ref} from "vue";

const posts = ref([])

axios.get("api/posts?page=1&size=5").then((response) => {
  console.log(response.data[0].createdAt)
  response.data.forEach((r: any) => {
    posts.value.push(r);
  });
});

</script>

<template>
  <ul>
    <li v-for="post in posts" :key="post.id">
      <div class="title">
        <router-link :to="{name:'read',params:{ postId : post.id}}"> {{ post.title }}</router-link>
      </div>

      <div class="content">
        {{ post.content }}
      </div>
      <div class="sub d-flex">
        <div class="createdAt">{{post.createdAt}}</div>
      </div>
    </li>
  </ul>

</template>
<style scoped lang="scss">
ul{
  list-style: none;
  padding: 0;
  li {
    margin-bottom: 1.4rem;
    .title {
      a{
        font-size:1.2rem;
        color: #383838;
        text-decoration: none;
      }
      &:hover{
        text-decoration: underline;
      }
    }
    .content{
      font-size: 0.95rem;
      margin-top: 8px;
      color: #5d5d5d;
    }
    &:last-child {
      margin-bottom: 0;
    }
    .sub{
      margin-top: 8px;
      font-size: 0.8rem;

      .regDate{
        margin-left: 5px;
        color: #6b6b6b;
      }
    }
  }
}



</style>