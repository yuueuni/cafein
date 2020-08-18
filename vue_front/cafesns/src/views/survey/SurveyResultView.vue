<template>
	<div>
		<h1>RESULT</h1>
		<h2>{{ surveyState }}</h2>
    <v-row justify="center">
    <v-dialog
      v-model="dialog"
      max-width="700"
    >
      <v-card>
        <v-card-title v-if="this.surveyState === 1" class="headline"><img src="@/assets/result/coffee.jpg" alt="" width="500"></v-card-title>
        <v-card-text v-if="this.surveyState === 1">
          Let Google help apps determine location. This means sending anonymous location data to Google, even when no apps are running.
        </v-card-text>

        <v-card-title v-if="this.surveyState === 2" class="headline"><img src="@/assets/result/hot.jpg" alt="" width="500"></v-card-title>
        <v-card-text v-if="this.surveyState === 2">
          Let Google help apps determine location. This means sending anonymous location data to Google, even when no apps are running.
        </v-card-text>

        <v-card-title v-if="this.surveyState === 3" class="headline"><img src="@/assets/result/dessert.jpg" alt="" width="500"></v-card-title>
        <v-card-text v-if="this.surveyState === 3">
          Let Google help apps determine location. This means sending anonymous location data to Google, even when no apps are running.
        </v-card-text>

        <v-card-title v-if="this.surveyState === 4" class="headline"><img src="@/assets/result/images.jpg" alt="" width="500"></v-card-title>
        <v-card-text v-if="this.surveyState === 4">
          Let Google help apps determine location. This means sending anonymous location data to Google, even when no apps are running.
        </v-card-text>

        <v-card-title v-if="this.surveyState === 5" class="headline"><img src="@/assets/result/random.jpg" alt="" width="500"></v-card-title>
        <v-card-text v-if="this.surveyState === 5">
          Let Google help apps determine location. This means sending anonymous location data to Google, even when no apps are running.
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
            @click="dialog = false"
          >
            Agree
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
  </div>
</template>


<script>
import { mapState } from 'vuex'

export default {
  name: 'LoginView',
  data () {
      return {
        dialog: false,
      }
    },
	computed: {
		...mapState([
			'surveyState',
		])
  },
  created() {
    this.dialog = true
  }
}
</script>