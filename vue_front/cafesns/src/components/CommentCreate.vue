<template>
  <v-layout row>
    <v-flex>
      <v-text-field
        label="Content" 
        v-model="commentData.contents"
        id="content"
        append-outer-icon
      >
      </v-text-field>
      <v-btn color="secondary" @click="createComment(commentData)">등록</v-btn>
    </v-flex>
  </v-layout>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  name: 'CommentCreate',
  data() {
    return {
      commentData: {
        pno: this.$route.params.post_id,
        contents: null,
        uid: null,
      }
    }
  },
  methods: {
    ...mapActions(['createComment'])
  },

}
</script>

<style>

</style>