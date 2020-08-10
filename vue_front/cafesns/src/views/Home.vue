<template>
  <div class="home text-center">
    <!-- cafe -->
    <v-hover v-slot:default="{ hover }">
      <v-card outlined class="card-list">
          <h1>Cafes</h1>
          <CafeList/>
        <v-expand-transition>
          <div
            v-if="!hover"
            class="d-flex white v-card--reveal display-3"
            style="height: 100%;"
          >
            <span style="font-family:'BBTreeGR';">Recommend Cafes</span>
          </div>
        </v-expand-transition>
      </v-card>
    </v-hover>
    <!-- post -->
    <v-hover v-slot:default="{ hover }">
      <v-card outlined class="card-list">
          <h1>Posts</h1>
          <PostList/>
        <v-expand-transition>
          <div
            v-if="!hover"
            class="d-flex white v-card--reveal display-3"
            style="height: 100%;"
          >
            <span style="font-family:'BBTreeGR';">Popular posts</span>
          </div>
        </v-expand-transition>
      </v-card>
    </v-hover>
  </div>
</template>

<script>
// @ is an alias to /src
import CafeList from '@/components/CafeList.vue'
import PostList from '@/components/PostList.vue'

export default {
  name: 'Home',
  components: {
    CafeList,
    PostList,
  },
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
