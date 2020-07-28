<template>
  <div>
    <v-card
      class="ma-4"
      v-for="cafe in cafeList"
      :key="cafe.id"
    >
      <div class="subheading">
        <v-btn @click="onSelectCafe(cafe.cafeno)" text>{{ cafe.name }}</v-btn>
      </div>
    </v-card>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'

export default {
  name: 'CafeList',
  computed: {
    ...mapState(['cafeList'])
  },
  methods: {
    ...mapActions(['fetchCafeList']),
    onSelectCafe(target) {
      this.$router.push(`/cafe/detail/${target}`)
    }
  },
  created() {
    this.fetchCafeList()
  },
}
</script>

<style>

</style>