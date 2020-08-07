<template>
  <v-layout>
    <v-flex>
      <v-text-field
        label="Content" 
        v-model="commentData.contents"
        id="content"
        clearable
        @keypress.enter="checkBeforeCreateComment(commentData)"
      >
      <template v-slot:append-outer>
        <v-btn
          color="secondary"
          @click="checkBeforeCreateComment(commentData)"
        >등록</v-btn>
      </template>
      </v-text-field>
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