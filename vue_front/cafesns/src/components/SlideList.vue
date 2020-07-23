<template>
  <v-slide-group
    class="pa-4"
    active-class="secondary"
    show-arrows
  >
    <v-slide-item
      v-for="card in cards"
      :key="card"
      v-slot:default="{ active, toggle }"
    >
      <v-card
        class="ma-4"
        @click="toggle"
      >
        <v-img
          :src="`https://picsum.photos/200/300?image=${getImage()}`"
          height="200px"
        >
          <v-row align="end" class="lightbox white--text fill-height">
            <v-col style="background:#00000080">
              <div class="subheading">{{ card }}</div>
            </v-col>
          </v-row>

        </v-img>
      </v-card>
    </v-slide-item>
  </v-slide-group>
</template>

<script>
  export default {
    name: 'SlideList',
    data () {
      return {
        cards: ['Good', 'Best', 'Finest', 'Good1', 'Best1', 'Finest1', 'Good2', 'Best2', 'Finest2', 'Good3', 'Best3', 'Finest3'],

      }
    },
    methods: {
      getImage () {
        const min = 550
        const max = 560
        return Math.floor(Math.random() * (max - min + 1)) + min
      },
    },
  }
</script>
