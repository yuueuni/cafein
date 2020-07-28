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
            src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
          >
            <v-row align="end" class="fill-height text-end">
              <v-col>
                <v-btn icon color="red lighten-3">
                  <v-icon>mdi-heart-outline</v-icon>
                </v-btn>
                <v-btn icon color="blue lighten-3">
                  <v-icon>mdi-bookmark-outline</v-icon>
                </v-btn>
              </v-col>
            </v-row>
          </v-img>
          <v-card-title>Cafe Name</v-card-title>
          <v-card-text>
            <span class="my-4 subtitle-1">cafe keywords</span>
          </v-card-text>
        </v-col>

        <v-col cols="6">
          <v-card-title>cafe open - close</v-card-title>
          <v-card-text>time 00:00 - 00:00</v-card-text>
          <v-card-title>cafe menu</v-card-title>
          <v-card-text>lists..</v-card-text>
        </v-col>
      </v-row>

      <v-card-actions>
        <v-spacer></v-spacer>
        <router-link to="/post/create" class="link-text">
          <v-btn
            color="deep-purple lighten-2"
            text
            @click="reserve"
          >
            new post
          </v-btn>
        </router-link>
      </v-card-actions>
    </v-card>
    <div>
      <h1>{{ cafe.name }}</h1>
    </div>
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
    }
  },
  computed: {
    ...mapState(['selectedCafe'])
  },
  methods: {
    ...mapActions(['cafeDetail'])
  },
  created() {
    this.selectedCafe(this.cafeId)
  }
}
</script>

<style>

</style>