package com.ssafy.joblog.domain.board.entity;

import com.ssafy.joblog.domain.board.dto.request.CommentUpdateRequestDto;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.global.entity.BaseEntity;
import com.ssafy.joblog.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

//@SQLDelete(sql = "UPDATE post_comment set is_delete = true WHERE comment_id = ?")
public class PostComment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int id;

    //연관관계 주인(=외래키 갖고있음)
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    //연관관계 주인(=외래키 갖고있음)
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String content;

    //cascade를 하면 postcomment만 persist하면됨
    @OneToMany(mappedBy = "postComment", cascade = CascadeType.ALL)
    private List<PostCommentLike> postCommentLikes = new ArrayList<>();

    // 더티 체킹 변경사항을 db로 update 쿼리
    public void updateComment(CommentUpdateRequestDto commentUpdateRequestDto) {
        this.content = commentUpdateRequestDto.getContent();
    }

    @Builder
    // 생성자
    public PostComment(int id, Post post, User user, String content) {
        this.id = id;
        this.post = post;
        this.user = user;
        this.content = content;
    }

}