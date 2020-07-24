<template>
    <div>
      <h3>Mypage</h3>
      <h3>userData.id</h3>
      <h3>followers: { followerList.length } | following: { followingList.length } | Posts: { PostList.length }</h3>
      <div>
        <h3>likes</h3>
        <img src="" alt="">
      </div>
      <div>
        <h3>stamp</h3>
        <img src="" alt="">
      </div>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'

export default {
  name: "MyPageView",
  
  computed: {
    ...mapState([
      'userData', 
      'postList',
      'likeList', 
      'stampList', 
      'followingList', 
      'followerList',
    ])
  },
  
  methods: {
    ...mapActions([
      'fetchUserData',
      'fetchPostList', 
      'fetchLikeLikst', 
      'fetchStampList', 
      'fetchFollowingList',
      'fetchFollowerList',
    ])
  },
  
  create() {
    this.fetchUserData()
  }
}
</script>

<style>

</style>