<script setup lang="ts">
import {defineProps, onMounted,ref} from "vue";
import axios from "axios";
import {useRouter} from "vue-router";
const props = defineProps({
  postId:{
    type:[Number,String],
    required:true,
  },
});
const post = ref({
  id:0,
  title: "",
  content: "",

});
const router = useRouter();
const moveToEdit = () => {
  router.push({name : "edit", params:{postId:props.postId}});
}

const deletePost = function () {
    if(confirm("삭제하시겠습니까?")){
      axios.delete("/api/posts/"+props.postId).then(() => {
        router.replace({name:"home"});
      });
    }
};

onMounted(()=>{
  axios.get("/api/posts/"+props.postId).then((response) => {
    post.value = response.data;
  });
});
</script>

<template>
  <el-row>
    <el-col>
      <h2 class="title">{{post.title}}</h2>
      <div class="sub d-flex">
        <div class="sub d-flex">
          <div class="category">개발</div>
          <div class="regDate">2023-03-03</div>
        </div>
      </div>
    </el-col>
  </el-row>

  <el-row class="mt-3">
    <el-col>
      <div class="content">{{post.content}}</div>
    </el-col>
  </el-row>


  <el-row class="mt-3">
    <el-col>
      <div class="d-flex justify-content-end">
      <el-button type="success" @click="moveToEdit()">수정</el-button>
        <el-button type="warning" @click="deletePost()">삭제</el-button>
      </div>
    </el-col>
  </el-row>


</template>

<style scoped lang="scss">
.title{
  font-size: 1.6rem;
  font-weight: 600;
  color: #383838;
  margin:0;
}
.sub{
  margin-top: 10px;
  font-size: 0.8rem;

  .regDate{
    margin-left: 10px;
    color: #6b6b6b;
  }
}
.content{
  font-size: 0.95rem;
  margin-top: 9px;
  color: #616161;
  white-space: break-spaces;
  line-height: 1.5;
}

</style>