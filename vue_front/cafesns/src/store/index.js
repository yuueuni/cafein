import Vue from 'vue'
import Vuex from 'vuex'

import cookies from 'vue-cookies'
import router from '@/router/index'
import axios from 'axios'

import SERVER from '@/API/drf'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    authToken: cookies.get('auth-token'),
  },
  
  getters: {
    isLoggedIn(state) {
      return !!state.authToken
    },
    config: state => ({
      headers: {
        Authorization: `Token ${state.authToken}`
      }
    })
  },

  // state변경
  mutations: {
    SET_TOKEN(state, token) {
      state.authToken = token
      cookies.set('auth-token', token)
    }   
  },

  actions: {
    authData({ commit }, info) {
      axios.post(SERVER.URL + info.location, info.data)
        .then(res => {
          commit('SET_TOKEN', res.data.key)
          router.push({ name: 'Home' })
        })
        .catch(err => console.log(err.respsone.data))
    },

    signup({ dispatch }, signupData) {
      const info = {
        location: SERVER.ROUTES.signup,
        data: signupData
      }
      dispatch('authData', info)
    },

    login({ dispatch }, loginData) {
      const info = {
        location: SERVER.ROUTES.login,
        data: loginData
      }
      dispatch('authData', info)
    },

    logout({ getters, commit }) {
      axios.post(SERVER.URL + SERVER.ROUTES.logout, null, getters.config)
        .then(() => {
          cookies.remove('auth-token')
          commit('SET_TOKEN', null)
          router.push({ name: 'Home' })
        })
        .catch(err => console.log(err.respsone.data))
    }
  },
  modules: {
  }
})
