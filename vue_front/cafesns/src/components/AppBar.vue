<template>
<v-bottom-navigation>
  <v-app-bar
      fixed
      color="#BCAAA4"
      dark
      hide-on-scroll
    >
    <v-toolbar-title><router-link to="/" class="link-text"><img src="@/assets/cafeIn_logo.png" alt="logoImage" width="120" my-1 ></router-link></v-toolbar-title>
      <v-spacer></v-spacer>
      <router-link v-if="!isLoggedIn" to="/accounts/login" class="link-text">
        <v-btn><span class="white--text">Login</span><span class="material-icons mx-3 white--text">login</span></v-btn>
      </router-link>
      <router-link v-if="!isLoggedIn" to="/accounts/signup" class="link-text">
        <v-btn><span class="white--text">Signup</span><span class="material-icons mx-3 white--text">group_add</span></v-btn>
      </router-link>
      <router-link v-if="isLoggedIn" :to="`/accounts/${currentUser}`" class="link-text">
        <v-btn><span class="white--text">Mypage</span><span class="material-icons mx-3 white--text">assignment_ind</span></v-btn>
      </router-link>
    <!-- <router-link to="/post/create" class="link-text"><v-btn text>New Post</v-btn></router-link> -->
      <router-link to="/search" class="link-text">
        <v-btn><span class="white--text">Search</span><span class="material-icons mx-3 white--text">search</span></v-btn>
      </router-link>
      <router-link to="/survey" class="link-text">
        <v-btn><span class="white--text">Survey</span><span class="material-icons mx-3 white--text">fact_check</span></v-btn>
      </router-link>
      <router-link v-if="isLoggedIn" to="#" @click.native="logout" class="link-text">
        <v-btn><span class="white--text">Logout</span><span class="material-icons mx-3 white--text">logout</span></v-btn>
      </router-link>
      <router-link to="/bgc" class="link-text">
        <v-btn><span class="white--text">bgc</span></v-btn>
      </router-link>
    </v-app-bar>
  </v-bottom-navigation>
</template>

<script>
import { mapGetters, mapActions, mapState } from 'vuex';

export default {
  name: 'AppBar',

  computed: {
    ...mapState(['currentUser']),
    ...mapGetters(['isLoggedIn']),
  },

  methods: {
    ...mapActions(['logout']),
    onMypage(userid) {
      this.$router.push(`/accounts/${userid}`)
    }
  },

  // mounted() {
  //   this.isLoggedIn = this.$cookies.isKey('auth-token')
  // }
}
</script>

<style scoped>
.link-text {
  text-decoration: none;
  color: white;
  /* #2c001e */
}
</style>