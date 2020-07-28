<template>
  <v-slide-group
    class="pa-4"
    active-class="secondary"
    show-arrows
  >
    <v-slide-item
      v-for="cafe in cafeList"
      :key="cafe.id"
      v-slot:default="{ active, toggle }"
    >
      <v-card
        :color="active ? 'primary' : 'grey' "
        class="ma-4"
        @click="toggle"
      >
        <v-img
          :src="`https://picsum.photos/200/300?image=${getImage()}`"
          height="200px"
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
  computed: {
    ...mapState(['cafeList'])
  },
  methods: {
    ...mapActions(['fetchCafeList']),
    getImage () {
      const min = 550
      const max = 560
      return Math.floor(Math.random() * (max - min + 1)) + min
    },
    onSelectCafe(target) {
      this.$router.push(`/cafe/detail/${target}`)
    }
  },
  created() {
    this.fetchCafeList()
  }
}
</script>
