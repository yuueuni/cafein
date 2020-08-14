<template>
  <div>
    <v-card
      class="mx-auto my-12 px-3"
    >
      <v-row>
        <v-col
          cols="6"  
        >
          <!-- cafe image -->
          <v-img
            height="250"
            class="grey lighten-2"
            :src="'http://i3a203.p.ssafy.io:5000/api/cafe/get/image/'+selectedCafe.cafeno"
          >
          </v-img>
          <v-row align="end"> 
            <v-card-title v-if="cafeKeywords">cafe keywords</v-card-title>
            <v-col class="text-end">
              <!-- like -->
              <v-tooltip top>
                <template v-slot:activator="{on}">
                  <v-btn v-if="liked" v-on="on" icon color="red lighten-3" @mouseover="likeExplanation()" @click="thisViewLikeCafe(selectedCafe.cafeno)" class="mr-2">
                    <i class="fas fa-heart fa-2x"></i>
                  </v-btn>
                  <v-btn v-else v-on="on" icon color="grey lighten-1" @click="thisViewLikeCafe(selectedCafe.cafeno)" class="mr-2">
                    <i class="fas fa-heart fa-2x"></i>
                  </v-btn>
                </template>  
                <span>방문하고 싶은 카페</span>
              </v-tooltip>
              <!-- stamp -->
              <v-tooltip top>
                <template v-slot:activator="{on}">
                  <v-btn v-if="stamped" v-on="on" icon color="blue lighten-3" @click="thisViewStampCafe(selectedCafe.cafeno)" class="mx-1">
                    <i class="fas fa-shoe-prints fa-rotate-270 fa-2x"></i>
                  </v-btn>
                  <v-btn v-else v-on="on" icon color="grey lighten-1" @click="thisViewStampCafe(selectedCafe.cafeno)" class="mx-1">
                    <i class="fas fa-shoe-prints fa-rotate-270 fa-2x"></i>
                  </v-btn>
                </template>
                <span>방문한 카페</span>
               </v-tooltip>
            </v-col>
          </v-row>
          <v-chip 
            v-for="keyword in cafeKeywords"
            :key="keyword.keyno"
            class="keyword ma-2"
          >{{ keyword.keyword }}
          </v-chip>
        </v-col>

        <v-col cols="6">
          <v-card-title>
            <h1>{{ selectedCafe.name }}</h1>
          </v-card-title>
          <v-card-text>
            <span class="my-4 subtitle-1">tel) {{ selectedCafe.tel }}</span>
          </v-card-text>
          <v-card-title>cafe open - close</v-card-title>
          <v-card-text>{{ selectedCafe.business_hours }}</v-card-text>
          <v-card-title>cafe address</v-card-title>
          <v-card-text>{{ selectedCafe.address }}</v-card-text>

        </v-col>
      </v-row>

      <v-card-actions>
        <v-spacer></v-spacer>
        <router-link to="/post/create" class="text-decoration-none">
          <v-btn
            color="deep-purple lighten-2"
            text
          >
            new post
          </v-btn>
        </router-link>
      </v-card-actions>
    </v-card>
    <PostList/>
  </div>
</template>

<script>
import { mapState, mapActions, mapGetters } from 'vuex'
import PostList from '@/components/cafe/PostList.vue'

export default {
  name: 'CafeDetail',
  components: {
    PostList,
  },
  data() {
    return {
      cafeId: this.$route.params.cafe_id,
      active: 1,
      keywords: ['coffee', 'drink', 'dessert'],
      liked: null,
      stamped: null,
    }
  },
  computed: {
    ...mapState([
      'selectedCafe',
      'cafeKeywords',
    ]),
    ...mapGetters(['isLoggedIn']),
  },
  methods: {
    ...mapActions([
      'cafeDetail',
      'aboutLike',
      'aboutStamp', 
      'likeCafe',
      'stampCafe',
    ]),
    thisViewLikeCafe(data) {
      this.likeCafe(data)
        .then(res => {
          this.liked = !res
          console.log(res, this.liked) 
        })
    },
    thisViewStampCafe(data) {
      this.stampCafe(data)
        .then(res => {
          this.stamped = !res
          console.log(res, this.stamped) 
        })
    },
  },
  beforeUpdate() {
    this.aboutLike(this.cafeId)
      .then(res => this.liked = res)
    this.aboutStamp(this.cafeId)
      .then(res => this.stamped = res)
  },
  created() {
    this.cafeDetail(this.cafeId)
  },
}
</script>

<style scoped>
.keyword :hover {
  cursor: pointer;
}
</style>