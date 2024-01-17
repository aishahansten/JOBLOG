import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import GAuth from 'vue-google-oauth2'

const app = createApp(App)

app.use(createPinia())
app.use(router)

app.mount('#app')
