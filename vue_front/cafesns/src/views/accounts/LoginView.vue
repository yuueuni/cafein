<template>
  <v-dialog
    v-model="dialog"
    transition="dialog-bottom-transition"
    persistent
    max-width="600px"
    overlay-color="#2c001e"
    overlay-opacity="1"
  >
    <v-card ref="form" color="#2c001e" dark class="pb-3">
      <v-list-item color="#2c001e" dark>
        <v-list-item-content>
          <v-list-item-title class="headline">오늘은 어디 카페 가지?</v-list-item-title>
        </v-list-item-content>
        <v-list-item-avatar>
          <v-btn icon dark @click="$router.go(-1)">
            <v-icon>mdi-close</v-icon>
          </v-btn>
        </v-list-item-avatar>
      </v-list-item>
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
        <v-btn color="#BCAAA4" @click="login(loginData)">Login</v-btn>
      </div>
      </v-card-text>
      <v-card-actions>
        <span style="font-size:small;">아직 회원이 아니신가요? <v-btn class="my-0" color="warning" @click="onSingup" text small>회원가입</v-btn></span>
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