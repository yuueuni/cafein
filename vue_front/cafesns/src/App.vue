<template>
  <v-app>
    <v-app-bar
      fixed
      color="#BCAAA4"
      dark
      inverted-scroll
    >
      <v-toolbar-title><router-link to="/" class="link-text">Cafe SNS</router-link></v-toolbar-title>

      <v-spacer></v-spacer>

      <v-btn text><router-link v-if="!isLoggedIn" to="/accounts/signup" class="link-text">Signup</router-link></v-btn>
      <v-btn text><router-link v-if="!isLoggedIn" to="/accounts/login" class="link-text">Login</router-link></v-btn>
      <!-- <v-btn text><router-link v-if="isLoggedIn" to="/accounts/logout" @click.natice="logout" class="link-text">Logout</router-link></v-btn> -->
      <v-btn text><router-link to="/accounts/mypage" class="link-text">Mypage</router-link></v-btn>
      <v-btn text><router-link to="/post/create" class="link-text">New Post</router-link></v-btn>

      <!-- <v-btn icon>
        <v-icon>mdi-magnify</v-icon>
      </v-btn>

      <v-btn icon>
        <v-icon>mdi-heart</v-icon>
      </v-btn>

      <v-btn icon>
        <v-icon>mdi-dots-vertical</v-icon>
      </v-btn> -->

    </v-app-bar>
    <v-container class="mt-14" style="min-height:1200px;">
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
    // this.isLoggedIn = this.$cookies.isKey('auth-token')
  }
};
</script>

<style scoped>
.link-text {
  text-decoration: none;
  color: white;
}
</style>
