<template>
  <div class="home text-center">
    <h1>Cafe</h1>
      <SlideList/>
    <h1>Post</h1>
      <SlideList/>
    <v-btn text @click="checkLogin"><h1>My page</h1></v-btn>
  </div>
</template>

<script>
// @ is an alias to /src
import SlideList from '../components/SlideList.vue'

export default {
  name: 'Home',
  components: {
    SlideList
  },
  methods: {
    checkLogin() {
      // alert('로그인이 필요합니다 !')
      this.$router.push({ name: 'mypage'})
    }
  }
}
</script>

<style scoped>
.link-text {
  color: black;
  text-decoration: none;
  text-transform: capitalize;
}

</style>
