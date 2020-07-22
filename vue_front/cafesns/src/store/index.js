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
    userData: {},
    postData: {},
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
    },
    SET_USERDATA(state, userData) {
      state.userData = userData
    },
    SET_POSTDATA(state, postData) {
      state.postData = postData
    }
  },

  actions: {
    // accounts
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
          commit('SET_TOKEN', null)
          cookies.remove('auth-token')
          router.push({ name: 'Home' })
        })
        .catch(err => console.log(err.respsone.data))
    },

    // user
    fetchUserData({ commit }) {
      axios.get(SERVER.URL + SERVER.ROUTES.mypage, )
        .then(res => {
          commit('SET_USERDATA', res.data)
        })
        .catch(err => console.log(err.response.data))
    },

    // post
    createPost({ commit }) {
      axios.get(SERVER.URL + SERVER.ROUTES.createPost)
        .then(res => {
          commit('SET_POSTDATA', res.data)
        })
        .catch(err => console.log(err.response.data))
    }
  },
  modules: {
  }
})
