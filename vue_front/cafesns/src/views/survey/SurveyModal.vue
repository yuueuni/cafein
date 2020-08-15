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
     
      <v-btn @click="surveySubmit('Y')">예</v-btn>
      <v-btn @click="surveySubmit('N')">아니오</v-btn>
      <v-btn absolute right rounded text @click="$router.push(`/`)">skip</v-btn>
      <v-btn
        class="ma-2"
        :loading="loading"
        :disabled="loading"
        color="success"
        @click="loader = 'loading'"

      >
      추천 카페 보기
      <template v-slot:loader>
        <span>카페 찾는 중...</span>
      </template>
      </v-btn>
   <SurveyResult/>    
  </v-overlay>
</template>


<script>
import { mapActions } from 'vuex'

export default {
  name: 'SurveyResult',
  data () {
    return {
      activeColor: "#2c001e",
      overlay: true,
      items: [
        { 
          question:
            '환영합니다. <br> cafe人 서비스는 <br>  성향테스트 결과에 따른 <br> 맞춤형 카페를 추천해드립니다. <br> 설문을 진행하시겠습니까?'
        },
        {
          question: '맛 vs 위치   dfasdfdfdafadfdfasdsdffffffff'
        },
        {
          question: '음료 vs 디저트   asdfasdfsdfasdfasdfsddsff'
        },
        // {
        //   question: 'https://cdn.vuetifyjs.com/images/carousel/planet.jpg',
        // },
        // {
        //   question: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg',
        // },
        // {
        //   question: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg',
        // },
      ],
      loader: null,
      loading: false,
    }
  },

  methods : {
    ...mapActions([
      'surveySubmit',
    ]),
  },

  watch: {
    loader () {
      const l = this.loader
      this[l] = !this[l]
      setTimeout(() => (this[l] = false), 3000)
      this.loader = null
    },
  },
}
</script>



<style scoped>
.backgroundcolor{
  background: #2c001e
}
.custom-loader {
    animation: loader 1s infinite;
    display: flex;
  }
  @-moz-keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
  }
  @-webkit-keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
  }
  @-o-keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
  }
  @keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
  }
</style>