<template>
  <v-slide-group
    class="pa-4"
    active-class="secondary"
    show-arrows
  >
    <v-slide-item
      v-for="cafe in newCafeList"
      :key="cafe.cafeno"
      v-slot:default="{ toggle }"
    >
      <v-card
        class="ma-4"
        @click="toggle"
      >
        <v-img
          height="200px"
          width="200px"
          class="grey lighten-2"
          :src="'http://i3a203.p.ssafy.io:5000/api/cafe/get/image/'+cafe.cafeno"
          @click="onSelectCafe(cafe.cafeno)"
        >
          <v-row align="end" class="lightbox white--text fill-height">
            <v-col style="background:#00000080">
              <div v-if="cafe.name.length<10" class="subheading">{{ cafe.name }}</div>
              <div v-else class="subheading">{{ cafe.name.substring(0, 10) + "..." }}</div>
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
    }
  },
  computed: {
    ...mapState([
      'newCafeList'
    ])
  },
  methods: {
    ...mapActions([
      'fetchNewCafeList',
    ]),
    onSelectCafe(target) {
      this.$router.push(`/cafe/detail/${target}`)
    },
  },
  created() {
    this.fetchNewCafeList(3)
  }
}
</script>
