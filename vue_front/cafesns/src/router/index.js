import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/views/Home.vue'

// accounts
import SignupView from '@/views/accounts/SignupView.vue'
import LoginView from '@/views/accounts/LoginView.vue'
import MypageView from '@/views/accounts/MypageView.vue'

import tempView from '@/views/accounts/temp.vue'

// post
import PostCreateView from '@/views/post/PostCreateView.vue'
import PostDetailView from '@/views/post/PostDetailView.vue'

// cafe
import CafeListView from '@/views/cafe/CafeListView.vue'
import CafeDetailView from '@/views/cafe/CafeDetailView.vue'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/accounts/signup',
    name: 'Signup',
    component: SignupView
  },
  {
    path: '/accounts/login',
    name: 'Login',
    component: LoginView
  },
  // {
  //   path: '/accounts/logout',
  //   name: 'Logout',
  //   component: LogoutView
  // },
  {
    path: '/accounts/:user_id',
    name: 'Mypage',
    component: MypageView
  },
  {
    path: '/accounts/temp',
    name: 'Temp',
    component: tempView
  },
  {
    path: '/post/create',
    name: 'PostCreate',
    component: PostCreateView
  },
  {
    // path: '/post/detail',
    path: '/post/detail/:post_id',
    name: 'PostDetail',
    component: PostDetailView,
  },
  {
    path: '/cafe/all',
    name: 'CafeList',
    component: CafeListView,
  },
  {
    path: '/cafe/detail/:cafe_id',
    // path: '/cafe/:id'
    name: 'CafeDetail',
    component: CafeDetailView,
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
