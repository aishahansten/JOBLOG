//schedule 생성
// import axios from "axios";
// import { ref, computed } from "vue";
// import { useAuthStore } from "@/stores/auth";
// import router from "@/router";

// const authStore = useAuthStore();

// const createSchedule = async () => {
//   try {
//       authStore.updateUserInfoFromToken();
//       newSchedule.value.userId = authStore.userInfo.sub;
//       const config = {
//           headers: {
//               Authoriation :`${authStore.accessToken}`,
//           },
//       };

//       const response = await axios.post(
//           `${import.meta.env.VITE_API_BASE_URL}/schedule/register`,
//           newSchedule.value,
//           config
//       );
//       console.log(response.data);
//       newSchedule.value = {
//           userId: authStore.userInfo.sub,
//           title: "test title",
//           content: "test content",
//           startDate: new Date("2024-02-08T00:00:00"),
//           endDate: new Date("2024-02-08T00:00:00"),
//       };
//       // router.push();
//   } catch (error) {
//       console.error(error);
//   }
// };

// const newSchedule = ref({
//   userId: authStore.userInfo.sub,
//   title: "test title",
//   content: "test content",
//   startDate: new Date("2024-02-08T00:00:00"),
//   endDate: new Date("2024-02-08T00:00:00"),
// });



//======================================================
//schedule 전체 조회
// import axios from "axios";
// import { useAuthStore } from "@/stores/auth";
// import router from "@/router";

// const authStore = useAuthStore();

// let schedules = [];

// const getSchedules = async () => {
//   try {
//     authStore.updateUserInfoFromToken();
//     const config = {
//         headers: {
//             Authoriation :`${authStore.accessToken}`,
//         },
//     };
//     const response = await axios.get(
//       `${import.meta.env.VITE_API_BASE_URL}/schedule/${authStore.userInfo.sub}`,
//       config
//     );
//     schedules = response.data;
//     console.log(schedules);
//   } catch (err) {
//     if (err.response && err.response.status === 500) {
//       router.push("/login2");
//     } else {
//       // Handle other errors or a case where the error does not have a response object
//       console.log("token", token); // Logging the token for debugging purposes
//     }
//   }
// }

//======================================================
//schedule 조회
// import axios from "axios";
// import { ref, computed } from "vue";
// import { useAuthStore } from "@/stores/auth";
// import router from "@/router";

// const authStore = useAuthStore();

// const schedule = ref({
//   userId: authStore.userInfo.sub,
//   title: "",
//   content: "",
//   startDate: null,
//   endDate: null,
// });

// const getSchedule = async () => {
//   try {
//     authStore.updateUserInfoFromToken();
//     const config = {
//         headers: {
//             Authoriation :`${authStore.accessToken}`,
//         },
//     };
//     const response = await axios.get(
//       `${import.meta.env.VITE_API_BASE_URL}/schedule/detail/2`,
//       config
//     );
//     schedule.value = response.data;
//     console.log(schedule.value);
//   } catch (err) {
//     if (err.response && err.response.status === 500) {
//       router.push("/login2");
//     } else {
//       // Handle other errors or a case where the error does not have a response object
//       console.log("err", err); // Logging the token for debugging purposes
//     }
//   }
// }

//======================================================
//schedule 수정
// import axios from "axios";
// import { ref, computed } from "vue";
// import { useAuthStore } from "@/stores/auth";
// import router from "@/router";

// const authStore = useAuthStore();

// const updateSchedule = async () => {
//   try {
//       authStore.updateUserInfoFromToken();
//       const config = {
//           headers: {
//               Authoriation :`${authStore.accessToken}`,
//           },
//       };

//       const newSchedule = {
//           scheduleId: 2,
//           userId: authStore.userInfo.sub,
//           title: "test title2222222",
//           content: "test content22222222",
//           startDate: new Date("2024-02-08T00:00:00"),
//           endDate: new Date("2024-02-08T00:00:00"),
//       };
//       const response = await axios.patch(
//           `${import.meta.env.VITE_API_BASE_URL}/schedule/update`,
//           newSchedule,
//           config
//       );
//       console.log(response.data);
//       // router.push();
//   } catch (error) {
//       console.error(error);
//   }
// };




//======================================================
//schedule 삭제
// import axios from "axios";
// import { useAuthStore } from "@/stores/auth";

// const authStore = useAuthStore();

// const deleteSchedule = async () => {
//   const isConfirmed = confirm("삭제하시겠습니까?");

//   if (isConfirmed) {
//     try {
//       const config = {
//         headers: {
//           Authorization: `${authStore.accessToken}`,
//         },
//       };
//       const res = await axios.delete(
//         `${import.meta.env.VITE_API_BASE_URL}/schedule/delete/3`,
//         config
//       );
//       console.log(res.data);
//       // router.push();

//       // Optionally, emit an event to inform the parent component to update the comment list
//     } catch (error) {
//       console.error("삭제 실패: ", error);
//     }
//   } else {
//     console.log("삭제하지 않음.");
//   }
// };