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

    <!-- geo -->
    <v-hover v-slot:default="{ hover }">
      <v-card outlined class="card-list">
          <h1>근처 카페</h1>
          <GeoList/>
        <v-expand-transition>
          <div
            v-if="!hover"
            class="d-flex white v-card--reveal display-3"
            style="height: 100%;"
          >
            <span style="font-family:'BBTreeGR';">Around Cafes</span>
          </div>
        </v-expand-transition>
      </v-card>
    </v-hover>

    <!-- likeRecommend -->
    <v-hover v-slot:default="{ hover }">
      <v-card outlined class="card-list">
          <h1>좋아요 카페 추천</h1>
          <LikeRecommendList/>
        <v-expand-transition>
          <div
            v-if="!hover"
            class="d-flex white v-card--reveal display-3"
            style="height: 100%;"
          >
            <span style="font-family:'BBTreeGR';">LikeRecommendList</span>
          </div>
        </v-expand-transition>
      </v-card>
    </v-hover>

    <!-- stampRecommend -->
    <v-hover v-slot:default="{ hover }">
      <v-card outlined class="card-list">
          <h1>방문 카페 추천</h1>
          <StampRecommendList/>
        <v-expand-transition>
          <div
            v-if="!hover"
            class="d-flex white v-card--reveal display-3"
            style="height: 100%;"
          >
            <span style="font-family:'BBTreeGR';">StampRecommendList</span>
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
import GeoList from '@/components/GeoList.vue'
import LikeRecommendList from '@/components/recommend/LikeRecommendList.vue'
import StampRecommendList from '@/components/recommend/StampRecommendList.vue'

export default {
  name: 'Home',
  components: {
    CafeList,
    PostList,
    GeoList,
    LikeRecommendList,
    StampRecommendList,
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
