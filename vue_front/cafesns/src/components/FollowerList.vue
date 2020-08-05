<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" scrollable max-width="300px">
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          color="primary"
          dark
          v-bind="attrs"
          v-on="on"
        >
          Follower
        </v-btn>
      </template>
      <v-card>
        <v-card-title>Follower</v-card-title>
        <v-divider></v-divider>
        <v-card-text style="height: 300px;">
          <v-radio-group v-model="dialogm1" column>
            <v-radio label="Bahamas, The" value="bahamas"></v-radio>
            <v-radio label="Bahrain" value="bahrain"></v-radio>
            <v-radio label="Bangladesh" value="bangladesh"></v-radio>
            <v-radio label="Barbados" value="barbados"></v-radio>
            <v-radio label="Belarus" value="belarus"></v-radio>
            <v-radio label="Belgium" value="belgium"></v-radio>
            <v-radio label="Belize" value="belize"></v-radio>
            <v-radio label="Benin" value="benin"></v-radio>
            <v-radio label="Bhutan" value="bhutan"></v-radio>
            <v-radio label="Bolivia" value="bolivia"></v-radio>
            <v-radio label="Bosnia and Herzegovina" value="bosnia"></v-radio>
            <v-radio label="Botswana" value="botswana"></v-radio>
            <v-radio label="Brazil" value="brazil"></v-radio>
            <v-radio label="Brunei" value="brunei"></v-radio>
            <v-radio label="Bulgaria" value="bulgaria"></v-radio>
            <v-radio label="Burkina Faso" value="burkina"></v-radio>
            <v-radio label="Burma" value="burma"></v-radio>
            <v-radio label="Burundi" value="burundi"></v-radio>
          </v-radio-group>
        </v-card-text>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import { mapState, mapActions } from 'vuex'

export default {
  name: "FollowerList",
  data () {
    return {
      dialogm1: '',
      dialog: false,
      currentUserId : this.$route.params.user_id,
    }
  },
  
  computed: {
    ...mapState([
      'userData', 
      'postList',
      'likeList', 
      'stampList', 
      'followingList', 
      'followerList',
    ])
  },
  
  methods: {
    ...mapActions([
      'fetchUserData',
      'fetchPostList', 
      'fetchLikeList', 
      'fetchStampList', 
      'fetchFollowingList',
      'fetchFollowerList',
      'followUser',
    ])
  },
  
  created() {
    this.fetchUserData(this.currentUserId)
      this.fetchLikeList()
      this.fetchStampList()
      this.fetchFollowingList()
      this.fetchFollowerList()
      this.fetchPostList()
  },
}
</script>