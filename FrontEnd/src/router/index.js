import { createRouter, createWebHistory } from "vue-router";

//FE 테스트 전용
import Test from "../views/test.vue";

//홈페이지 링크
import HomeView from "../views/home/HomeView.vue";

// 로그인 페이지
import LoginView from "../views/home/LoginView.vue";
import LoginLocalView from '@/views/home/LoginViewLocal.vue'
import SignIn from "../views/home/components/login/SignIn.vue"
import DeleteUser from "@/views/home/DeleteUserView.vue"

//취준토크 페이지
import CoffeeChat from "@/views/coffee_chat/CoffeeChatView.vue";
import ChatterSign from "@/views/coffee_chat/ChatterSign.vue";

//채용공고 페이지
import JobPost from "@/views/job_post/JobPostView.vue";
import JobDetail from "@/views/job_post/JobDetailView.vue";
import JobScrap from "@/views/job_post/ScrapJobView.vue";

//커뮤니티 페이지
import QnABoard from "@/views/community/qna_board/QnABoardView.vue";
import ResumeCreate from "@/views/blog/application/resume/ResumeCreateView.vue";
import QnADetail from "@/views/community/qna_board/QnADetailView.vue";
import QnACreate from "@/views/community/qna_board/QnACreateView.vue";
import QnACommentCreate from "@/views/community/qna_board/components/QnACommentCreate.vue";
import QnAUpdate from "@/views/community/qna_board/QnAUpdateView.vue";

//취준로그 페이지
import Schedule from "@/views/blog/schedule/ScheduleView.vue";
import Journal from "@/views/blog/journal/JournalView.vue";
import JournalCreate from "@/views/blog/journal/JournalCreateView.vue";
import Review from "@/views/blog/review/ReviewView.vue";
import ReviewCreate from "@/views/blog/review/ReviewCreateView.vue";
import Application from "@/views/blog/application/ApplicationView.vue";
import ResumeDetail from "@/views/blog/application/resume/ResumeDetailView.vue";
import ResumeUpdate from "@/views/blog/application/resume/ResumeUpdateView.vue";

//프로필 세팅 페이지
import ProfileSetting from "@/views/setting/SettingView.vue";
import AccountSetting from "@/views/setting/components/account/AccountSetting.vue";
import ResumeSetting from "@/views/setting/components/resume/ResumeSetting.vue";
import AuthStatus from "@/views/setting/components/employee-auth/AuthStatus.vue";
import NotificationSetting from "@/views/setting/components/notification/NotificationSetting.vue";
import WithdrawSetting from "@/views/setting/components/withdrawal/WithdrawSetting.vue";
import WithdrawCompleteView from "@/views/setting/WithdrawCompleteView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/test",
      name: "test",
      component: Test,
    },
    // 메인
    {
      path: "/",
      name: "Home",
      component: HomeView,
    },

    // 로그인
    {
      path: "/login",
      name: "Login",
      component: LoginView,
    },    
    {
      path: "/login2",
      name: "Login2",
      component: LoginLocalView,
    },
    {
      path: "/sign-in",
      name: "SignIn",
      component: SignIn,
    },
    {
      path: "/delete-user",
      name: "DeleteUserHandle",
      component: DeleteUser,
    },

    // 채용공고
    {
      path: "/job-posts",
      name: "Jobs",
      component: JobPost,
    },
    {
      path: "/job-detail/:id",
      name: "JobDetail",
      component: JobDetail,
    },
    {
      path: "/job-scrap",
      name: "JobScrap",
      component: JobScrap,
    },

    // 커피챗
    {
      path: "/coffee-chat",
      name: "Coffee",
      component: CoffeeChat,
    },
    {
      path: "/chatter-reg",
      name: "ChatterReg",
      component: ChatterSign,
    },

    // Q&A
    {
      path: "/qna-board",
      name: "QnABoard",
      component: QnABoard,
    },
    {
      path: "/qna-board/detail/:id",
      name: "QnADetail",
      component: QnADetail,
    },
    {
      path: "/qna-create",
      name: "QnACreate",
      component: QnACreate,
    },
    {
      path: "/qna-board/comment",
      name: "QnACommentCreate",
      props: true,
      component: QnACommentCreate,
    },
    {
      path: "/qna-board/update/:id",
      name: "QnAUpdate",
      props: true,
      component: QnAUpdate,
    },

    // 캘린더
    {
      path: "/blog-schedule",
      name: "BlogSchedule",
      component: Schedule,
    },

    // 다이어리
    {
      path: "/blog-journal",
      name: "BlogJournal",
      component: Journal,
    },
    {
      path: "/journal-create",
      name: "JournalCreate",
      component: JournalCreate,
    },

    // 전형리뷰
    {
      path: "/blog-review",
      name: "BlogReview",
      component: Review,
    },
    {
      path: "/review-create",
      name: "ReviewCreate",
      component: ReviewCreate,
    },

    // 이력서 및 자소서
    {
      path: "/blog-application",
      name: "BlogApplication",
      component: Application,
    },
    {
      path: "/blog-application/resume-create",
      name: "ResumeCreate",
      component: ResumeCreate,
    },
    {
      path: "/blog-application/resume/:id",
      name: "ResumeDetail",
      component: ResumeDetail,
    },
    {
      path: "/blog=applicatoin/resume/update/:id",
      name: "ResumeUpdate",
      component: ResumeUpdate
    },


    // 설정
    {
      path: "/setting",
      name: "ProfileSetting",
      redirect: "/setting/account",
      component: ProfileSetting,
      children: [
        {
          path: "account",
          name: "AccountSetting",
          component: AccountSetting,
        },
        {
          path: "resume",
          name: "ResumeSetting",
          component: ResumeSetting,
        },
        {
          path: "employee-auth",
          name: "AuthStatus",
          component: AuthStatus,
        },
        {
          path: "notification",
          name: "NotificationSetting",
          component: NotificationSetting,
        },
        {
          path: "withdraw",
          name: "WithdrawSetting",
          component: WithdrawSetting,
        },
      ],
    },
    {
      path: "/bye",
      name: "WithdrawCompleteView",
      component: WithdrawCompleteView,
    },
  ],
});

function checkAuthentication(cookieName) {
  const value = `; ${document.cookie}`
  const parts = value.split(`; ${cookieName}=`)
  if (parts.length === 2) {
    return true
  }
  return false
}


router.beforeEach((to, from, next) => {
  const isAuthenticated = checkAuthentication('accessToken')

  const publicRoutes = [
    'Login', 
    'Login2',
    'SignIn',
    'Jobs', 
    'Home',
    'JobDetail',
    'QnABoard',
    'QnADetail',
    'Coffee',
    'DeleteUserHandle'
  ];

  if (!isAuthenticated && !publicRoutes.includes(to.name)) {
    alert('로그인이 필요합니다'); // Notify the user
    next({ name: 'Login2' });
  } else if (isAuthenticated && to.name === 'Login2') {
    alert('이미 로그인되어 있습니다.')
    next({ name: 'Home' })
  } else {
    // In all other cases, proceed as normal
    next(); // Proceed to the route
  }
});



export default router;
