<template>
  <div>
    <v-row justify="center">
      <v-col cols="12" sm="10" md="8" lg="6">
        <v-card ref="form" class="px-3 d-inline-block">
          <v-card-text class="text-center">
            <v-card-title>
              <h1>New Post</h1>
              <v-spacer></v-spacer>
              <span class="text-subtitle-2">cafe name</span>
            </v-card-title>
            <v-divider></v-divider>
            <v-row>
              <v-col
                cols="12"
              >
                <!-- taste -->
                <v-btn-toggle
                  tile
                  color="deep-purple accent-3"
                  group
                  mandatory
                >
                  <v-btn depressed @click="postList.taste = true">
                    <v-icon left>mdi-thumb-up</v-icon>
                    <span>like</span>
                  </v-btn>
                  <v-btn>
                    맛
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
                >
                  <v-btn depressed @click="postList.mood = true">
                    <v-icon left>mdi-thumb-up</v-icon>
                    <span>like</span>
                  </v-btn>
                  <v-btn>
                    분위기
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
                >
                  <v-btn depressed @click="postList.clean = true">
                    <v-icon left>mdi-thumb-up</v-icon>
                    <span>like</span>
                  </v-btn>
                  <v-btn>
                    청결도
                  </v-btn>
                  <v-btn depressed @click="postList.clean = false">
                    <v-icon left>mdi-thumb-down</v-icon>
                    <span>unlike</span>
                  </v-btn>
                </v-btn-toggle>
              </v-col>
            </v-row>

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

<style>

</style>