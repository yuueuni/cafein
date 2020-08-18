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
          v-for="(item, i) in surveyJSON.elements"
          :key="i"
          :step="i"
        >
          <p v-html="item.title"></p>
          <div v-if="i == 0">
            <v-btn @click="survey = 1">next</v-btn>
          </div>
          <!-- answer -->
          <div
            v-else
            v-for="(choice, c) in item.choices"
            :key="c"
          >
            <v-btn v-if="item.name==priority" @click="priority=choice.value, survey += 1" width="100%">{{ choice.text }}</v-btn>
            <v-btn v-else @click="target=choice.value, onNext(i)" width="100%">{{ choice.text }}</v-btn>
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
      answer: [0, 0, 0, 0, 0],
      priority: "priority",
      surveyJSON : {
        "elements":[
          {
            "name":"question",
            "title":"환영합니다. <br> cafe人 서비스는 <br> 성향테스트 결과에 따른 <br> 맞춤형 카페를 추천해드립니다. <br> 설문을 진행하시겠습니까?",
          },
          {
            "name":"priority",
            "title":"우선순위",
            "choices": [
              {
                "value": 1,
                "text":"맛"
              },
              {
                "value": 2,
                "text":"분위기"
              },
              {
                "value": 3,
                "text":"분위기"
              },
              {
                "value": 4,
                "text":"분위기"
              },
              {
                "value": 5,
                "text":"분위기"
              },
            ]
          },
          {
            "name":"question1",
            "title":"맛 vs 분위기",
            "choices":[
              {
                "value": 1,
                "text":"맛"
              },
              {
                "value": 2,
                "text":"분위기"
              },
              {
                "value": 3,
                "text":"분위기"
              },
              {
                "value": 4,
                "text":"분위기"
              },
              {
                "value": 5,
                "text":"분위기"
              },
            ]
          },
          {
            "name":"question2",
            "title":"커피  vs 디저트",
            "choices":[
              {
                "value": 1,
                "text":"커피"
              },
              {
                "value": 2,
                "text":"디저트"
              },
              {
                "value": 3,
                "text":"분위기"
              },
              {
                "value": 4,
                "text":"분위기"
              },
              {
                "value": 5,
                "text":"분위기"
              },
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

      function getIndex(array, priority) {
        return new Promise((resolve) => {
          const maxNum = Math.max(...array)
          let maxValue = null
          for (let n = 0; n <= array.length; n ++ ) {
            if (array[n] === maxNum) {
              if (!maxValue) {
                maxValue = n + 1
              } else {
                if (n === priority) {
                  maxValue = n + 1
                  resolve(maxValue)
                } else {
                  maxValue = n + 1
                }
              }
            }
          }
          resolve(maxValue)
        })
      }

      async function findValue (array, priority) {
        const getValue = await getIndex(array, priority)
        return getValue
      }
      
      this.answer[this.target-1] += 1
      if (i === this.surveyJSON.elements.length-1) {
        findValue(this.answer, this.priority)
          .then((res) => {
            this.surveySubmit(res)
          })
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