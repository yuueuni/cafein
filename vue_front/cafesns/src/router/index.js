import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/views/Home.vue'

// accounts
import SignupView from '@/views/accounts/SignupView.vue'
import LoginView from '@/views/accounts/LoginView.vue'
import MypageView from '@/views/accounts/MypageView.vue'

// post
import PostCreateView from '@/views/post/PostCreateView.vue'
import PostDetailView from '@/views/post/PostDetailView.vue'

// cafe
import CafeDetailView from '@/views/CafeDetailView.vue'

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
    name: 'PostCreate',
    component: PostCreateView
  },
  {
    path: '/post/detail',
    // path: '/post/:id'
    name: 'PostDetail',
    component: PostDetailView,
  },
  {
    path: '/cafe/detail',
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
