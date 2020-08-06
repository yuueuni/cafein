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
      <v-btn color="secondary" @click="checkBeforeCreateComment(commentData)">등록</v-btn>
    </v-flex>
  </v-layout>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'

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

  computed: {
    ...mapGetters(['isLoggedIn']),
  },

  methods: {
    ...mapActions(['createComment']),
    checkBeforeCreateComment(commentData) {
      if (!this.isLoggedIn) {
        this.$router.push({ name: 'Login'})
      } 
      else {
        this.createComment(commentData)
      }
    },
  },
}
</script>

<style>

</style>