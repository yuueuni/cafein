<template>
    <div>
      <div class="float-right">
        <router-link to="" class="link-text "><v-btn text>Followers: {{ followerList.length }}</v-btn></router-link> |
        <router-link to="" class="link-text "><v-btn text>Following: {{ followingList.length }}</v-btn></router-link> |
        <router-link to="" class="link-text"><v-btn text>Posts: { PostList.length }</v-btn></router-link> 
        <!-- <h3>followers: {{ followerList.length }} | following: {{ followingList.length }} | Posts: {{ PostList.length }}</h3> -->
      </div>
      <h1>username{{ userData.id}}</h1>
        <!-- <h3>{{ userData.id.split('@')[0] }}</h3> -->
        <!-- const username = userData.id.spli('@')
        <h3>{{ username[0] }}</h3> -->
      <hr>
      <br>
      <div>
        <span style="font-size: 3em; color: Tomato;">
          <i class="fas fa-heart"></i>
        </span>
        <!-- <router-link v-for="like in likeList" :key="like.lno" to="" class="link-text"> -->
          <SlideList/>
          <!-- <img src="like.cafeno" alt="like cafe image"> -->
          <!-- <h4>{{ like.cafeno.name }}</h4> -->
        <!-- </router-link> -->
      </div>
      <div>
        <span style="font-size: 3em; color: Mediumslateblue;">
          <i class="fas fa-shoe-prints"></i>
        </span>
          <!-- <router-link v-for="stamp in stampList" :key="stamp.sno" to="" class="link-text"> -->
          <SlideList/>
            <!-- <img src="stamp.cafeno" alt="stamp cafe image"> -->
            <!-- <h4>{{ stamp.cafeno.name }}</h4> -->
        <!-- </router-link> -->
      </div>
  </div>
</template>

<script>
import SlideList from '@/components/SlideList.vue'

import { mapState, mapActions } from 'vuex'

export default {
  name: "MyPageView",
  components: {
    SlideList
  },
  
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