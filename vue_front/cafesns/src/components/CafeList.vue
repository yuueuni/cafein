<template>
  <v-carousel>
  <!-- <div class="cafeList container my-2"> -->
  <h1 class="pb-2">CafeList</h1>
  <div class="d-flex flex-row cafeList">
    <router-link :to="`/cafe/detail/${cafe.cafeno}`" 
      v-for="cafe in cafeList"
      :key="cafe.id" 
      class="card col-6 col-md-2 px-1 border-0 text-decoration-none"
    >
      <v-img 
        height="200px"
        width="200px"
        class="grey lighten-2 rounded"
        :src="'https://i3a203.p.ssafy.io:5000/api/cafe/get/image/'+cafe.cafeno" 
        alt="cafe image"
      >
      <v-row align="end" class="lightbox white--text fill-height">
        <v-col style="background:#00000080">
          <div v-if="cafe.name.length<10" class="subheading text-center">{{ cafe.name }}</div>
          <div v-else class="subheading text-center">{{ cafe.name.substring(0, 10) + "..." }}</div>
        </v-col>
      </v-row>
      </v-img>
    </router-link>
  </div>
  </v-carousel>
  

  
    <!-- <v-carousel-item
      v-for="(item,i) in items"
      :key="i"
      :src="item.src"
    ></v-carousel-item>
   -->

  <!-- <v-slide-group
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
          height="200px"
          width="200px"
          class="grey lighten-2"
          :src="'https://i3a203.p.ssafy.io:5000/api/cafe/get/image/'+cafe.cafeno"
          @click="onSelectCafe(cafe.cafeno)"
        >
          <v-row align="end" class="lightbox white--text fill-height">
            <v-col style="background:#00000080">
              <div v-if="cafe.name.length<10" class="subheading text-center">{{ cafe.name }}</div>
              <div v-else class="subheading text-center">{{ cafe.name.substring(0, 10) + "..." }}</div>
            </v-col>
          </v-row>
        </v-img>
      </v-card>
    </v-slide-item>
  </v-slide-group> -->
</template>


<script>
import { mapState, mapActions } from 'vuex'

export default {
  name: 'CafeList',
  data () {
      return {
        items: [
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg',
          },
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg',
          },
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/bird.jpg',
          },
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/planet.jpg',
          },
        ],
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

<style scoped>
  .cafeList {
    overflow-x: hidden;
    overflow-y: hidden;
    white-space: nowrap;
  }
  .card {
    white-space: normal;
  }
</style>
