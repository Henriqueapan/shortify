import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import PrimeVue from "primevue/config";
import API from "@/api"
import ToastService from "primevue/toastservice";

API.setup()
    .catch((error) => console.error("Exception when setting up axios", error));

const app = createApp(App).use(PrimeVue).use(ToastService);

app.mount('#app');
