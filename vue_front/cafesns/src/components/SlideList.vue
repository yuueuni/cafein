<template>
  <v-slide-group
    v-model="model"
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
          <!-- version.1 image in text -->
          <v-row align="end" class="lightbox white--text fill-height">
            <v-col style="background:#00000080">
              <div class="subheading">Cafe Name</div>
            </v-col>
          </v-row>
        </v-img>
        <!-- version.2 image out text -->
        <!-- <v-scale-transition>
          <h3 color="white">cafe name</h3>
        </v-scale-transition> -->
      </v-card>
    </v-slide-item>
  </v-slide-group>
</template>

<script>
  export default {
    name: 'SlideList',
    data () {
      return {
        cards: ['Good', 'Best', 'Finest', 'Good', 'Best', 'Finest', 'Good', 'Best', 'Finest', 'Good', 'Best', 'Finest'],
        socials: [
          {
            icon: 'mdi-heart',
            color: 'grey',
          },
          {
            icon: 'mdi-thumb-up',
            color: 'red lighten-3',
          },
          {
            icon: 'mdi-shoe-print',
            color: 'grey',
          },
        ],
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
