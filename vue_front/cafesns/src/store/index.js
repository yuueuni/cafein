import Vue from 'vue'
import Vuex from 'vuex'

import cookies from 'vue-cookies'
import router from '@/router/index'
import axios from 'axios'

import SERVER from '@/API/url'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    authToken: cookies.get('auth-token'),
    currentUser: null,
    userData: {},
    
    postList: {},
    posts: [],
    selectedPost: null,
    updatePostData: {},
    uploadImageURL: null,
    
    commentData: {},
    comments: [],
    selectedComment: null,
    
    likeList: {},
    stampList: {},
    
    followingList: {},
    followerList: {},
    
    cafeList: {},
    selectedCafe: null,
    
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

    SET_POSTS(state, posts) {
      state.posts = posts
    },

    SET_COMMENTS(state, comments) {
      state.comments = comments
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

    SET_CAFELIST(state, cafeList) {
      state.cafeList = cafeList
    },
    SET_SELECTCAFE(state, selectedCafe) {
      state.selectedCafe = selectedCafe
    },
    SET_IMAGEURL(state, imageURL) {
      state.uploadImageURL = imageURL
    },

  },

  actions: {
    // accounts
    authData({ state, commit }, info) {
      axios.post(SERVER.URL + info.location, info.data)
        .then(res => {
          commit('SET_TOKEN', res.data)
          state.currentUser = info.data.id
          this.dispatch("fetchUserData")
          router.push({ name: 'Home' })
        })
        .catch(err => {
          console.log(err)
          alert("이메일과 비밀번호를 확인하세요.")
        })
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

    logout({ state, commit }) {
      commit('SET_TOKEN', null)
      state.currentUser = null
      cookies.remove('auth-token')
      router.push({ name: 'Home' })
    },

    // user
    fetchUserData({ state, commit, getters }) {
      axios.get(SERVER.URL + SERVER.ROUTES.mypage + `/${state.currentUser}`, getters.config)
        .then(res => {
          console.log(res)
          commit('SET_USERDATA', res.data)
        })
        .catch(err => console.log(err))
    },

    // post
    createPost({ getters }, postList) {
      postList.taste = postList.taste.toString()
      postList.mood = postList.mood.toString()
      postList.clean = postList.clean.toString()
      axios.post(SERVER.URL + SERVER.ROUTES.postList, postList, getters.config)
        .then(() => {
          this.$router.push({ name: 'Home' })
        })
        .catch(err => console.log(err))
    },
    fetchPosts({ commit }) {
      axios.get(SERVER.URL + SERVER.ROUTES.postList)
        .then(res => commit('SET_POSTS', res.data))
        .catch(err => console.error(err))
    },
    // updatePosts({ state, dispatch, getters }, updatePostDate) {
    //   axios.put(SERVER.URL + SERVER.ROUTES.postList + `/${state.selectedPost.id}`,updatePostDate, getters.config)
        // .then(() => {
        //   dispatch('fetchPosts', state.selectedPost.id)
        // }
    //     .catch(err => console.error(err))
    // },
    deletePost({ getters }, postId) {
      axios.delete(SERVER.URL + SERVER.ROUTES.postList + `/${postId}`, getters.config)
    },
    uploadImage({ commit }, formData) {
      axios.post(SERVER.URL + SERVER.ROUTES.UploadImage, formData)
        .then(res => {
          commit('SET_IMAGEURL', res.data)
        })
        .catch(err => console.log(err))
    },

    // comment
    createComment({ getters }, commentData) {
      axios.post(SERVER.URL + SERVER.ROUTES.createComment, commentData, getters.config)
        .then(() => {
          console.log(commentData)
        })
        .catch(err => console.log(err))
    },
    fetchComments({ commit }) {
      axios.get(SERVER.URL + SERVER.ROUTES.commentList)
        .then(res => commit('SET_COMMENTS', res.data))
        .catch(err => console.error(err))
    },
    deleteComment({ state, getters, dispatch }, commentId) {
      axios.delete(SERVER.URL + SERVER.ROUTES.deletePost + `/${state.selectedPost.id}/comment/${commentId}`, getters.config)
        .then(() => {
          dispatch('fetchComments', state.selectedPost.id)
        })
        .catch(err => console.log(err))
    },

    // like, stamp
    // fetchLikeList({ state, getters, commit }, cafeno, i) {
    //   const userid = state.userData.id
    //   axios.get(SERVER.URL + SERVER.ROUTES.like + `/${cafeno}/${userid}/`, getters.config)
    //     .then(res => {
    //       commit('SET_LIKELIST', res)
    //     })
    //     .catch(err => console.log(err))
    // },

    // likeCafe({ state }, cafeno, i) {
    //   console.log("like")
    //   const userid = state.userData.id
    //   axios.get(SERVER.URL + SERVER.ROUTES.like + `/check/${cafeno}/${userid}`)
    //     .then(res => {
    //       console.log("COUNT: " + res.data);
    //       if (res.data === 0) {
    //         const likeData = {
    //           cafeno: cafeno,
    //           uid: userid
    //         }
    //         axios.post(SERVER.URL + SERVER.ROUTES.like, likeData)
    //           .then(() => {
    //             this.$set(this.cafeList[i], "like", this.cafeList[i].like + 1);
    //             console.log("success");
    //           })
    //           .catch(err => console.log(err))
    //       } 
    //       else {
    //         axios.delete(SERVER.URL + SERVER.ROUTES.like + `/delete/${cafeno}/${userid}`)
    //           .then(() => {
    //             this.$set(this.cafeList[i], "like", this.cafeList[i].like - 1);
    //             console.log("success");
    //           })
    //           .catch(err => console.log(err))
    //         console.log("else");
    //       }
    //     })
    //     .catch(err => console.log(err))
    // },



    fetchStampList({ state, getters, commit }) {
      axios.get(SERVER.URL + SERVER.ROUTES.stamp + `${state.userData.id}/`, getters.config)
      .then(res => {
        commit('SET_STAMPLIST', res)
      })
      .catch(err => console.log(err))
    },

    // follow
    fetchFollowingList({ getters, commit }) {
      axios.get(SERVER.URL + SERVER.ROUTES.following, getters.config)
        .then(res => {
          commit('SET_FOLLWINGLIST', res)
        })
        .catch(err => console.log(err))
    },
    fetchFollowerList({ getters, commit }) {
      axios.get(SERVER.URL + SERVER.ROUTES.follower, getters.config)
      .then(res => {
        commit('SET_FOLLOWERLIST', res)
      })
      .catch(err => console.log(err))
    },

    //cafe
    fetchCafeList({ commit }) {
      axios.get(SERVER.URL + SERVER.ROUTES.cafeList)
        .then(res => commit('SET_CAFELIST', res.data))
        .catch(err => console.error(err))
    },
    cafeDetail({ commit }, id) {
      axios.get(SERVER.URL + SERVER.ROUTES.cafeDetail + id)
        .then(res => {
          commit('SET_SELECTCAFE', res.data)
        })
        .catch(err => console.error(err))
    },
  },
  modules: {
  },
  plugins: [
    createPersistedState()
  ]
})