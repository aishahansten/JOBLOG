<template>
<div class="main-container">
  <div class="review-header">
    <div class="review-left">
      <div>
        <span>채용공고명:</span> {{ review.myRecruitTitle }}
      </div>
      <div>
        <span>지원 직무:</span> {{ review.applyJob }}
      </div>
    </div>
    <div class="review-right">
      <div>
        <div><span>전형 종류:</span> {{ review.title }}</div>
        <div><span>전형 방법:</span> {{ review.type }}</div>
        <div><span>전형 단계:</span> {{ review.step }}단계</div>
      </div>
      <div>
        <div><span>소요 시간:</span> 약 {{ review.timeRequired }}분</div>
        <div><span>질문 개수:</span> 약 {{ review.totalQuestion }}개</div>
      </div>
      <div>
        <div><span>분위기:</span> {{ atmosDescription(review.atmosphere) }}</div>
        <div><span>난이도:</span> {{ difDescription(review.difficulty) }}</div>
      </div>
    </div>
  </div>
  <div class="review-body">
    <div><span>짧은 한마디</span></div>
    <div class="review-comment">{{ review.comment }}</div>
  </div>
</div>
</template>

<script setup>

const props = defineProps ({
    review: Object
})

console.log(props.review)

function atmosDescription(value) {
  const descriptions = {
    1: '매우 편안함',
    2: '편안함',
    3: '보통',
    4: '엄격함',
    5: '매우 엄격함',
  };
  return descriptions[value] || '정보 없음';
}

function difDescription(value) {
  const descriptions = {
    1: '매우 쉬움',
    2: '쉬움',
    3: '보통',
    4: '어려움',
    5: '매우 어려움',
  };
  return descriptions[value] || '정보 없음';
}
</script>

<style scoped>

  .main-container {
    display: flex;
    flex-direction: column;
    border: 1px solid rgba(0, 0, 0, 0.152);
    border-radius: 8px;
    margin-bottom: 3px;
  }
  .review-header { 
    display: grid;
    grid-template-columns: 1fr 2fr;
    border-bottom: 1px solid rgba(0, 0, 0, 0.152);
    margin-bottom: 10px;
    padding: 10px;
    box-sizing: border-box;
  }

  .review-body {
    display: flex;
    flex-direction: column;
    gap: 10px;
    padding: 10px;
    box-sizing: border-box;
  }

  .review-left {
    display: flex;
    flex-direction: column;
    gap: 10px;
  }

  .review-right {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
  }

  span {
    font-weight: bold;
  }
</style>