<template>
  <div>
    <v-text-field
      label="Content" 
      v-model="commentData.content"
      id="content"
    >
    </v-text-field>
    <v-btn color="secondary" @click="createComment(commentData)">등록</v-btn>
  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  name: 'CommentCreate',
  data() {
    return {
      commentData: {
        content: null,
      }
    }
  },
  methods: {
    ...mapActions(['createComment'])
  }

}
</script>

<style>

</style>