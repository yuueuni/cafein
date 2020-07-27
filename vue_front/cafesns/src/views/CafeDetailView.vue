<template>
  <div>
    <v-card
      :loading="loading"
      class="mx-auto my-12"
      max-width="374"
    >
      <!-- cafe image -->
      <v-img
        height="250"
        src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
      >
        <v-row align="end" class="fill-height text-end">
          <v-col>
            <v-btn icon color="red lighten-3">
              <v-icon>mdi-heart</v-icon>
            </v-btn>
            <v-btn icon color="blue lighten-3">
              <v-icon>mdi-bookmark</v-icon>
            </v-btn>
          </v-col>
        </v-row>
      </v-img>

      <v-card-title>Cafe Name</v-card-title>

      <v-card-text>
        <div class="my-4 subtitle-1">
          cafe keywords
        </div>
      </v-card-text>

      <v-divider class="mx-4"></v-divider>

      <v-card-title>cafe open - close</v-card-title>
      <v-card-text>time 00:00 - 00:00</v-card-text>

      <v-card-title>cafe menu</v-card-title>
      <v-card-text>lists..</v-card-text>

      <v-card-actions>
        <v-spacer></v-spacer>
        <router-link to="/post/create" class="link-text">
          <v-btn
            color="deep-purple lighten-2"
            text
            @click="reserve"
          >
            new post
          </v-btn>
        </router-link>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
export default {
  name: 'CafeDetail',
}
</script>

<style>

</style>