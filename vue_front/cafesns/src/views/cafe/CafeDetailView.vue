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
            contain
            class="grey lighten-2"
            :src="'http://i3a203.p.ssafy.io:5000/api/cafe/get/image/'+selectedCafe.cafeno"
          >
          </v-img>
          <v-row align="end"> 
            <v-card-title>cafe keywords</v-card-title>
            <v-col class="text-end">
              <v-btn icon :color="active ? 'red lighten-3': 'white'" @click="likeCafe(selectedCafe.cafeno)" class="mx-1">
                <i class="fas fa-heart fa-2x"></i>
              </v-btn>
              <v-btn icon :color="active ? 'blue lighten-3': 'white'" @click="stampCafe(selectedCafe.cafeno)" class="mx-1">
                <i class="fas fa-shoe-prints fa-rotate-270 fa-2x"></i>
              </v-btn>
            </v-col>
          </v-row>
          <v-chip-group>
            <v-chip 
              v-for="keyword in keywords"
              :key="keyword"
            >{{ keyword }}
            </v-chip>
          </v-chip-group>
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
import { mapState, mapActions } from 'vuex'
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
      keywords: ['coffee', 'drink', 'dessert']
    }
  },
  computed: {
    ...mapState(['selectedCafe'])
  },
  methods: {
    ...mapActions([
      'cafeDetail', 
      'likeCafe', 
      'stampCafe'
    ]),
  },
  created() {
    this.cafeDetail(this.cafeId)
  }
}
</script>

<style>

</style>