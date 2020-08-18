<template>
  <v-app-bar
      fixed
      color="#BCAAA4"
      dark
      hide-on-scroll
    >
      <v-toolbar-title><router-link to="/" class="link-text"><img src="@/assets/cafeIn_logo.png" alt="" width="120" my-1 ></router-link></v-toolbar-title>
      <v-spacer></v-spacer>
      <router-link v-if="!isLoggedIn" to="/accounts/login" class="link-text"><span class="material-icons mx-3">login</span></router-link>
      <router-link v-if="!isLoggedIn" to="/accounts/signup" class="link-text"><span class="material-icons mx-3">group_add</span></router-link>
      <router-link v-if="isLoggedIn" :to="`/accounts/${currentUser}`" class="link-text"><span class="material-icons mx-3">assignment_ind</span></router-link>
      <!-- <router-link to="/post/create" class="link-text"><v-btn text>New Post</v-btn></router-link> -->
      <router-link to="/search" class="link-text"><span class="material-icons mx-3">search</span></router-link>
      <router-link to="/survey" class="link-text"><span class="material-icons mx-3">fact_check</span></router-link>
      <router-link v-if="isLoggedIn" to="#" @click.native="logout" class="link-text"><span class="material-icons mx-3">logout</span></router-link>
      
    </v-app-bar>
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