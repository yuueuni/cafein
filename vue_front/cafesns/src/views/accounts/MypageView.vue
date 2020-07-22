<template>
    <div>
      <h3>Mypage</h3>
      <h3>userData.id</h3>
      <h3>followers: {} | following: {} | Reviews: {}</h3>
      <div>
        <h3>likes</h3>
        <img src="" alt="">
      </div>
      <div>
        <h3>visited</h3>
        <img src="" alt="">
      </div>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'

export default {
  name: "MyPageView",
  
  computed: {
    ...mapState(['userData'])
  },
  
  methods: {
    ...mapActions(['fetchUserData'])
  },
  
  create() {
    this.fetchUserData()
  }
}
</script>

<style>

</style>