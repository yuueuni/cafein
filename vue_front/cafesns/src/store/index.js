import Vue from 'vue'
import Vuex from 'vuex'

import cookies from 'vue-cookies'
import router from '@/router/index'
import axios from 'axios'

import SERVER from '@/API/url'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    authToken: cookies.get('auth-token'),
    userData: {},

    postList: {},

    likeList: {},
    stampList: {},

    followingList: {},
    followerList: {},
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

    SET_POSTDATA(state, postList) {
      state.postList = postList
    },

    SET_LIKELIST(state, likeList) {
      state.likeList = likeList
    },
    SET_STAMPLIST(state, stampList) {
      state.stampList = stampList
    },

    SET_FOLLWINGLIST(state, followingList) {
      state.followingList = followingList
    },
    SET_FOLLOWERLIST(state, followerList) {
      state.followerList = followerList
    },

  },

  actions: {
    // accounts
    authData({ commit }, info) {
      axios.post(SERVER.URL + info.location, info.data)
        .then(res => {
          commit('SET_TOKEN', res.data)
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

    logout({ commit }) {
      // axios.post(SERVER.URL + SERVER.ROUTES.logout, null, getters.config)
      //   .then(() => {
      commit('SET_TOKEN', null)
      cookies.remove('auth-token')
      router.push({ name: 'Home' })
        // })
        // .catch(err => console.log(err.respsone.data))
    },

    // user
    fetchUserData({ getters, commit }, ) {
      axios.get(SERVER.URL + SERVER.ROUTES.mypage, getters.config)
        .then(res => {
          commit('SET_USERDATA', res.data)
        })
        .catch(err => console.log(err.response.data))
    },

    // post
    createPost({ getters, commit }) {
      axios.post(SERVER.URL + SERVER.ROUTES.createPost, getters.config)
        .then(res => {
          commit('SET_POSTDATA', res.data)
        })
        .catch(err => console.log(err.response.data))
    },

    // like, stamp
    fetchLikeList({ state, getters, commit }) {
      axios.get(SERVER.URL + SERVER.ROUTES.like + `${state.userData.id}/`, getters.config)
        .then(res => {
          commit('SET_LIKELIST', res)
        })
        .catch(err => console.log(err.response.data))
    },
    fetchStampList({ state, getters, commit }) {
      axios.get(SERVER.URL + SERVER.ROUTES.stamp + `${state.userData.id}/`, getters.config)
      .then(res => {
        commit('SET_STAMPLIST', res)
      })
      .catch(err => console.log(err.response.data))
    },

    // follow
    fetchFollowingList({ getters, commit }) {
      axios.get(SERVER.URL + SERVER.ROUTES.following, getters.config)
        .then(res => {
          commit('SET_FOLLWINGLIST', res)
        })
        .catch(err => console.log(err.response.data))
    },
    fetchFollowerList({ getters, commit }) {
      axios.get(SERVER.URL + SERVER.ROUTES.follower, getters.config)
      .then(res => {
        commit('SET_FOLLOWERLIST', res)
      })
      .catch(err => console.log(err.response.data))
    },
  },
  modules: {
  }
})
