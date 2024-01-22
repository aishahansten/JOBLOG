import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/home/HomeView.vue";
import LoginView from "../views/home/LoginView.vue";
import Test from "../views/test.vue";
import CoffeeChat from "@/views/coffee_chat/CoffeeChatView.vue";
import EmployeeRegister from "@/views/profile/EmployeeRegisterView.vue";
import JobPost from "@/views/job_post/JobPostView.vue";
import JobDetail from "@/views/job_post/JobDetailView.vue";
import ScheduleView from "@/views/blog/schedule/ScheduleView.vue";
import ApplicantBoard from "@/views/community/board/ApplicantBoardView.vue";
import EmployeeBoard from "@/views/community/board/EmployeeBoardView.vue";
import BoardCreate from "@/views/community/board/BoardCreateView.vue";
import QnABoard from "@/views/community/qna_board/QnABoardView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "Home",
      component: HomeView,
    },
    {
      path: "/login",
      name: "Login",
      component: LoginView,
    },
    {
      path: "/test",
      name: "test",
      component: Test,
    },
    {
      path: "/coffee-chat",
      name: "Coffee",
      component: CoffeeChat,
    },
    {
      path: "/employee-register",
      name: "EmployeeRegister",
      component: EmployeeRegister,
    },
    {
      path: "/job-posts",
      name: "Jobs",
      component: JobPost,
    },
    {
      path: "/job-detail",
      name: "JobDetail",
      component: JobDetail,
      // 추후에 각 번호로 입력 받아야함
      // /job-detail/<int: detail-pk>
    },
    {
      path: "/schedule",
      name: "Schedule",
      component: ScheduleView,
    },
    {
      path: "/applicant-board",
      name: "ApplicantBoard",
      component: ApplicantBoard,
    },
    {
      path: "/employee-board",
      name: "EmployeeBoard",
      component: EmployeeBoard,
    },
    {
      path: "/qna-board",
      name: "QnABoard",
      component: QnABoard,
    },
    {
      path: "/create-board",
      name: "CreateBoard",
      component: BoardCreate,
    },
  ],
});

export default router;
