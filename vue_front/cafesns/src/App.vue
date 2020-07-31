<template>
  <v-app>
    <AppBar class="d-block"/>
    <v-main>
      <v-container class="mt-16">
        <router-view/>
      </v-container>
    </v-main>

    <v-footer
      class="font-weight-medium mt-10"
    >
      <v-col
        class="text-center"
        cols="12"
      >
        &copy; {{ new Date().getFullYear() }} — <strong>Cafe人</strong>
      </v-col>
    </v-footer>
  </v-app>
</template>

<script>
import AppBar from '@/components/AppBar.vue'

export default {
  name: 'App',
  components: {
    AppBar,
  },
};
</script>

<style>

</style>