import { localAxios } from "@/utils/http-common";

const local = localAxios(); // axios instance

const url = "/board"

function listArticle(success, fail) {
  local.get(`${url}`).then(success).catch(fail);
}

function listArticleByLike(success, fail) {
  local.get(`${url}/like`).then(success).catch(fail);
}

function registArticle(form, success, fail) {
  console.log("boardjs article", form);
  local.post(`${url}`, form,  {
    headers: {
    'Content-Type': 'multipart/form-data'
    }
    }).then(success).catch(fail);
}

function insertLike(like, success, fail) {
  local.post(`/like`, JSON.stringify(like)).then(success).catch(fail);
}

function deleteLike(like, success, fail) {
  console.log(like);
  local.delete(`/like/${like.userId}/${like.boardId}`).then(success).catch(fail);
}

function increaseBoardLike(like, success, fail) {
  local.put(`/like/increase/${like.boardId}`).then(success).catch(fail);
}

function decreaseBoardLike(like, success, fail) {
  local.put(`/like/decrease/${like.boardId}`).then(success).catch(fail);
}

function checkLikeStatus(like, success, fail) {
  local.get(`/like/${like.userId}/${like.boardId}`).then(success).catch(fail);
}

function deleteArticle(articleNo, success, fail) {
  local.delete(`/board/${articleNo}`).then(success).catch(fail);
}

export {
  listArticle,
  listArticleByLike,
  registArticle,
  insertLike,
  deleteLike,
  increaseBoardLike,
  decreaseBoardLike,
  checkLikeStatus,
  deleteArticle,
};
