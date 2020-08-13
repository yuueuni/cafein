<template>
  <v-app>
    <AppBar class="d-block"/>
    <v-main>
      <v-container class="mt-16">
        <router-view/>
         <!-- :key="$route.fullPath" -->
      </v-container>
    </v-main>

    <v-footer
      class="font-weight-medium mt-10 white"
    >
      <v-col
        class="text-center"
        cols="12"
      >
        &copy; 2020  <strong>Cafe人: 나를 위한 카페 추천</strong>
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