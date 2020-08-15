<template>
    <div>
        <v-row>
          <h1 class="px-3">{{ userData.id.split('@')[0] }}</h1>
          <v-btn v-if="profileUserId !== currentUser" class="px-1" @click="followUser(profileUserId)" small>
            <span v-if="!followState">Follow</span>
            <span v-else>Following</span>
          </v-btn>
          <v-spacer></v-spacer>
          <FollowingList/>
          <FollowerList/>
          <UserPostList/>
        </v-row>
      <v-divider class="mb-3"></v-divider>
      <div>
        <i class="fas fa-heart fa-3x" style="color: #ef9a9a"></i>
        <div class="home text-center">
          <LikeList/>
        </div>
      </div>
      <div>
          <i class="fas fa-shoe-prints fa-rotate-270 fa-3x" style="color: #90caf9"></i>
          <div class="home text-center">
            <StampList/>
          </div>
      </div>
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
      check: 1,
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
      'aboutFollow',
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
    this.aboutFollow(this.$route.params.user_id)
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