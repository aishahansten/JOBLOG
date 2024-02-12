//myRecruit 생성
// import axios from "axios";
// import { ref, computed } from "vue";
// import { useAuthStore } from "@/stores/auth";
// import router from "@/router";

// const authStore = useAuthStore();

// const createMyRecruit = async () => {
//   try {
//       authStore.updateUserInfoFromToken();
//       newMyRecruit.value.userId = authStore.userInfo.sub;
//       const config = {
//           headers: {
//               Authoriation :`${authStore.accessToken}`,
//           },
//       };

//       const response = await axios.post(
//           `${import.meta.env.VITE_API_BASE_URL}/myRecruit/register`,
//           newMyRecruit.value,
//           config
//       );
//       console.log(response.data);
//       newMyRecruit.value = {
//         userId: authStore.userInfo.sub,
//         companyName: "test company",
//         companyCODE: "test code",
//         job: "test job",
//         openingDate: new Date("2024-02-08T00:00:00"),
//         expirationDate: new Date("2024-02-08T00:00:00"),
//         description: "test description",
//       };
//       // router.push();
//   } catch (error) {
//       console.error(error);
//   }
// };

// const newMyRecruit = ref({
//   userId: authStore.userInfo.sub,
//   companyName: "test company",
//   companyCODE: "test code",
//   job: "test job",
//   openingDate: new Date("2024-02-08T00:00:00"),
//   expirationDate: new Date("2024-02-08T00:00:00"),
//   description: "test description",
// });



//======================================================
//myRecruit 전체 조회
// import axios from "axios";
// import { useAuthStore } from "@/stores/auth";
// import router from "@/router";

// const authStore = useAuthStore();

// let myRecruits = [];

// const getMyRecruits = async () => {
//   try {
//     authStore.updateUserInfoFromToken();
//     const config = {
//         headers: {
//             Authoriation :`${authStore.accessToken}`,
//         },
//     };
//     const response = await axios.get(
//       `${import.meta.env.VITE_API_BASE_URL}/myRecruit/${authStore.userInfo.sub}`,
//       config
//     );
//     myRecruits = response.data;
//     console.log(myRecruits);
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
//myRecruit 조회
// import axios from "axios";
// import { ref, computed } from "vue";
// import { useAuthStore } from "@/stores/auth";
// import router from "@/router";

// const authStore = useAuthStore();

// const myRecruit = ref({
//   userId: authStore.userInfo.sub,
//   companyName: "",
//   companyCODE: "",
//   job: "",
//   openingDate: new Date(),
//   expirationDate: new Date(),
//   description: "",
// });

// const getMyRecruit = async () => {
//   try {
//     authStore.updateUserInfoFromToken();
//     const config = {
//         headers: {
//             Authoriation :`${authStore.accessToken}`,
//         },
//     };
//     const response = await axios.get(
//       `${import.meta.env.VITE_API_BASE_URL}/myRecruit/detail/1`,
//       config
//     );
//     myRecruit.value = response.data;
//     console.log(myRecruit.value);
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
//myRecruit 수정
// import axios from "axios";
// import { ref, computed } from "vue";
// import { useAuthStore } from "@/stores/auth";
// import router from "@/router";

// const authStore = useAuthStore();

// const updateMyRecruit = async () => {
//   try {
//       authStore.updateUserInfoFromToken();
//       const config = {
//           headers: {
//               Authoriation :`${authStore.accessToken}`,
//           },
//       };

//       const newMyRecruit = {
//           id: 1,
//           userId: authStore.userInfo.sub,
//           companyName: "test company22222",
//           companyCODE: "test company22222",
//           job: "test company22222",
//           openingDate: new Date(),
//           expirationDate: new Date(),
//           description: "test company22222",
//       };
//       const response = await axios.patch(
//           `${import.meta.env.VITE_API_BASE_URL}/myRecruit/update`,
//           newMyRecruit,
//           config
//       );
//       console.log(response.data);
//       // router.push();
//   } catch (error) {
//       console.error(error);
//   }
// };



//======================================================
//myRecruit 삭제
// import axios from "axios";
// import { useAuthStore } from "@/stores/auth";

// const authStore = useAuthStore();

// const deleteMyRecruit = async () => {
//   const isConfirmed = confirm("삭제하시겠습니까?");

//   if (isConfirmed) {
//     try {
//       const config = {
//         headers: {
//           Authorization: `${authStore.accessToken}`,
//         },
//       };
//       const res = await axios.delete(
//         `${import.meta.env.VITE_API_BASE_URL}/myRecruit/delete/1`,
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