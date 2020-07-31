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
    SET_SELECTPOST(state, selectedPost) {
      state.selectedPost = selectedPost
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
    createPost({ state, getters }, postList) {

      postList.uid = state.currentUser
      postList.image = state.uploadImageURL

      console.log(postList)

      axios.post(SERVER.URL + SERVER.ROUTES.postList, postList, getters.config)
        .then((res) => {
          console.log('success', res)
          router.push({ name: 'Home' })
        })
        .catch(err => console.log('error', err))
    },
    fetchPosts({ commit }, cafeno) {
      axios.get(SERVER.URL + SERVER.ROUTES.postList + cafeno)
        .then(res => commit('SET_POSTS', res.data))
        .catch(err => console.error(err))
    },
    postDetail({ commit }, id) {
      axios.get(SERVER.URL + SERVER.ROUTES.postDetail + id)
        .then(res => {
          commit('SET_SELECTPOST', res.data)
        })
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
    uploadImage({ dispatch, commit }, postData) {
      commit('SET_IMAGEURL', "2020630111230621.png")
      dispatch('createPost', postData.postList)
      // axios.post(SERVER.URL + SERVER.ROUTES.UploadImage, postData.formData)
      //   .then(res => {
      //     console.log(res)
      //     commit('SET_IMAGEURL', res.data)
      //     dispatch('createPost', postData.postList)
      //   })
      //   .catch(err => console.log(err))
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