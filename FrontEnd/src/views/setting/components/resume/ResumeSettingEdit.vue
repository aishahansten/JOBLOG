<script setup>
import "@/assets/css/setting/setting-header.css";
import "@/assets/css/setting/menu/resume-setting.css";
</script>

<template>
  <div class="content-container">
    <div class="content-info-space">
      <div class="content-info">
        <p class="content-title">이력 정보 설정</p>
        <p>이력서 작성에 사용될 개인 이력 정보를 변경합니다.</p>
      </div>
      <div class="content-function">
        <a href="" class="update-btn clickable">변경 완료</a>
      </div>
    </div>

    <div class="resume-content">
      <div class="component" id="half">
        <div class="description">
          <p class="title">취업사진 등록</p>
          <p>이력서에 사용될 증명사진입니다.</p>
        </div>
        <div class="identify-pic-space">
          <div class="input-pic-guide">
            <ul>
              <li>얼굴이 뚜렷하게 드러난 사진 파일</li>
              <li>배경이 없는 png 등이 아닌 사진 파일</li>
            </ul>
            <input type="file" class="file-input" accept="image/jpg, image/jpeg" />
          </div>
          <div class="identify-pic">
            <img src="@/assets/img/profile/default-user-pic.jpg" alt="" />
          </div>
        </div>
      </div>
      <div class="component">
        <div class="description">
          <p class="title">인적사항 등록</p>
          <p>이력서에 사용될 성명, 주소, 연락처입니다.</p>
        </div>
        <div class="info-space">
          <div>
            <div class="info">
              <span class="tag">성명(한글)</span>
              <input type="text" placeholder="원래 이름. 없으면 '성명(한글)'" />
            </div>
            <div class="info">
              <span class="tag">성명(영문)</span>
              <input type="text" placeholder="원래 이름. 없으면 '성명(영문)'" />
            </div>
          </div>
          <div>
            <div class="info">
              <span class="tag">생년월일</span>
              <input
                type="text"
                placeholder="원래 생년월일. 없으면 '생년월일'"
              />
            </div>
            <div class="info">
              <span class="tag">성별</span>
              <input type="text" placeholder="원래 성별. 없으면 '성별'" />
            </div>
          </div>
          <div>
            <div class="info">
              <span class="tag">전화번호</span>
              <input
                type="text"
                placeholder="원래 전화번호. 없으면 '전화번호'"
              />
            </div>
            <div class="info">
              <span class="tag">이메일 주소</span>
              <input
                type="text"
                placeholder="원래 이메일. 없으면 '이메일 주소'"
              />
            </div>
          </div>
          <div>
            <div class="info">
              <span class="tag">현주소(실거주지)</span>
              <input
                type="text"
                placeholder="원래 주소. 없으면 '현주소(실거주지)'"
                id="long"
              />
            </div>
          </div>
        </div>
      </div>
      <div class="component">
        <div class="description">
          <p class="title">학적사항 등록</p>
          <p>고등학교, 대학원, 대학원 등의 학적입니다.</p>
        </div>
        <div>
          <table>
            <tr>
              <th class="tag">학교명</th>
              <th class="tag">전공 및 학위</th>
              <th class="tag">입학 및 졸업 연/월</th>
            </tr>
            <tr class="data">
              <td>
                <input type="text" placeholder="원래 학교명. 없으면 '학교명'" />
              </td>
              <td>
                <input
                  type="text"
                  placeholder="원래 전공과 학위. 없으면 '전공 및 학위'"
                />
              </td>
              <td>
                <input type="text" placeholder="0000년 0월 ~ 0000년 0월" />
              </td>
            </tr>
          </table>
          <div>
            <a class="add-btn click">+</a>
          </div>
        </div>
      </div>
      <div class="component">
        <div class="description">
          <p class="title">경력사항 등록</p>
          <p>이력서에 등록할 수 있는 경력사항입니다.</p>
        </div>
        <div>
          <table>
            <tr>
              <th class="tag">기관명</th>
              <th class="tag">담당 직무</th>
              <th class="tag">재직 기간</th>
            </tr>
            <tr class="data">
              <td>
                <input type="text" placeholder="원래 학교명. 없으면 '학교명'" />
              </td>
              <td>
                <input
                  type="text"
                  placeholder="원래 전공과 학위. 없으면 '전공 및 학위'"
                />
              </td>
              <td>
                <input type="text" placeholder="0000년 0월 ~ 0000년 0월" />
              </td>
            </tr>
          </table>
          <div>
            <a class="add-btn click">+</a>
          </div>
        </div>
      </div>
      <div class="component">
        <div class="description">
          <p class="title">자격 및 교육사항 등록</p>
          <p>이력서에 등록할 수 있는 자격·면허사항 및 교육사항입니다.</p>
        </div>
        <div>
          <table>
            <tr>
              <th class="tag">자격명</th>
              <th class="tag">발급일</th>
              <th class="tag">발급기관</th>
            </tr>
            <tr class="data">
              <td>
                <input type="text" placeholder="원래 학교명. 없으면 '학교명'" />
              </td>
              <td>
                <input
                  type="text"
                  placeholder="원래 전공과 학위. 없으면 '전공 및 학위'"
                />
              </td>
              <td>
                <input type="text" placeholder="0000년 0월 ~ 0000년 0월" />
              </td>
            </tr>
          </table>
          <div>
            <a class="add-btn click">+</a>
          </div>
        </div>
      </div>
      <div class="component">
        <div class="description">
          <p class="title">수상이력 등록</p>
          <p>이력서에 등록할 수 있는 공모전, 대회 등의 수상이력입니다.</p>
        </div>
        <div>
          <table>
            <tr>
              <th class="tag">대회 및 수상명</th>
              <th class="tag">수상일</th>
              <th class="tag">주최기관</th>
            </tr>
            <tr class="data">
              <td>
                <input type="text" placeholder="원래 학교명. 없으면 '학교명'" />
              </td>
              <td>
                <input
                  type="text"
                  placeholder="원래 전공과 학위. 없으면 '전공 및 학위'"
                />
              </td>
              <td>
                <input type="text" placeholder="0000년 0월 ~ 0000년 0월" />
              </td>
            </tr>
          </table>
          <div>
            <a class="add-btn click">+</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
