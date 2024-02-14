<template>
  <div class="container">
    <div class="header">
      <div class="img">
        <img src="@/assets/img/blog/diary-img.jpg" alt="" />
      </div>
      <div class="title">
        <a class="btn f-color-w h-solid-g">
          <i class="fa-solid fa-caret-left"></i>
          수정 취소
        </a>
        <h3>수정하기</h3>
        <a
          @click="saveJournal"
          class="btn lined-bg f-color-c h-lined-c a-solid-c"
        >
          <i class='fa fa-floppy-o'></i>
          수정 완료
        </a>
        <h3>00월 00일의 일기</h3>
        <a class="btn lined-bg f-color-c h-lined-c a-solid-c"> 작성 완료 </a>
      </div>
    </div>

    <div class="content">
      <div class="title" v-if="journal">
        <i class="fa-solid fa fa-pencil fa-2xl"></i>
        <input
          type="text"
          class="input-simple focus-lined-c"
          placeholder="글을 작성해주세요"
          v-model="journal.content"
          required
        />
      </div>
      <textarea
        class="input focus-lined-c"
        placeholder="미리보기"
        v-model="journal.content"
        required
      ></textarea>
    </div>


  </div>
</template>

<script setup></script>

<style scoped>
.container {
  padding: 50px 0;
}

.header .img {
  width: 100%;
  height: 200px;
  overflow: hidden;
  border-radius: 10px 10px 0 0;
}
.img img {
  width: 100%;
}

.title {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 50px 20px 20px 20px;
  border-radius: 0 0 10px 10px;
  border: 1px solid var(--border-gray);
  border-top: none;
}
.title a {
  display: flex;
  gap: 5px;
}

.content {
  padding: 30px 0;
}

.content textarea {
  width: 100%;
  height: 500px;
  padding: 20px;
  resize: none; /* Prevents resizing */
  font-size: 16px;
}
</style>
