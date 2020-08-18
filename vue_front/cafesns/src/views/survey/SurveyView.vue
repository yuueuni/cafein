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
    <v-stepper v-model="survey" class="text-center">
      <v-stepper-items>
        <v-stepper-content
          v-for="(item, i) in surveyJSON.elements"
          :key="i"
          :step="i"
        >
          <p v-html="item.title"></p>
          <div v-if="i == 0">
            <v-btn text @click="survey = 1">next</v-btn>
          </div>
          <!-- answer -->
          <div
            v-else
            v-for="(choice, c) in item.choices"
            :key="c"
          >
            <v-btn v-if="item.name==priority" @click="priority=choice.value, survey += 1" width="70%">{{ choice.text }}</v-btn>
            <v-btn v-else @click="target=choice.value, onNext(i)" width="70%">{{ choice.text }}</v-btn>
            <!-- <v-img
          :src="'https://i3a203.p.ssafy.io:5000/api/cafe/get/image/'+cafe.cafeno"
          height="170px"
          width="170px"
          @click="onSelectCafe(cafe.cafeno)"
        > -->
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
            "name":"question1",
            "title":" 당신은 숲 속 길을 걷던 중 집을 발견했다. <br> 이 집에서 가장 눈에 띄는 것은?",
            "choices":[
              {
                "value": 1,
                "text":"동네 사람들이 모여 바베큐 파티를 하고 있는 모습"
              },
              {
                "value": 2,
                "text":"편안한 소파와 푹신한 침대"
              },
              {
                "value": 3,
                "text":"식탁 위에 먹음직스러운 음식들"
              },
              {
                "value": 4,
                "text":"평범한 가정집이 아닌 게스트하우스 같은 모습"
              },
              {
                "value": 5,
                "text":"우와 집이다. 하고 그냥 가던 길 간다"
              },
            ]
          },
          {
            "name":"question2",
            "title":"선물을 고를 때 당신의 취향은?",
            "choices":[
              {
                "value": 1,
                "text":"핵인싸템"
              },
              {
                "value": 2,
                "text":"꽃과 손편지"
              },
              {
                "value": 3,
                "text":"축하 케이크"
              },
              {
                "value": 4,
                "text":"직접만든 향수"
              },
              {
                "value": 5,
                "text":"카카오페이 송금"
              },
            ]
          },
          {
            "name":"question3",
            "title":"당신의 영화 취향은?",
            "choices":[
              {
                "value": 1,
                "text": "@/assets/cafeIn_logo.png"
              },
              {
                "value": 2,
                "text":"꽃과 손편지"
              },
              {
                "value": 3,
                "text":"축하 케이크"
              },
              {
                "value": 4,
                "text":"직접만든 향수"
              },
              {
                "value": 5,
                "text":"카카오페이 송금"
              },
            ]
          },
          {
            "name":"question4",
            "title":"회사 첫 출근을 위해 출근룩을 준비하는 당신은?",
            "choices":[
              {
                "value": 1,
                "text":"SNS를 켜서 #출근룩 검색 후 고대로 사러간다."
              },
              {
                "value": 2,
                "text":"집에 있던 정장룩을 꺼내 정성스럽게 다려놓는다."
              },
              {
                "value": 3,
                "text":"출근룩보단 내가 쓸 사무실 개인용품 챙기기(feat.텀블러, 슬리퍼, 쿠션)"
              },
              {
                "value": 4,
                "text":"미생을 보며 최애 캐릭터룩을 따라입는다."
              },
              {
                "value": 5,
                "text":"옷장 처음 열었을 때 손길이 닿는 거를 운명으로 알고 대충 주워 입는다."
              },
            ]
          },
          {
            "name":"question5",
            "title":"밖에 나갔다 집에 들어와 바로 하는 행동은?",
            "choices":[
              {
                "value": 1,
                "text":"저녁 1깡을 실천한다."
              },
              {
                "value": 2,
                "text":"씻고 옷을 갈아입는다."
              },
              {
                "value": 3,
                "text":"출출한데 뭐 먹지? 냉장고부터 연다."
              },
              {
                "value": 4,
                "text":"강아지랑 논다."
              },
              {
                "value": 5,
                "text":"일단 눕고 생각한다."
              },
            ]
          },
          {
            "name":"priority",
            "title":"우선순위",
            "choices": [
              {
                "value": 1,
                "text":"핵인싸"
              },
              {
                "value": 2,
                "text":"커피"
              },
              {
                "value": 3,
                "text":"디저트"
              },
              {
                "value": 4,
                "text":"테마"
              },
              {
                "value": 5,
                "text":"귀차니즘"
              },
            ]
          },
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