<template>
	<div>
		<h1>RESULT</h1>
		<h2>{{ surveyState }}</h2>
	</div>
</template>


<script>
import { mapState } from 'vuex'

export default {
  name: 'LoginView',

	computed: {
		...mapState([
			'surveyState',
		])
	},
}
</script>