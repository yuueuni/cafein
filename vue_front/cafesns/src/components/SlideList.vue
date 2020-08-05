<template>
  <v-slide-group
    class="pa-4"
    active-class="secondary"
    show-arrows
  >
    <v-slide-item
      v-for="cafe in cafeList"
      :key="cafe.id"
      v-slot:default="{ toggle }"
    >
      <v-card
        class="ma-4"
        @click="toggle"
      >
        <v-img
          :src= randomImg
          height="200px"
          width="200px"
          @click="onSelectCafe(cafe.cafeno)"
        >
          <v-row align="end" class="lightbox white--text fill-height">
            <v-col style="background:#00000080">
              <div class="subheading">{{ cafe.name }}</div>
            </v-col>
          </v-row>
        </v-img>
      </v-card>
    </v-slide-item>
  </v-slide-group>
</template>

<script>
import { mapState, mapActions } from 'vuex'

export default {
  name: 'SlideList',
  data() {
    return {
      randomImg : "https://cdn.vuetifyjs.com/images/cards/cooking.png",
      likeImg: null,
      stampImg: null,
    }
  },
  computed: {
    ...mapState([
      'cafeList'
    ])
  },
  methods: {
    ...mapActions([
      'fetchCafeList',
    ]),
    onSelectCafe(target) {
      this.$router.push(`/cafe/detail/${target}`)
    },
  },
  created() {
    this.fetchCafeList(1)
  }
}
</script>
