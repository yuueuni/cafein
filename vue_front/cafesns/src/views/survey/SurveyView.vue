<template>
  <div id="surveyContainer">
    <v-dialog
      v-model="dialog"
      transition="dialog-bottom-transition"
      persistent
      width="600px"
      overlay-color="#2c001e"
      overlay-opacity="0"
      class="py-4"
    >
    <v-stepper v-model="survey">
      <v-stepper-items>
        <v-stepper-content
          v-for="(item, i) in items"
          :key="i"
          :step="i"
        >
          <p v-html="item"></p>
          <div v-if="i == 0">
            <v-btn @click="survey = 1">next</v-btn>
          </div>
          <!-- answer -->
          <div v-else>
            <v-btn @click="target=1, onNext(i)" width="100%">1</v-btn>
            <v-btn @click="target=2, onNext(i)" width="100%">2</v-btn>
            <v-btn @click="target=3, onNext(i)" width="100%">3</v-btn>
            <v-btn @click="target=4, onNext(i)" width="100%">4</v-btn>
            <v-btn @click="target=5, onNext(i)" width="100%">5</v-btn>
          </div>
          
        </v-stepper-content>
        <div class="text-right">
          <v-btn v-if="survey == 0" class="ma-2" rounded text dark color="red" @click="$router.push(`/`)">skip</v-btn>
        </div>
      </v-stepper-items>
    </v-stepper>
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
      survey: 0,
      target: null,
      answer: {
        1: null,
        2: null,
        3: null,
        4: null,
        5: null,
      },
      items: [
          "환영합니다. <br> cafe人 서비스는 <br> 성향테스트 결과에 따른 <br> 맞춤형 카페를 추천해드립니다. <br> 설문을 진행하시겠습니까?",
          '맛 vs 위치',
          '음료 vs 디저트',
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
    toSurveySubmit(questionNo,ans) {
      this.answer[questionNo]= ans
      this.surveySubmit(this.answer)
    },
    sendDataToServer(survey) {
  //send Ajax request to your web server.
    alert("The results are:" + JSON.stringify(survey.data))
    },
    onNext(i) {
      this.answer[i] = this.target
      if (i === this.items.length-1) {
        this.surveySubmit(this.answer)
      } else {
        this.survey = i + 1
      }
    },
  },
  
}
</script>


<style scoped>
.background_color {
  color: white;
}
</style>