<template>
  <div>
    <router-link to="/post/detail"><v-btn text>go post</v-btn></router-link>
    <v-row justify="center">
      <v-col cols="12" sm="10" md="8" lg="6">
        <v-card ref="form" class="d-inline-block px-3">
          <v-card-text class="text-center">
            <v-card-title>
              <h1>New Post</h1>
              <v-spacer></v-spacer>
              <span class="text-subtitle-2">cafe name</span>
            </v-card-title>
            <v-divider></v-divider>

            <!-- taste -->
            <v-btn-toggle
              tile
              color="deep-purple accent-3"
              group
              mandatory
              class="post-checklist"
            >
              <v-btn depressed @click="postList.taste = true">
                <v-icon left>mdi-thumb-up</v-icon>
                <span>like</span>
              </v-btn>
              <v-btn depressed class="disabled-button">
                <span>맛</span>
              </v-btn>
              <v-btn depressed @click="postList.taste = false">
                <v-icon left>mdi-thumb-down</v-icon>
                <span>unlike</span>
              </v-btn>
            </v-btn-toggle>

            <!-- mood -->
            <v-btn-toggle
              tile
              color="deep-purple accent-3"
              group
              mandatory
              class="post-checklist"
            >
              <v-btn depressed @click="postList.mood = true">
                <v-icon left>mdi-thumb-up</v-icon>
                <span>like</span>
              </v-btn>
              <v-btn depressed class="disabled-button">
                <span>분위기</span>
              </v-btn>
              <v-btn depressed @click="postList.mood = false">
                <v-icon left>mdi-thumb-down</v-icon>
                <span>unlike</span>
              </v-btn>
            </v-btn-toggle>

            <!-- clean -->
            <v-btn-toggle
              tile
              color="deep-purple accent-3"
              group
              mandatory
              class="post-checklist"
            >
              <v-btn depressed @click="postList.clean = true">
                <v-icon left>mdi-thumb-up</v-icon>
                <span>like</span>
              </v-btn>
              <v-btn depressed class="disabled-button">
                <span>위생</span>
              </v-btn>
              <v-btn depressed @click="postList.clean = false">
                <v-icon left>mdi-thumb-down</v-icon>
                <span>unlike</span>
              </v-btn>
            </v-btn-toggle>

            <v-textarea 
              label="Content" 
              v-model="postList.contents"
              id="content"
            >
            </v-textarea>

            <v-file-input accept="image/*" label="File input" @change="onFileChange"></v-file-input>
            <v-img v-if="url" :src="url" contain max-width="100%" max-height="300px"></v-img>
          </v-card-text>
          <div class="text-center mb-3">
            <v-btn color="secondary" @click="createPost(postList)">Save</v-btn>
          </div>
        </v-card>  
      </v-col>
    </v-row>
  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  name: 'postCreateView',
  data() {
    return {
      postList: {
        cafeno: null,
        contents: null,
        image: null,
        taste: true,
        mood: true,
        clean: true,
      },
      url: null,
    }
  },
  methods: {
    ...mapActions(['createPost']),
    onFileChange(e) {
      const file = e
      this.url = URL.createObjectURL(file)
    }
  }

}
</script>

<style scoped>
.post-checklist {
  justify-content: center;
  width: 100%;
  margin-top: 1rem;
}

.disabled-button {
  pointer-events: none;
}

</style>