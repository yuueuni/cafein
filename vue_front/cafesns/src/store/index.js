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
        Authorization: `${state.authToken}`
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

    SET_FOLLOWINGLIST(state, followingList) {
      state.followingList = followingList
    },
    SET_FOLLOWERLIST(state, followerList) {
      state.followerList = followerList
    },

    SET_ADDCAFELIST(state, cafeList) {
      state.cafeList = state.cafeList.concat(cafeList)
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
          router.go(-1)
        })
        .catch(error => {
          const msg = error.response.data
          if ( msg === 'NO_ID') {
            alert('이메일을 다시 확인해주세요.')
          } else if (msg === 'WRONG_PW') {
            alert('비밀번호를 확인해주세요.')
          } else if (msg === 'EXISTING_ID') {
            alert('이미 존재하는 이메일입니다.')
          } else {
            alert("잘못된 접근입니다.")
          }
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
      router.go(-1)
    },

    // user
    fetchUserData({ commit, getters }, userid) {
      axios.get(SERVER.URL + SERVER.ROUTES.mypage + `/${userid}`, getters.config)
        .then(res => commit('SET_USERDATA', res.data))
        .catch(err => console.log(err))
    },

    // post
    createPost({ state, getters }, postList) {
      postList.cafeno = state.selectedCafe.cafeno
      postList.uid = state.currentUser
      postList.image = state.uploadImageURL
      axios.post(SERVER.URL + SERVER.ROUTES.postDetail, postList, getters.config)
        .then((res) => {
          console.log(res)
          router.push(`/post/detail/${res.data.pno}`)
        })
        .catch(err => console.log('error', err))
    },
    fetchPosts({ commit }, cafeno) {
      axios.get(SERVER.URL + SERVER.ROUTES.postList + cafeno)
        .then(res => commit('SET_POSTS', res.data))
        .catch(err => console.error(err))
    },
    postDetail({ commit }, id) {
      axios.get(SERVER.URL + SERVER.ROUTES.postDetail + `/${id}`)
        .then(res => {
          commit('SET_SELECTPOST', res.data)
        })
        .catch(err => console.error(err))
      },
    updatePost({ state, commit, dispatch, getters }, postData) {
      postData.image = state.uploadImageURL
      console.log(postData.image, state.uploadImageURL)
      axios.put(SERVER.URL + SERVER.ROUTES.postDetail, postData, getters.config)
        .then(() => {
          dispatch('fetchPosts', postData.cafeno)
          commit('SET_SELECTPOST', postData)
          router.push(`/post/detail/${postData.pno}`)
        })
        .catch(err => console.error(err))
    },
    deletePost({ state, getters, dispatch }, postId) {
      axios.delete(SERVER.URL + SERVER.ROUTES.postDelete + `${postId}`, getters.config)
        .then(() => {
          dispatch('fetchPosts', state.selectedPost.pno)
          router.push(`/cafe/detail/${state.selectedCafe.cafeno}`)
        })
        .catch(err => console.log(err))
    },
    uploadImage({ dispatch, commit }, postData) {
      if (!postData.formData.image) {
        dispatch('updatePost', postData.selectedPost)
      } else {
        axios.post(SERVER.URL + SERVER.ROUTES.uploadImage, postData.formData)
          .then((res) => {
            if (postData.selectedPost) {
              commit('SET_IMAGEURL', res.data)
              dispatch('updatePost', postData.selectedPost)
            } else {
              commit('SET_IMAGEURL', res.data)
              dispatch('createPost', postData.postList)
            }
          })
          .catch(err => console.log(err))
      }
    },

    // comment
    createComment({ state, getters,dispatch }, commentData) {
      commentData.uid = state.currentUser
      axios.post(SERVER.URL + SERVER.ROUTES.createComment, commentData, getters.config)
        .then(() => {
          dispatch('fetchComments', state.selectedPost.pno)
        })
        .catch(err => console.log(err))
    },
    fetchComments({ commit }, postno) {
      axios.get(SERVER.URL + SERVER.ROUTES.commentList+postno)
        .then(res => commit('SET_COMMENTS', res.data))
        .catch(err => console.error(err))
    },
    deleteComment({ state, getters, dispatch }, commentId) {
      axios.delete(SERVER.URL + SERVER.ROUTES.deleteComment + `${commentId}`, getters.config)
        .then(() => {
          dispatch('fetchComments', state.selectedPost.pno)
        })
        .catch(err => console.log(err))
    },

    // like
    fetchLikeList({ state, getters, commit }) {
      const userid = state.userData.id
      axios.get(SERVER.URL + SERVER.ROUTES.like + `/list/${userid}`, getters.config)
        .then(res => {
          commit('SET_LIKELIST', res.data)
        })
        .catch(err => console.log(err))
    },
    likeCafe({ state, getters }, cafeno) {
      if (!getters.isLoggedIn) {
        alert('로그인이 필요합니다!')
        router.push(`/accounts/login`)
      }
      const userid = state.userData.id
      axios.get(SERVER.URL + SERVER.ROUTES.like + `/check/${cafeno}/${userid}`, getters.config)
        .then(res => {
          if (res.data === 0) {
            const likeData = {
              cafeno: cafeno,
              uid: userid
            }
            axios.post(SERVER.URL + SERVER.ROUTES.like, likeData, getters.config)
              .then(() => console.log("success"))
              .catch(err => console.log(err))
          } 
          else {
            axios.delete(SERVER.URL + SERVER.ROUTES.like + `/delete/${cafeno}/${userid}`, getters.config)
              .then(() => console.log("success"))
              .catch(err => console.log(err))
          }
        })
        .catch(err => console.log(err))
    },

    //stamp
    fetchStampList({ state, commit }) {
      const userid = state.userData.id
      axios.get(SERVER.URL + SERVER.ROUTES.stamp + `/list/${userid}`)
      .then(res => {
        commit('SET_STAMPLIST', res.data)
      })
      .catch(err => console.log(err))
    },
    stampCafe({ state, getters }, cafeno) {
      if (!getters.isLoggedIn) {
        alert('로그인이 필요합니다!')
        router.push(`/accounts/login`)
      }
      const userid = state.userData.id
      axios.get(SERVER.URL + SERVER.ROUTES.stamp + `/check/${cafeno}/${userid}`, getters.config)
        .then(res => {
          if (res.data === 0) {
            const stampData = {
              cafeno: cafeno,
              uid: userid
            }
            axios.post(SERVER.URL + SERVER.ROUTES.stamp, stampData, getters.config)
              .then(() => console.log("success"))
              .catch(err => console.log(err))
          } 
          else {
            axios.delete(SERVER.URL + SERVER.ROUTES.stamp + `/delete/${cafeno}/${userid}`, getters.config)
              .then(() => console.log("success"))
              .catch(err => console.log(err))
          }
        })
        .catch(err => console.log(err))
    },

    // follow
    fetchFollowingList({ state, commit }) {
      const userid = state.userData.id
      axios.get(SERVER.URL + SERVER.ROUTES.follow + `/list/following/${userid}`)
        .then(res => commit('SET_FOLLOWINGLIST', res.data))
        .catch(err => console.log(err))
    },
    fetchFollowerList({ state, commit }) {
      const userid = state.userData.id
      axios.get(SERVER.URL + SERVER.ROUTES.follow + `/list/follower/${userid}`)
      .then(res => commit('SET_FOLLOWERLIST', res.data))
      .catch(err => console.log(err))
    },
    followUser({ state, getters }, followingid) {
      const userid = state.userData.id
      axios.get(SERVER.URL + SERVER.ROUTES.follow + `/check/${userid}/${followingid}`, getters.config)
        .then(res => {
          if (res.data === 0) {
            axios.post(SERVER.URL + SERVER.ROUTES.follow, {userid,followingid}, getters.config)
              .then(() => console.log("success"))
              .catch(err => console.log(err))
          } 
          else {
            axios.delete(SERVER.URL + SERVER.ROUTES.follow + `/delete/${userid}/${followingid}`, getters.config)
              .then(() => console.log("success"))
              .catch(err => console.log(err))
          }
        })
        .catch(err => console.log(err))
    },
      
   
    //cafe
    fetchCafeList({ state, commit }, page) {
      const cafeLen = Object.keys(state.cafeList).length
      axios.get(SERVER.URL + SERVER.ROUTES.cafeList + page)
        .then(res => {
          if (!cafeLen) {
            commit('SET_CAFELIST', res.data)
          } else if (page === 1) {
            return
          } else {
            commit('SET_ADDCAFELIST', res.data)
          }
        })
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