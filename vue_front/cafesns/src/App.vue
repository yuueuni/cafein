<template>
  <v-app>
    <v-app-bar
      fixed
      color="#BCAAA4"
      dark
      hide-on-scroll
    >
      <v-toolbar-title><router-link to="/" class="link-text">Cafe SNS</router-link></v-toolbar-title>
      <v-spacer></v-spacer>
      <router-link v-if="!isLoggedIn" to="/accounts/signup" class="link-text"><v-btn text>Signup</v-btn></router-link>
      <router-link v-if="!isLoggedIn" to="/accounts/login" class="link-text"><v-btn text>Login</v-btn></router-link>
      <!-- <router-link v-if="isLoggedIn" to="/accounts/logout" @click.natice="logout" class="link-text"><v-btn text>Logout</v-btn></router-link> -->
      <router-link to="/accounts/mypage" class="link-text"><v-btn text>Mypage</v-btn></router-link>
      <router-link to="/post/create" class="link-text"><v-btn text>New Post</v-btn></router-link>
    </v-app-bar>
    <v-container class="mt-16" style="min-height:1200px;">
      <router-view/>
    </v-container>
  </v-app>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';

export default {
  name: 'App',

  computed: {
    ...mapGetters(['isLoggedIn'])
  },

  methods: {
    ...mapActions(['logout'])
  },

  mounted() {
    this.isLoggedIn = this.$cookies.isKey('auth-token')
  }
};
</script>

<style scoped>
.link-text {
  text-decoration: none;
  color: white;
}
</style>
