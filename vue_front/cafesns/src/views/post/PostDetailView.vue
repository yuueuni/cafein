<template>
  <div>
    <v-row justify="center">
      <v-col
        cols="12"
        sm="10"
        md="8"
        lg="6"
      >
        <v-card ref="form" class="px-3">
          <v-card-text class="text-center">
            <v-card-title>
              <h1><v-btn @click="onMypage(selectedPost.uid)" text>{{ selectedPost.uid }}</v-btn></h1>
              <v-spacer></v-spacer>
              <span class="text-subtitle-2"><router-link to="/cafe/detail/${selectedPost.cafeno}`">{{ selectedPost.cafename }}</router-link></span>
            </v-card-title>
            <v-divider class="mb-3"></v-divider>

            <span>맛 {{ selectedPost.taste }}</span>
            <v-rating
              v-model="selectedPost.taste"
              color="yellow darken-3"
              background-color="grey darken-1"
              empty-icon="$ratingFull"
              readonly
            ></v-rating>

            <span>분위기 {{ selectedPost.mood }}</span>
            <v-rating
              v-model="selectedPost.mood"
              color="yellow darken-3"
              background-color="grey darken-1"
              empty-icon="$ratingFull"
              readonly
            ></v-rating>

            <span>위생 {{ selectedPost.clean }}</span>
            <v-rating
              v-model="selectedPost.clean"
              color="yellow darken-3"
              background-color="grey darken-1"
              empty-icon="$ratingFull"
              readonly
            ></v-rating>
          <v-divider></v-divider>
            <p class="my-3 text-start" style="min-height:100px;">{{ selectedPost.contents }}</p>

            <v-img src="https://cdn.vuetifyjs.com/images/cards/cooking.png" contain max-width="100%" max-height="300px"></v-img>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn v-if="selectedPost.uid === currentUser" @click="deletePost(selectedPost.pno)" text color="secondary">삭제</v-btn>
            <v-btn v-if="selectedPost.uid === currentUser" @click="deletePost(selectedPost.pno)" text color="primary">수정</v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
    <CommentCreate/>
    <CommentList/>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from 'vuex'

import CommentCreate from '@/components/CommentCreate.vue'
import CommentList from '@/components/CommentList.vue'

export default {
  name: 'PostDetailView',
  components: {
    CommentCreate,
    CommentList,
  },
  data() {
    return {
      postId: this.$route.params.post_id,
    }
  },
  computed: {
    ...mapState([
      'selectedPost',
      'currentUser',
    ]),
    ...mapGetters([
      'isLoggedIn'
    ]),
  },
  methods: {
    ...mapActions([
      'postDetail',
      'deletePost',
    ]),
    onMypage(userid) {
      this.$router.push(`/accounts/${userid}`)
    }
  },
  created() {
    this.postDetail(this.postId)
    // if (this.isLoggedIn === false)
  }
}
</script>

<style scoped>
.disabled-button {
  pointer-events: none;
}

</style>