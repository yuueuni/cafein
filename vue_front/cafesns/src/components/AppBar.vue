<template>
  <v-app-bar
      fixed
      color="#BCAAA4"
      dark
      hide-on-scroll
    >
      <v-toolbar-title><router-link to="/" class="link-text">Cafe人</router-link></v-toolbar-title>
      <v-spacer></v-spacer>
      <router-link v-if="!isLoggedIn" to="/accounts/login" class="link-text"><v-btn text>Login</v-btn></router-link>
      <router-link v-if="!isLoggedIn" to="/accounts/signup" class="link-text"><v-btn text>Signup</v-btn></router-link>
      <v-btn v-if="isLoggedIn" @click="onMypage(currentUser)" text>Mypage</v-btn>
      <!-- <router-link to="/post/create" class="link-text"><v-btn text>New Post</v-btn></router-link> -->
      <router-link to="/search" class="link-text"><v-btn text>search</v-btn></router-link>
      <router-link v-if="isLoggedIn" to="/accounts/logout" @click.native="logout" class="link-text"><v-btn text>Logout</v-btn></router-link>
    </v-app-bar>
</template>

<script>
import { mapGetters, mapActions, mapState } from 'vuex';

export default {
  name: 'AppBar',

  computed: {
    ...mapGetters(['isLoggedIn']),
    ...mapState(['currentUser']),
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
}
</style>