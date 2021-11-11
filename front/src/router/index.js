import Vue from 'vue'
import Router from 'vue-router'
import MainPage from '@/components/MainPage'
import Editor from '@/components/Editor'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'MainPage',
      component: MainPage
    },
    {
      path: '/editor',
      name: 'Editor',
      component: Editor
    }
  ]
})
