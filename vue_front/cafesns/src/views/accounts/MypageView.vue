<template>
    <div>
        <!-- <div>{{ followingList }}</div>
        <div>{{ followerList }}</div> -->
        <v-row>
          <h1>{{ userData.id.split('@')[0] }}</h1>
          <v-btn @click="followUser(userData.id)">
            <h2>follow</h2>
          </v-btn>
          <v-spacer></v-spacer>
          <FollowingList/>
          <FollowerList/>
        </v-row>
        <!-- <router-link to="" class="link-text"><v-btn text>Posts: { PostList.length }</v-btn></router-link>  -->
      <v-divider></v-divider>
      <div>
        <span style="font-size: 3em; color: Tomato;">
          <i class="fas fa-heart"></i>
        </span>
        <!-- <router-link v-for="like in likeList" :key="like.lno" to="" class="link-text"> -->
          <div class="home text-center"><LikeList/></div>
          <!-- <img src="like.cafeno" alt="like cafe image"> -->
          <!-- <h4>{{ like.cafeno.name }}</h4> -->
        <!-- </router-link> -->
      </div>
      <div>
        <span style="font-size: 3em; color: Mediumslateblue;">
          <i class="fas fa-shoe-prints fa-rotate-270"></i>
        </span>
          <!-- <router-link v-for="stamp in stampList" :key="stamp.sno" to="" class="link-text"> -->
          <div class="home text-center"><StampList/></div>
            <!-- <img src="stamp.cafeno" alt="stamp cafe image"> -->
            <!-- <h4>{{ stamp.cafeno.name }}</h4> -->
        <!-- </router-link> -->
      </div>
      <div>{{ likeList }}</div>
      <br>
      <div>{{ stampList }}</div>
  </div>
</template>

<script>
import LikeList from '@/components/LikeList.vue'
import StampList from '@/components/StampList.vue'
import FollowingList from '@/components/FollowingList.vue'
import FollowerList from '@/components/FollowerList.vue'

import { mapState, mapActions } from 'vuex'

export default {
  name: "MyPageView",
  data() {
    return {
      currentUserId : this.$route.params.user_id
    }
  },
  components: {
    LikeList,
    StampList,
    FollowingList,
    FollowerList,
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
      'fetchLikeList', 
      'fetchStampList', 
      'fetchFollowingList',
      'fetchFollowerList',
      'followUser',
    ])
  },
  
  created() {
    this.fetchUserData(this.currentUserId)
      this.fetchLikeList()
      this.fetchStampList()
      this.fetchFollowingList()
      this.fetchFollowerList()
  },
  watch : {
    '$route.params.user_id' : 'fetchUserData'
  },
}
</script>

<style>

</style>