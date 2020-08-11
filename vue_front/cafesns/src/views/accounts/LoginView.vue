<template>
  <v-dialog v-model="dialog" transition="dialog-bottom-transition" persistent max-width="600px">
    <v-card ref="form">
      <v-toolbar dark>
        <v-toolbar-title>Login</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-toolbar-items>
          <v-btn icon dark @click="$router.go(-1)">
            <v-icon>mdi-close</v-icon>
          </v-btn>
        </v-toolbar-items>
      </v-toolbar>
      <v-card-text class="px-3 pt-5">
        <v-text-field 
          label="Username"
          v-model="loginData.id"
          hint="* 이메일 형식으로 입력해주세요."
          persistent-hint
          id="username"
          autofocus
        >
        </v-text-field>
        <v-text-field 
          label="Password" 
          type="password"
          v-model="loginData.pw"
          id="password" 
          @keypress.enter="login(loginData)"
        >
        </v-text-field>
      <div class="text-center">
        <v-btn color="secondary" @click="login(loginData)">Login</v-btn>
      </div>
      </v-card-text>
      <v-divider></v-divider>
      <v-card-actions>
        <span style="font-size:small;">아직 회원이 아니신가요? <v-btn class="my-0" color="primary" @click="onSingup" text small>회원가입</v-btn></span>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import { mapActions } from 'vuex'

export default {
    name: 'LoginView',
    data() {
      return {
        dialog: true,
        loginData: {
          id:null,
          pw:null,
        },
      }
    },
    methods: {
      ...mapActions(['login']),
      onSingup() {
        this.$router.replace({ name: 'Signup'})
      }
    }

}
</script>