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

router.beforeEach((to, from, next) => {
  const publicPages = ['Home', 'Signup', 'Login', 'PostDetail', 'CafeList', 'CafeDetail', ]  //login 안해도 됨
  const privatePages = ['Login', 'Signup', ]  //login 되어 있으면 안됨

  const authRequired = !publicPages.includes(to.name)
  const unauthRequired = privatePages.includes(to.name)  //로그인 안한 상태가 필요함
  
  const isLoggedIn = !!Vue.$cookies.isKey('auth-token')

  document.title = to.meta.title

  if (unauthRequired && isLoggedIn) {
    next('/')
  }

  if (authRequired && !isLoggedIn) {
    next({ next: 'Login' })
  } else {
    next()
  }
})

export default router
