import axios from "axios";

// console.dir(import.meta.env)

const { VITE_API_BASE_URL } = import.meta.env;

function localAxios() {
  const instance = axios.create({
    baseURL: VITE_API_BASE_URL,
    // headers: {
    //   "Content-Type": "application/json;charset=utf-8",
    // },
  });
  // Request 발생 시 적용할 내용.
  instance.defaults.headers.common["Authorization"] = "";
  instance.defaults.headers.post["Content-Type"] = "application/json";
  instance.defaults.headers.put["Content-Type"] = "application/json";

  // Request, Response 시 설정한 내용을 적용.
  instance.interceptors.request.use((config) => {
    return config;
  }),
    (error) => {
      return Promise.reject(error);
    };
  return instance;
}

export { localAxios };
