<template>
  <div>
    <h3>Signup</h3>
      <form>
        <div class="form-group">
          <label for="username">아이디</label>
          <input type="text" v-model="signupData.username" id="username" class="form-control" placeholder="아이디를 입력하세요">
        </div>

        <div class="form-group">
          <label for="password1">비밀번호</label>
          <input type="password" v-model="signupData.password1" id="password1" class="form-control" placeholder="비밀번호를 입력하세요">
        </div>

        <div class="form-group">
          <label for="password2">비밀번호확인</label>
          <input type="password" v-model="signupData.password2" id="password2" class="form-control" placeholder="확인용 비밀번호를 입력하세요">
        </div>
      </form>  
    <button @click="signup(signupData)" class="btn btn-primary">회원가입</button>
  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  name: "SignupView",

  data() {
    return {
      signupData: {
        username: null,
        passworod1: null,
        password2: null,
      }
    }
  },

  methods: {
  ...mapActions(['signup'])
  },
}

</script>

<style>

</style>