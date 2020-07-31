<template>
  <div>
    작성자 | 댓글 내용
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'

export default {
  name: 'ReviewList',
  computed: {
    ...mapState(['comments'])
  },
  methods: {
    ...mapActions(['fetchComments'])
  },
  created() {
    this.fetchComments()
  }

}
</script>

<style>

</style>