<template>
  <div>
    <v-row justify="center">
      <v-col cols="12" sm="10" md="8" lg="6">
        <v-card ref="form" class="px-3">
          <v-card-text>
            <h1 class="text-center mb-3">New Post</h1>
            <v-text-field 
              label="Title"
              v-model="postData.title" 
              id="title"
              autofocus
            >
            </v-text-field>
            <v-textarea 
              label="Content" 
              v-model="postData.content" 
              id="content" 
            >
            </v-textarea>
          </v-card-text>
          <v-divider></v-divider>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="primary" @click="createPost(postData)" text>Save</v-btn>
          </v-card-actions>
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
      postData: {
        title: null,
        content: null,
      }
    }
  },
  methods: {
    ...mapActions(['createPost'])
  }

}
</script>

<style>

</style>