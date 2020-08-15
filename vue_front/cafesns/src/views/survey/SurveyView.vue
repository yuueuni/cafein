<template>
  <div id="surveyContainer">
    <v-dialog
    v-model="dialog"
    transition="dialog-bottom-transition"
    persistent
    max-width="600px"
    overlay-color="#2c001e"
    overlay-opacity="0"
    >
    <v-row justify="space-around">
      <v-icon v-if="question<2" @click="question++, answer[question]= 1;">YES</v-icon>
      <v-icon v-else @click.native="answer[question]= 1, surveySubmit(answer);">YES</v-icon>
        <h2 v-for="(item,i) in items" :key="i" :src="item.question">
          {{ item[question] }} 
        </h2>
      <h1 v-html="item"></h1>
      <h2>{{ answer }}</h2>
      <v-icon v-if="question<2" @click="question++; answer[question]= 0;">NO</v-icon>
      <v-icon v-else @click.native="answer[question]= 0, surveySubmit(answer);">NO</v-icon>
      <v-btn v-if="question!==3" absoute right rounded text dark color="red" @click="$router.push(`/`)">skip</v-btn>
    </v-row>
    </v-dialog>
  </div>
</template>
  
  <!-- <input
      background-color="white"
      color="black"
      label="username"
  > -->

<script>
import { mapState, mapActions } from 'vuex'

// Survey.StylesManager.applyTheme("bootstrap");

export default {
  name: 'LoginView',
  data() {
    return {
      dialog: true,
      question:0,
      answer: {
        1: null,
        2: null,
      },
      items: [
        { 
          0:
            "환영합니다. <br> cafe人 서비스는 <br> 성향테스트 결과에 따른 <br> 맞춤형 카페를 추천해드립니다. <br> 설문을 진행하시겠습니까?"
        },
        {
          1: '맛 vs 위치'
        },
        {
          2: '음료 vs 디저트'
        },
      ],
      // survey : new Survey.Model(surveyJSON),
      // $("#surveyContainer").Survey({
      //   model: survey,
      //   onComplete: sendDataToServer
      // }),
      surveyJSON : {
        "elements":[
          {
            "type":"radiogroup",
            "name":"question1",
            "title":"맛 vs 분위기",
            "choices":[
              {
                "value":"item1",
                "text":"맛"
              },
              {
                "value":"item2",
                "text":"분위기"
              }
            ]
          },
          {
            "type":"radiogroup",
            "name":"question2",
            "title":"커피  vs 디저트",
            "choices":[
              {
                "value":"item1",
                "text":"커피"
              },
              {
                "value":"item2",
                "text":"디저트"
              }
            ]
          }
        ]
      }
    }
  },

  computed: {
    ...mapState([
      'surveyState',
    ])
  },


  methods: {
    ...mapActions(['surveySubmit']),
    sendDataToServer(survey) {
  //send Ajax request to your web server.
    alert("The results are:" + JSON.stringify(survey.data))
    },
  },
  
  created() {
    this.surveyState = []
  },

}
</script>


<style scoped>
.background_color {
  color: white;
}
</style>