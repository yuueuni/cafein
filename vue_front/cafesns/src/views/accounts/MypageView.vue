<template>
    <div>
        <!-- <div>{{ followingList }}</div>
        <div>{{ followerList }}</div> -->
        <v-row>
          <h1 class="px-3">{{ userData.id.split('@')[0] }}</h1>
          <v-btn class="px-1" @click="followUser(profileUserId)">
            <h2 v-if="followState">follow</h2>
            <h2 v-else>Unfollow</h2>
          </v-btn>
          <v-spacer></v-spacer>
          <FollowingList/>
          <FollowerList/>
          <UserPostList/>
        </v-row>
        <!-- <router-link to="" class="link-text"><v-btn text>Posts: { PostList.length }</v-btn></router-link>  -->
      <v-divider></v-divider>
      <div>
        <span style="font-size: 3em">
          <i class="fas fa-heart" style="color: #ef9a9a"></i>
        </span>
          <div class="home text-center"><LikeList/></div>
      </div>
      <div>
        <span style="font-size: 3em">
          <i class="fas fa-shoe-prints fa-rotate-270" style="color: #90caf9"></i>
        </span>
          <div class="home text-center"><StampList/></div>
      </div>
      <!-- <div>{{ likeList }}</div>
      <br>
      <div>{{ stampList }}</div> -->
  </div>
</template>

<script>
import LikeList from '@/components/user_profile/LikeList.vue'
import StampList from '@/components/user_profile/StampList.vue'
import FollowingList from '@/components/user_profile/FollowingList.vue'
import FollowerList from '@/components/user_profile/FollowerList.vue'
import UserPostList from '@/components/user_profile/UserPostList.vue'

import { mapState, mapActions } from 'vuex'

export default {
  name: "MyPageView",
  data() {
    return {
      profileUserId : null,
    }
  },

  components: {
    LikeList,
    StampList,
    FollowingList,
    FollowerList,
    UserPostList,
  },
  
  computed: {
    ...mapState([
      'userData',
      'currentUser',
      'likeList', 
      'stampList', 
      'followState',
      'followingList', 
      'followerList',
      'postList',
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
    ]),
    showUserProfile() {
      this.profileUserId = this.$route.params.user_id
      this.fetchUserData(this.profileUserId)
        .then(() => {
          this.fetchLikeList()
          this.fetchStampList()
          this.fetchFollowingList()
          this.fetchFollowerList()
        })
    },
    
  },

  created() {
    this.showUserProfile()
    // console.log(typeof(this.currentUser))
    // console.log(this.currentUser.toString() in this.followerList)
  },

  watch: {
    '$route' (to, from) {
      if (to !== from ) {
        this.showUserProfile()
      }
    }
  },
}
</script>

<style>

</style>