<script setup>
import { ref } from "vue";
import "@/assets/css/setting/setting-header.css";
import "@/assets/css/setting/menu/notification-setting.css";

const isScrapAlertOn = ref(true);
const isOtherAlertOn = ref(false);
const isCoffeeReserveAlertOn = ref(true);
const isCoffeeStartAlertOn = ref(false);

// 함수 1개로 처리하고 싶으나 지역 변수 문제로 해결 못함 왕 지저분..
function toggleScrapAlert() {
  isScrapAlertOn.value = !isScrapAlertOn.value;
}
function toggleOtherAlert() {
  isOtherAlertOn.value = !isOtherAlertOn.value;
}
function toggleCoffeeReserveAlert() {
  isCoffeeReserveAlertOn.value = !isCoffeeReserveAlertOn.value;
}
function toggleCoffeeStartAlert() {
  isCoffeeStartAlertOn.value = !isCoffeeStartAlertOn.value;
}
</script>

<template>
  <div class="content-container">
    <div class="content-info-space">
      <div class="content-info">
        <p class="content-title">알림 설정</p>
        <p>카카오톡, 지메일, 사이트 내 알림 등을 설정합니다.</p>
      </div>
    </div>

    <div class="notify-content">
      <section>
        <div class="section-title">
          <h2>나의 일정관리</h2>
          <p>나의 일정관리 관련 알림 설정</p>
        </div>
        <article>
          <div class="line">
            <span>스크랩 공고 마감 전 알림</span>
            <div class="toggle-btn btn-on" v-if="isScrapAlertOn">
              <div class="on">ON</div>
              <div class="off clickable-off" @click="toggleScrapAlert">OFF</div>
            </div>
            <div class="toggle-btn btn-off" v-if="!isScrapAlertOn">
              <div class="on clickable-on" @click="toggleScrapAlert">ON</div>
              <div class="off">OFF</div>
            </div>
          </div>
          <div class="line">
            <span>기타 일정 시작 전 알림</span>
            <div class="toggle-btn btn-on" v-if="isOtherAlertOn">
              <div class="on">ON</div>
              <div class="off clickable-off" @click="toggleOtherAlert">OFF</div>
            </div>
            <div class="toggle-btn btn-off" v-if="!isOtherAlertOn">
              <div class="on clickable-on" @click="toggleOtherAlert">ON</div>
              <div class="off">OFF</div>
            </div>
          </div>
        </article>
      </section>
      <section>
        <div class="section-title">
          <h2>커피챗</h2>
          <p>커피챗 관련 알림 설정</p>
        </div>
        <article>
          <div class="line">
            <span>커피챗 예약 시간 확정 알림</span>
            <div class="toggle-btn btn-on" v-if="isCoffeeReserveAlertOn">
              <div class="on">ON</div>
              <div class="off clickable-off" @click="toggleCoffeeReserveAlert">
                OFF
              </div>
            </div>
            <div class="toggle-btn btn-off" v-if="!isCoffeeReserveAlertOn">
              <div class="on clickable-on" @click="toggleCoffeeReserveAlert">
                ON
              </div>
              <div class="off">OFF</div>
            </div>
          </div>
          <div class="line">
            <span>커피챗 예약 시간 10분 전 알림</span>
            <div class="toggle-btn btn-on" v-if="isCoffeeStartAlertOn">
              <div class="on">ON</div>
              <div class="off clickable-off" @click="toggleCoffeeStartAlert">
                OFF
              </div>
            </div>
            <div class="toggle-btn btn-off" v-if="!isCoffeeStartAlertOn">
              <div class="on clickable-on" @click="toggleCoffeeStartAlert">
                ON
              </div>
              <div class="off">OFF</div>
            </div>
          </div>
        </article>
      </section>
    </div>
  </div>
</template>
