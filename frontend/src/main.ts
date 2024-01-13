import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import axios from 'axios';

const app = createApp(App)

app.use(createPinia())
app.use(router)

app.mount('#app')

// Globally set the cross origin access on localhost api
axios.defaults.headers['Access-Control-Allow-Origin'] = '*';
axios.defaults.headers['Access-Control-Allow-Methods'] = 'POST';
axios.defaults.headers['Access-Control-Allow-Headers'] = 'Content-Type';
axios.defaults.baseURL = 'http://localhost:10001';
