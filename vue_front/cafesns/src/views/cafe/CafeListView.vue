<template>
  <v-layout>
    <v-flex>
      <v-row>
        <v-card
          class="ma-4"
          v-for="cafe in cafeList"
          :key="cafe.id"
          @click="onSelectCafe(cafe.cafeno)"
        >
          <v-card-text class="subheading">
            {{ cafe.name }}
          </v-card-text>
        </v-card>
      </v-row>
    <infinite-loading @infinite="infiniteHandler" spinner="waveDots"></infinite-loading>
    </v-flex>
  </v-layout>
</template>

<script>
import { mapState, mapActions } from 'vuex'
import InfiniteLoading from 'vue-infinite-loading'

export default {
  name: 'CafeListView',
  data() {
    return {
      page: 0,
    }
  },
  components: {
    InfiniteLoading,
  },
  computed: {
    ...mapState(['cafeList'])
  },
  methods: {
    ...mapActions(['fetchCafeList']),
    onSelectCafe(target) {
      this.$router.push(`/cafe/detail/${target}`)
    },
    infiniteHandler($state) {
      this.fetchCafeList(this.page)
        .then(() => {
          setTimeout(() => {
            if (Object.keys(this.cafeList).length) {
              $state.loaded()
              this.page += 1
              if (Object.keys(this.cafeList).length / 10 == 0) {
                $state.complete()
              }
            } else {
              $state.complete()
            }
          }, 1000)
        })
        .catch(err => console.log(err))
    }
  },
  created() {
    this.fetchCafeList(this.page)
    this.page += 1
  },
}
</script>

<style>

</style>