<template>
  <div class="home text-center">
    <!-- cafe -->
    <v-hover v-slot:default="{ hover }">
        <v-card outlined class="card-list">
            <h1>Cafe</h1>
            <SlideList/>
          <v-expand-transition>
            <div
              v-if="!hover"
              class="d-flex white v-card--reveal display-3"
              style="height: 100%;"
            >
              Cafe
            </div>
          </v-expand-transition>
        </v-card>
    </v-hover>
    <!-- post -->
    <v-hover v-slot:default="{ hover }">
        <v-card outlined class="card-list">
            <h1>Post</h1>
            <SlideList/>
          <v-expand-transition>
            <div
              v-if="!hover"
              class="d-flex white v-card--reveal display-3"
              style="height: 100%;"
            >
              Post
            </div>
          </v-expand-transition>
        </v-card>
    </v-hover>
  </div>
</template>

<script>
// @ is an alias to /src
import SlideList from '@/components/SlideList.vue'

export default {
  name: 'Home',
  components: {
    SlideList
  },
  methods: {
    checkLogin() {
      alert('로그인이 필요합니다 !')
      this.$router.push({ name: 'Login'})
    }
  }
}
</script>

<style scoped>
.link-text {
  color: black;
  text-decoration: none;
  text-transform: capitalize;
}

.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  position: absolute;
  opacity: 0.9;
  width: 100%;
}

.card-list {
  border: none;
}

</style>
