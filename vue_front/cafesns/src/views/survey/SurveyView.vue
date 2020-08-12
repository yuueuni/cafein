<template>
  <v-overlay :value="overlay" style="background:#2c001e;">
    <v-carousel hide-delimiters>
      <v-carousel-item
        v-for="(item,i) in items"
        :key="i"
        :src="item.question"
        
      >
      <h2 v-html="item.question"></h2>
      </v-carousel-item>
    </v-carousel>
    <v-btn rounded text @click="$router.push(`/`)">skip</v-btn>
  </v-overlay>
</template>

<script>
  export default {
    data () {
      return {
        activeColor: "#2c001e",
        overlay: true,
        items: [
          { 
            question:
              'cafe人에 오신 것을 환영합니다. <br> cafe人 서비스는 성향테스트 결과에 따른 <br> 맞춤형 카페를 추천해드립니다. <br> 설문을 진행하시겠습니까?'
          },
          {
            question: '다음 중 선호하는 카페 유형을 선택해주세요.',
          },
          {
            question: 'https://cdn.vuetifyjs.com/images/carousel/planet.jpg',
          },
          {
            question: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg',
          },
          {
            question: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg',
          },
          {
            question: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg',
          },
        ],
      }
    
    },
  }
</script>



<style scoped>
.backgroundcolor{
  background: #2c001e
}
</style>