import { createApp } from "vue";
import { createPinia } from "pinia";

import App from "./App.vue";
import router from "./router";
import "@/common/css/font.css";
import "@/common/css/base.css";
import "@/common/css/arrange.css";
import "@/common/css/element.css";
import "@/common/css/button.css";
import "@/common/css/cursor.css";
import "@/common/css/input.css";

const app = createApp(App);

app.use(createPinia());
app.use(router);

app.mount("#app");
