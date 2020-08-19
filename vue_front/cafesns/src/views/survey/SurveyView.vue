<template>
  <div id="surveyContainer">
    <v-dialog
      v-model="dialog"
      transition="dialog-bottom-transition"
      persistent
      width="700px"
      overlay-color="#2c001e"
      overlay-opacity="0"
      class="py-4"
    >
    <v-stepper v-model="survey" color="#2c001e" class="text-center">
      <v-stepper-items color="#2c001e">
        <v-stepper-content
          v-for="(item, i) in surveyJSON.elements"
          :key="i"
          :step="i"
        >
          <h3 v-html="item.title"></h3><br>
          <div v-if="i == 0">
            <v-btn text @click="survey = 1">next</v-btn>
          </div>
          <!-- answer -->
          <!-- question1 && question2 && question4 && question5 -->
          <div
            v-else
            v-for="(choice, c) in item.choices"
            :key="c"
            class="d-inline-block"
          >
            <div v-if="item.name === 'question3'" class="ma-2">
              <v-img
                outlined
                :src="choice.text"
                width="170px"
                @click="target=choice.value; onNext(i);"
              ></v-img>
            </div>
            <div v-else-if="item.name === priority" class="ma-2">
              <v-img
                outlined
                :src="choice.text"
                width="170px"
                @click="priority=choice.value; onNext(i);"
              ></v-img>
            </div>
            <div v-else>
              <v-btn text @click="target=choice.value; onNext(i);">{{ choice.text }}</v-btn>
            </div>
          </div>
          <h1>{{ target }} {{ answer }}</h1>
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

export default {
  name: 'SurveyView',
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
            "title":"환영합니다! <br> cafe人 서비스는 <br> 당신의 성향테스트 결과에 따른 <br> 맞춤형 카페를 추천해드립니다. <br> 설문을 해보시는건 어떤가요?",
          },
          {
            "name":"question1",
            "title":"Q1. 당신은 숲 속 길을 걷던 중 집을 발견했다. <br> 이 집에서 가장 눈에 띄는 것은?",
            "choices":[
              {
                "value": 1,
                "text":"편안한 소파와 푹신한 침대"
              },
              {
                "value": 2,
                "text":"동네 사람들이 모여 바베큐 파티를 하고 있는 모습"
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
            "title":"Q2. 선물을 고를 때 당신의 취향은?",
            "choices":[
              {
                "value": 1,
                "text":"꽃과 손편지"
              },
              {
                "value": 2,
                "text":"핵인싸템"
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
            "title":"Q3. 당신의 영화 취향은?",
            "choices":[
              {
                "value": 1,
                "text": require("@/assets/movies/classic.jpg")
              },
              {
                "value": 2,
                "text": require("@/assets/movies/para.jpg")
              },
              {
                "value": 3,
                "text": require("@/assets/movies/charlie.jpg")
              },
              {
                "value": 4,
                "text": require("@/assets/movies/avengers.jpg")
              },
              {
                "value": 5,
                "text": require("@/assets/movies/now.jpg")
              },
            ]
          },
          {
            "name":"question4",
            "title":"Q4. 회사 첫 출근을 위해 출근룩을 준비하는 당신은?",
            "choices":[
              {
                "value": 1,
                "text":"집에 있던 정장룩을 꺼내 정성스럽게 다려놓는다."
              },
              {
                "value": 2,
                "text":"SNS를 켜서 #출근룩 검색 후 고대로 사러간다."
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
            "title":"Q5. 집에 들어오자마자 바로 하는 행동은?",
            "choices":[
              {
                "value": 1,
                "text":"씻고 옷을 갈아입는다."
              },
              {
                "value": 2,
                "text":"저녁 1깡을 실천한다."
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
            "title":"Q6. 다음 중 1초만에 선택하세요.",
            "choices": [
              {
                "value": 1,
                "text": require("@/assets/priority/hot.jpg")
              },
              {
                "value": 2,
                "text":require("@/assets/priority/coffee.jpg")
              },
              {
                "value": 3,
                "text":require("@/assets/priority/dessert.jpg")
              },
              {
                "value": 4,
                "text":require("@/assets/priority/theme.jpg")
              },
              {
                "value": 5,
                "text":require("@/assets/priority/random.jpg")
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
    onNext(i) {
      function getIndex(array, priority) {
        return new Promise((resolve) => {
          const maxNum = Math.max(...array)
          if (array[priority-1] === maxNum) {
            resolve(priority)
          } else {
            const maxValue = array.indexOf(maxNum) + 1
            resolve(maxValue)
          }
        })
      }

      async function findValue (array, priority) {
        const getValue = await getIndex(array, priority)
        return getValue
      }
      
      if (i === this.surveyJSON.elements.length-1) {
        findValue(this.answer, this.priority)
          .then((res) => {
            this.surveySubmit(res)
          })
      } else {
        this.answer[this.target-1] += 1
        this.survey = i + 1
      }
    },
    
  },
}
</script>

<style scoped>
.background_color {
  color: blue;
}
</style>