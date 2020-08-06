<template>
  <div>
    <h3>Posts</h3>
    <v-list flat>
      <v-list-item-group>
        <v-list-item
          v-for="post in postList"
          :key="post.pno"
          @click="onSelectPost(post.pno)"
        >
          <v-list-item-avatar>
            <!-- <v-icon class="grey lighten-1 white--text" v-text="folder"></v-icon> -->
            <v-img
              src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
              @click="onSelectPost(post.pno)"
            >
            </v-img>
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title>{{ post.contents }}</v-list-item-title>
            
            <v-list-item-subtitle>{{ post.uid }}</v-list-item-subtitle>
          </v-list-item-content>
          <v-list-item-action>{{ post.date }}</v-list-item-action>
        </v-list-item>
      </v-list-item-group>
    </v-list>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'

export default {
  name: 'PostList',
  computed: {
    ...mapState(['postList'])
  },
  methods: {
    ...mapActions(['fetchList']),
    onSelectPost(postno) {
      this.$router.push(`/post/detail/${postno}`)
    }
  },
  created() {
    this.fetchPostList(this.$route.params.cafe_id)
  },

}
</script>

<style>

</style>