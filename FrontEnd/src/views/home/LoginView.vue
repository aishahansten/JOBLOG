<template>
    <body>
      <div class="image-container">
        <button class="social-login-btn"> <img src="../../assets/img/login/kakao_login.png" alt="kakao login"> </button>
        <img src="../../assets/img/login/login-background.png" alt="login background" class="login-background-img">
      </div>
    </body>
    <div>
  
    </div>
  
  </template>
  
  <script setup>
  
  </script>
  
  <style scoped>
    .image-container {
      padding: 0px;
      margin: 0px;
    }
    .login-background-img {
      display: flex;
      width: 1344px;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      z-index: -1; /* Ensure the background image is behind other elements */
    }
  
    .social-login-btn {
      position: relative;
      top: 500px;
      left: 50%;
      transform: translate(-50%, -50%);
      border: 0px;
      background-color: white;
      /* Additional styling for the button if needed */
    }
  
  </style>