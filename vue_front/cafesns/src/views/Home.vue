<template>
  <div class="home text-center">
    <!-- cafe -->
    <v-card outlined class="card-list">
      <h1>Cafes</h1>
      <CafeList/>
        <div
          class="d-flex display-3"
          style="height: 100%;"
        >
      </div>
    </v-card>
    
    <!-- post -->
    <!-- <v-hover v-slot:default="{ hover }"> -->
      <v-card outlined class="card-list">
          <h1>Posts</h1>
          <PostList/>
        <!-- <v-expand-transition> -->
          <div
            class="d-flex display-3"
            style="height: 100%;"
          >
          </div>
        <!-- </v-expand-transition> -->
      </v-card>
    <!-- </v-hover> -->

    <!-- surveyResultRecommend -->
    <v-card outlined class="card-list">
      <h1>'유형이름'에게 추천하는 카페</h1>
      <SurveyRecommendList/>
        <div
          class="d-flex display-3"
          style="height: 100%;"
        >
      </div>
    </v-card>

    <!-- likeRecommend -->
    <v-card outlined class="card-list">
      <h1><i class="fas fa-heart fa-1x" style="color: #ef9a9a"></i> 많은 카페 </h1>
      <MostLikeRecommendList/>
        <div
          class="d-flex display-3"
          style="height: 100%;"
        >
        </div>
    </v-card>

    <v-card outlined class="card-list">
      <h1>최근 <i class="fas fa-heart fa-1x" style="color: #ef9a9a"></i> 카페</h1>
      <RecentLikeRecommendList/>
        <div
          class="d-flex display-3"
          style="height: 100%;"
        >
        </div>
    </v-card>

    <!-- stampRecommend -->
    <v-card outlined class="card-list">
      <h1>최다 <i class="fas fa-shoe-prints fa-rotate-270 fa-1x" style="color: #90caf9"></i> 카페</h1>
      <MostStampRecommendList/>
        <div
          class="d-flex display-3"
          style="height: 100%;"
        >
        </div>
    </v-card>

    <v-card outlined class="card-list">
      <h1>요즘 <i class="fas fa-shoe-prints fa-rotate-270 fa-1x" style="color: #90caf9"></i> 카페</h1>
      <RecentStampRecommendList/>
        <div
          class="d-flex display-3"
          style="height: 100%;"
        >
        </div>
    </v-card>


        <!-- geo -->
    <v-hover v-slot:default="{ hover }">
      <v-card outlined class="card-list">
          <h1>내 주위 근처 카페</h1>
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
  </div>
</template>

<script>
// @ is an alias to /src
import CafeList from '@/components/CafeList.vue'
import PostList from '@/components/PostList.vue'
import GeoList from '@/components/GeoList.vue'

import SurveyRecommendList from '@/components/recommend/SurveyRecommendList.vue'
import MostLikeRecommendList from '@/components/recommend/MostLikeRecommendList.vue'
import RecentLikeRecommendList from '@/components/recommend/RecentLikeRecommendList.vue'
import MostStampRecommendList from '@/components/recommend/MostStampRecommendList.vue'
import RecentStampRecommendList from '@/components/recommend/RecentStampRecommendList.vue'

export default {
  name: 'Home',
  components: {
    CafeList,
    PostList,
    GeoList,
    SurveyRecommendList,
    MostLikeRecommendList,
    RecentLikeRecommendList,
    MostStampRecommendList,
    RecentStampRecommendList,
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
