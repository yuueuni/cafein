<template>
  <div>
    <v-row justify="center">
      <v-col cols="12" sm="10" md="8" lg="6">
        <v-card ref="form" class="px-3">
          <v-card-text>
            <h1 class="text-center mb-3">Signup</h1>
            <v-text-field 
              label="Username"
              v-model="signupData.username" 
              id="Username"
              autofocus
            >
            </v-text-field>
            <v-text-field 
              label="Password" 
              type="password" 
              v-model="signupData.password" 
              id="password" 
            >
            </v-text-field>
            <v-text-field 
              label="Confirm Password"
              type="password" 
              v-model="signupData.password2" 
              id="password2"
              hint="* 비밀번호를 다시 입력해주세요."
              persistent-hint
            >
            </v-text-field>
          </v-card-text>
          <v-divider></v-divider>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="primary" @click="signup(signupData)" text>Sumbit</v-btn>
          </v-card-actions>
        </v-card>  
      </v-col>
    </v-row>
  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  name: "SignupView",

  data() {
    return {
      signupData: {
        username: null,
        password: null,
        password2: null,
      }
    }
  },

  methods: {
  ...mapActions(['signup'])
  // password != password2
  },
}

</script>

<style>

</style>