<template>
  <div>
    <v-dialog v-model="dialog" transition="dialog-bottom-transition" persistent max-width="600px">
      <v-card ref="form">
        <v-toolbar dark>
          <v-toolbar-title>Sign up</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-toolbar-items>
            <v-btn icon dark @click="$router.go(-1)">
              <v-icon>mdi-close</v-icon>
            </v-btn>
          </v-toolbar-items>
        </v-toolbar>
        <v-card-text class="px-3 pt-3">
          <v-text-field 
            label="Username"
            v-model="signupData.id" 
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
            @keypress.enter="signup(signupData)"
          >
          </v-text-field>
        </v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="secondary" :disabled="valid" @click="signup(signupData)">Sumbit</v-btn>
          <v-spacer></v-spacer>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  name: "SignupView",

  data() {
    return {
      dialog: true,
      signupData: {
        id: null,
        password: null,
        password2: null,
      },
      valid: true,
    }
  },
  methods: {
    ...mapActions(['signup'])
  },
}

</script>

<style>

</style>