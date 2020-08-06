<template>
  <div>
    <v-dialog v-model="dialog" transition="dialog-bottom-transition" persistent max-width="600px">
      <v-card ref="form">
        <v-toolbar dark>
          <v-toolbar-title>Sign up</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-toolbar-items>
            <v-btn icon dark @click="$router.push('/')">
              <v-icon>mdi-close</v-icon>
            </v-btn>
          </v-toolbar-items>
        </v-toolbar>
        <v-card-text class="px-3 pt-3">
          <v-text-field 
            label="E-mail"
            v-model="signupData.id"
            :rules="[rules.emailMatch]"
            id="Username"
            autofocus
            required
          >
          </v-text-field>
          <v-text-field 
            label="Password" 
            type="password" 
            v-model="signupData.password"
            :rules="[rules.passwordMatch]"
            hint="* 최소 8자리(영문,숫자,특수문자 모두 포함)"
            persistent-hint
            id="password"
            required 
          >
          </v-text-field>
          <v-text-field 
            label="Password Confirm"
            type="password" 
            v-model="signupData.password2" 
            :rules="[rules.passwordConfirm]"
            id="password2"
            required
            @keypress.enter="signup(signupData)"
          >
          </v-text-field>
        </v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn :disabled="!checkValidForm()" color="secondary" @click="signup(signupData)">Sumbit</v-btn>
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
      emailValid : false,
      pwValid: false,
      conPwValid : false,
      rules: {
        emailMatch:  v => {
          const pattern = /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})/
          if (pattern.test(v)) {
            this.emailValid = true
          } else {
            this.emailValid = false
            return '유효한 이메일을 입력해주세요'
          }
        },
        passwordMatch:  v => {
          const pattern = /^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})/
          if (pattern.test(v)) {
            this.pwValid = true
          } else {
            this.pwValid = false
            return '* 최소 8자리(영문,숫자,특수문자 모두 포함)'
          }
        },
        passwordConfirm: () => {
          if (this.signupData.password2 && this.signupData.password === this.signupData.password2) {
            this.conPwValid = true
          } else {
            this.conPwValid = false
            return '비밀번호를 확인해주세요.'
          }
        },
      },
    }
  },
  methods: {
    ...mapActions(['signup']),
    checkValidForm () {
      if (this.emailValid && this.pwValid && this.conPwValid) {
        return true
      } else {
        return false
      }
    },
  },
}

</script>

<style>

</style>