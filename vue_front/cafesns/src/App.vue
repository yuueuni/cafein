<template>
  <v-app>
    <AppBar class="d-block"/>
    <v-main>
      <v-container class="mt-16">
        <router-view/>
        <router-view v-if="hidden"/>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import AppBar from './components/AppBar.vue'

export default {
  name: 'App',
  components: {
    AppBar,
  },
  data() {
    return {
      hidden: false,
    }
  }

};
</script>

<style>
.router-link-exact-active {
  color: black;
}
</style>