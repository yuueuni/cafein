<template>
  <div>
    <div>
      <router-link to="/accounts/signup">Signup</router-link> |
      <router-link to="/accounts/login">Login</router-link> |
      <!-- <router-link to="/accounts/logout">Logout</router-link> -->
      <router-link to="/accounts/mypage">Mypage</router-link> 
    </div>
    <router-view/>
  </div>
  
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
