<template>
  <v-app>
    <v-app-bar
      fixed
      color="#BCAAA4"
      dark
      inverted-scroll
    >
      <v-app-bar-nav-icon></v-app-bar-nav-icon>

      <v-toolbar-title>Cafe SNS</v-toolbar-title>

      <v-spacer></v-spacer>

      <v-btn text><router-link to="/accounts/signup" class="link-text">Signup</router-link></v-btn>
      <v-btn text><router-link to="/accounts/login" class="link-text">Login</router-link></v-btn>
      <!-- <v-btn text><router-link to="/accounts/logout" class="link-text">Logout</router-link></v-btn> -->
      <v-btn text><router-link to="/accounts/mypage" class="link-text">Mypage</router-link></v-btn>

      <v-btn icon>
        <v-icon>mdi-magnify</v-icon>
      </v-btn>

      <v-btn icon>
        <v-icon>mdi-heart</v-icon>
      </v-btn>

      <v-btn icon>
        <v-icon>mdi-dots-vertical</v-icon>
      </v-btn>
    </v-app-bar>
    <div>
    </div>
    <v-container>
      <router-view/>
    </v-container>
  </v-app>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  name: 'App',
  computed: {
    ...mapGetters(['isLoggedIn'])
  },

  methods: {
  },

  mounted() {
    this.isLoggedIn = this.$cookies.isKey('auth-token')
  },

};
</script>

<style scoped>
.link-text {
  text-decoration: none;
  color: white;
}
</style>
