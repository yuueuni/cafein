<template>
  <div>
    <span>작성자 | 댓글 내용 | 작성시간</span>
    <div
      v-for="comment in commentList"
      :key="comment.key"
    >
      <v-dialog v-model="dialog" persistent max-width="290">
        <template v-slot:activator="{ on, attrs }">
        <p>
          <v-btn @click="onUserPage(comment.uid)" text>{{ comment.uid }}</v-btn> | {{ comment.contents }} | {{ comment.date }}
          <v-btn
            v-if="comment.uid === currentUser"
            color="primary"
            dark
            v-bind="attrs"
            v-on="on"
            small
          >
            삭제
          </v-btn>
        </p>
        </template>
        <v-card>
          <v-card-title>
            댓글을 삭제하시겠습니까?
          </v-card-title>
          <v-card-actions>
            <v-btn color="green darken-1" text @click="deleteComment(comment.cno), dialog = false">삭제</v-btn>
            <v-spacer></v-spacer>
            <v-btn color="grey darken-1" text @click="dialog = false">취소</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
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
      dialog: false,
    }
  },
  computed: {
    ...mapState([
      'commentList',
      'currentUser'
    ])
  },
  methods: {
    ...mapActions([
      'fetchCommentList',
      'deleteComment',
    ]),
    onUserPage(userid) {
      this.$router.push(`/accounts/${userid}`)
    }
  },
  created() {
    this.fetchCommentList(this.postId)
  }

}
</script>

<style>

</style>