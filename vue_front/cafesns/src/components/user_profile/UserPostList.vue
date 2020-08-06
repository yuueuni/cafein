<template>
  <div>
    <v-dialog v-model="dialog" scrollable max-width="300px">
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          color="#BCAAA4"
          v-bind="attrs"
          v-on="on"
          text
        >
          POSTS {{ userPostList.length }}
        </v-btn>
      </template>
      <v-card>
        <v-row align="center" justify="center">
          <v-card-title>Posts</v-card-title>
        </v-row>
        <v-divider></v-divider>
        <v-card-text style="height: 300px;">
          <v-radio-group v-model="dialogm1" column>
            <v-row v-for="post in userPostList" :key="post.pno">
              <h1>{{ post }}</h1>
              <v-list rounded>
                <v-list-item-group color="primary">
                  <v-list-item
                    v-for="(item, i) in items"
                    :key="i"
                    @click="onSelectPost(post)"
                  >
                  <v-list-item-icon>
                    <v-icon v-text="item.icon"></v-icon>
                  </v-list-item-icon>
                  <v-list-item-content>
                    <v-list-item-title >{{ post.cafename }}</v-list-item-title>
                  </v-list-item-content>
                  </v-list-item>
                </v-list-item-group>
              </v-list>
            </v-row>
          </v-radio-group>
        </v-card-text>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'

export default {
  name: "UserPostList",
  data () {
    return {
      dialogm1: '',
      dialog: false,
      currentUserId : null,
      items: [
        { text: 'post.pno', icon: 'mdi-account' }
      ],
    }
  },

  computed: {
    ...mapState([ 
      'userPostList', 
    ])
  },
  
  methods: {
    ...mapActions([
      'fetchUserPostList',
    ]),
    onSelectPost(postno) {
      this.dialog = false  
      this.$router.push(`/post/detail/${postno}`)
    },
  },
  
  created() {
    this.currentUserId = this.$route.params.user_id,
    this.fetchUserPostList(this.currentUserId)
  },
}
</script>