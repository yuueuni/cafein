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
              <h1>New Post</h1>
              <v-spacer></v-spacer>
              <span class="text-subtitle-2">cafe name</span>
            </v-card-title>
            <v-divider class="mb-3"></v-divider>

            <span>맛 {{ postList.taste }}</span>
            <v-rating
              v-model="postList.taste"
              color="yellow darken-3"
              background-color="grey darken-1"
              empty-icon="$ratingFull"
              half-increments
              hover
            ></v-rating>

            <span>분위기 {{ postList.mood }}</span>
            <v-rating
              v-model="postList.mood"
              color="yellow darken-3"
              background-color="grey darken-1"
              empty-icon="$ratingFull"
              half-increments
              hover
            ></v-rating>

            <span>위생 {{ postList.clean }}</span>
            <v-rating
              v-model="postList.clean"
              color="yellow darken-3"
              background-color="grey darken-1"
              empty-icon="$ratingFull"
              half-increments
              hover
            ></v-rating>

            <v-textarea 
              label="Content" 
              v-model="postList.contents"
              id="content"
            >
            </v-textarea>

            <v-file-input accept="image/*" label="File input" @change="onFileChange"></v-file-input>
            <v-img v-if="url" :src="url" contain max-width="100%" max-height="300px"></v-img>
          </v-card-text>
          <div class="text-center mb-3 pb-3">
            <v-btn color="secondary" @click="uploadImage({postList, formData})">Save</v-btn>
          </div>
        </v-card>  
      </v-col>
    </v-row>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'

export default {
  name: 'postCreateView',
  data() {
    return {
      postList: {
        cafeno: null,
        contents: null,
        taste: 3,
        mood: 3,
        clean: 3,
        uid: null,
        image: null,
      },
      formData: new FormData(),
      url: null,
      selectedFile: null,
    }
  },
  computed: {
    ...mapState(['currentUser'])
  },
  methods: {
    ...mapActions(['uploadImage']),
    onFileChange(e) {
      this.selectedFile = e

      this.url = URL.createObjectURL(this.selectedFile)

      this.formData.append("image", this.selectedFile, this.selectedFile.name)
    },
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