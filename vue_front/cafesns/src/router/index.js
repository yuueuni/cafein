import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/views/Home.vue'

// accounts
import SignupView from '@/views/accounts/SignupView.vue'
import LoginView from '@/views/accounts/LoginView.vue'
import MypageView from '@/views/accounts/MypageView.vue'

// post
import postCreateView from '@/views/post/postCreateView.vue'

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
    path: '/accounts/mypage',
    name: 'Mypage',
    component: MypageView
  },
  {
    path: '/post/create',
    name: 'postCreate',
    component: postCreateView
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
