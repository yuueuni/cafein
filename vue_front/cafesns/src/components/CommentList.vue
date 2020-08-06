<template>
  <div>
    <h3>작성자 | 댓글 내용 | 작성시간</h3>
    <div
      v-for="comment in comments"
      :key="comment.key"
    >
      <p>{{ comment.uid }} | {{ comment.contents }} | {{ comment.date }} <v-btn v-if="comment.uid === currentUser" @click="deleteComment(comment.cno)">삭제</v-btn></p>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'

export default {
  name: 'ReviewList',
  data() {
    return {
      postId: this.$route.params.post_id,
    }
  },
  computed: {
    ...mapState([
      'comments',
      'currentUser'
    ])
  },
  methods: {
    ...mapActions([
      'fetchComments',
      'deleteComment',
    ])
  },
  created() {
    this.fetchComments(this.postId)
  }

}
</script>

<style>

</style>