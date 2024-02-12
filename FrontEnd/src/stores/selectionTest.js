//selection 생성
// import axios from "axios";
// import { ref, computed } from "vue";
// import { useAuthStore } from "@/stores/auth";
// import router from "@/router";

// const authStore = useAuthStore();

// const createSelection = async () => {
//   try {
//       authStore.updateUserInfoFromToken();
//       newSelection.value.userId = authStore.userInfo.sub;
//       const config = {
//           headers: {
//               Authoriation :`${authStore.accessToken}`,
//           },
//       };

//       const response = await axios.post(
//           `${import.meta.env.VITE_API_BASE_URL}/selection/register`,
//           newSelection.value,
//           config
//       );
//       console.log(response.data);
//       newSelection.value = {
//           userId: authStore.userInfo.sub,
//           myRecruitId: 1,
//           companyName: "test title",
//           companyCode: "test content",
//           step: 1,
//           state: "test state",
//           date: new Date("2024-02-08T00:00:00"),
//       };
//       // router.push();
//   } catch (error) {
//       console.error(error);
//   }
// };

// const newSelection = ref({
//   userId: authStore.userInfo.sub,
//   myRecruitId: 1,
//   companyName: "test title",
//   companyCode: "test content",
//   step: 1,
//   state: "test state",
//   date: new Date("2024-02-08T00:00:00"),
// });



//======================================================
//selection 전체 조회
// import axios from "axios";
// import { useAuthStore } from "@/stores/auth";
// import router from "@/router";

// const authStore = useAuthStore();

// let selections = [];

// const getSelections = async () => {
//   try {
//     authStore.updateUserInfoFromToken();
//     const config = {
//         headers: {
//             Authoriation :`${authStore.accessToken}`,
//         },
//     };
//     const response = await axios.get(
//       `${import.meta.env.VITE_API_BASE_URL}/selection/${authStore.userInfo.sub}`,
//       config
//     );
//     selections = response.data;
//     console.log(selections);
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
//selection 조회
// import axios from "axios";
// import { ref, computed } from "vue";
// import { useAuthStore } from "@/stores/auth";
// import router from "@/router";

// const authStore = useAuthStore();

// const selection = ref({
//   userId: authStore.userInfo.sub,
//   myRecruitId: 1,
//   companyName: "test title",
//   companyCode: "test content",
//   step: 1,
//   state: "test state",
//   date: new Date("2024-02-08T00:00:00"),
// });

// const getSelection = async () => {
//   try {
//     authStore.updateUserInfoFromToken();
//     const config = {
//         headers: {
//             Authoriation :`${authStore.accessToken}`,
//         },
//     };
//     const response = await axios.get(
//       `${import.meta.env.VITE_API_BASE_URL}/selection/detail/2`,
//       config
//     );
//     selection.value = response.data;
//     console.log(selection.value);
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
//selection 수정
// import axios from "axios";
// import { ref, computed } from "vue";
// import { useAuthStore } from "@/stores/auth";
// import router from "@/router";

// const authStore = useAuthStore();

// const updateSelection = async () => {
//   try {
//       authStore.updateUserInfoFromToken();
//       const config = {
//           headers: {
//               Authoriation :`${authStore.accessToken}`,
//           },
//       };

//       const newSelection = {
//         id: 1,
//         userId: authStore.userInfo.sub,
//         myRecruitId: 1,
//         companyName: "test title22222222222",
//         companyCode: "test content2222222222222",
//         step: 2,
//         state: "test state2222222222222",
//         date: new Date("2024-02-08T00:00:00"),
//       };
//       const response = await axios.patch(
//           `${import.meta.env.VITE_API_BASE_URL}/selection/update`,
//           newSelection,
//           config
//       );
//       console.log(response.data);
//       // router.push();
//   } catch (error) {
//       console.error(error);
//   }
// };




//======================================================
//selection 삭제
// import axios from "axios";
// import { useAuthStore } from "@/stores/auth";

// const authStore = useAuthStore();

// const deleteSelection = async () => {
//   const isConfirmed = confirm("삭제하시겠습니까?");

//   if (isConfirmed) {
//     try {
//       const config = {
//         headers: {
//           Authorization: `${authStore.accessToken}`,
//         },
//       };
//       const res = await axios.delete(
//         `${import.meta.env.VITE_API_BASE_URL}/selection/delete/3`,
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