<template>
<div id="app" v-cloak>
  
  <div v-if="errorStr">
    Sorry, but the following error
    occurred: {{errorStr}}
  </div>
  
  <div v-if="gettingLocation">
    <i>Getting your location...</i>
  </div>
  
  <div v-if="location">
    Your location data is {{ location.coords.latitude }}, {{ location.coords.longitude}}
    <v-btn color="secondary" @click="geo(location)">find</v-btn>
  </div>
  
</div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
    name: 'GeolocationView',
    created() { console.log("created start") 
        //do we support geolocation
        if(!("geolocation" in navigator)) {
            this.errorStr = 'Geolocation is not available.';
            return;
        }

        this.gettingLocation = true;
        // get position
        navigator.geolocation.getCurrentPosition(pos => {
            this.gettingLocation = false;
            this.location = pos;
        }, err => {
            this.gettingLocation = false;
            this.errorStr = err.message;
        })
    }, 
    data() {
      return {
        location:null,
        gettingLocation: false,
        errorStr:null ,
      }
    },
    methods: {
      ...mapActions(['geo'])
    }

}
</script>