<template>
  <div>
    <h3>Login</h3>
      <form>
        <div class="form-group">
          <label for="username">아이디</label>
          <input type="text" v-model="loginData.username" id="username" class="form-control" placeholder="아이디를 입력하세요">
        </div>

        <div class="form-group">
          <label for="password1">비밀번호</label>
          <input type="password" v-model="loginData.password" id="password" class="form-control" placeholder="비밀번호를 입력하세요">
        </div>
      </form>  
    <button @click="login(loginData)" class="btn btn-primary">로그인</button>
  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
    name: 'LoginView',
    data() {
      return {
        loginData: {
          username:null,
          password:null,
        }
      }
    },
    methods: {
      ...mapActions(['login'])
    }

}
</script>

<style>

</style>