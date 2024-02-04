import axios from "axios";

// 환경 변수에서 API 기본 URL을 가져옴
const { VITE_API_BASE_URL } = import.meta.env;

function localAxios() {
  const instance = axios.create({
    baseURL: VITE_API_BASE_URL, // 환경 변수에서 가져온 API의 기본 URL 설정
    // headers: {
    //   "Content-Type": "application/json;charset=utf-8",
    // },
  });

  // 기본 요청 헤더 설정
  instance.defaults.headers.common["Authorization"] = "";
  instance.defaults.headers.post["Content-Type"] = "application/json";
  instance.defaults.headers.put["Content-Type"] = "application/json";

  // 요청 인터셉터 추가
  instance.interceptors.request.use(
    (config) => {
      // 쿠키에서 accessToken 읽기
      const accessToken = document.cookie
        .split("; ")
        .find((row) => row.startsWith("accessToken="))
        ?.split("=")[1];

      if (accessToken) {
        // 요청 헤더에 Authorization 추가
        config.headers["Authorization"] = `Bearer ${accessToken}`;
      }

      return config;
    },
    (error) => {
      return Promise.reject(error);
    }
  );

  return instance;
}

export { localAxios };
