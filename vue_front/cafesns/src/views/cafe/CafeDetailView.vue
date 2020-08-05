<template>
  <div>
    <v-card
      class="mx-auto my-12 px-3"
    >
      <v-row>
        <v-col cols="6">
          <!-- cafe image -->
          <v-img
            height="250"
            :src="selectedCafe.thumb"
          >
            <v-row align="end" class="fill-height text-end">
              <v-col>
                <!-- <v-btn icon color="red lighten-3">
                  <v-icon>mdi-heart</v-icon>
                </v-btn> -->
                <!-- :color="active ? 'primary' : 'grey lighten-1'" -->
                <v-btn icon style="font-size: 3em;" :color="active ? 'red lighten-3': 'white'" @click="likeCafe(selectedCafe.cafeno)">
                  <i class="fas fa-heart"></i>
                </v-btn>
                <!-- <button style="font-size: 3em; color: Tomato;" @click="likeCafe(selectedCafe.cafeno)">
                  <i class="fas fa-heart"></i>
                </button> -->
                <!-- <v-btn icon color="blue lighten-3">
                  <v-icon>mdi-bookmark-outline</v-icon>
                </v-btn> -->
                <button style="font-size: 3em; color: Mediumslateblue;" @click="stampCafe(selectedCafe.cafeno)">
                 <i class="fas fa-shoe-prints fa-rotate-270"></i>
                </button>
              </v-col>
            </v-row>
          </v-img>
          <v-card-title>cafe keywords</v-card-title>
          <v-card-text></v-card-text>
        </v-col>

        <v-col cols="6">
          <v-card-title class="display-2">{{ selectedCafe.name }}</v-card-title>
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
        <router-link to="/post/create" class="link-text">
          <v-btn
            color="deep-purple lighten-2"
            text
            @click="cafePost(selectedCafe.cafeno)"
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
import PostList from '@/components/PostList.vue'

export default {
  name: 'CafeDetail',
  components: {
    PostList,
  },
  data() {
    return {
      cafeId: this.$route.params.cafe_id,
      active: 1
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
    cafePost(cafeno) {
      this.$router.push(`/cafe/detail/${cafeno}`)
    }
  },
  created() {
    this.cafeDetail(this.cafeId)
  }
}
</script>

<style>

</style>