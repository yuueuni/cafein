<template>
  <div>
    <v-row justify="center">
      <v-col cols="12" sm="10" md="8" lg="6">
        <v-card ref="form" class="px-3">
          <v-card-text>
            <h1 class="text-center mb-3">Login</h1>
            <v-text-field 
              label="Username"
              v-model="loginData.username" 
              id="username"
              autofocus
            >
            </v-text-field>
            <v-text-field 
              label="Password" 
              type="password" 
              v-model="loginData.password" 
              id="password" 
            >
            </v-text-field>
          </v-card-text>
          <v-divider></v-divider>
          <v-card-actions>
            <span>아직 회원이 아니신가요? <router-link to="/accounts/signup">회원가입</router-link></span>
            <v-spacer></v-spacer>
            <v-btn color="primary" @click="login(loginData)" text>Login</v-btn>
          </v-card-actions>
        </v-card>  
      </v-col>
    </v-row>
  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
    name: 'LoginView',
    data() {
      return {
        loginData: {
          username:null,
          password:null,
        }
      }
    },
    methods: {
      ...mapActions(['login'])
    }

}
</script>

<style>

</style>