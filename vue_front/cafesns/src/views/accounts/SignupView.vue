<template>
    <div>
      <h1>회원가입페이지</h1>
      <label for="username">아이디</label>
      <input type="text" id="username" placeholder="아이디를 입력하세요">

      <label for="password1">비밀번호</label>
      <input type="password" id="password1" placeholder="비밀번호를 입력하세요">

      <label for="password2">비밀번호확인</label>
      <input type="password" id="password2" placeholder="동일한 비밀번호를 입력하세요">
      <button @click="signup">가입</button>
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
  ...mapActions(["signup"]),
  },
}

</script>

<style>

</style>