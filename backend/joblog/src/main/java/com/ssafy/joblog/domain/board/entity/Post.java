package com.ssafy.joblog.domain.board.entity;

import com.ssafy.joblog.domain.board.dto.request.PostUpdateRequestDto;
import com.ssafy.joblog.domain.board.dto.response.PostResponseDto;
import com.ssafy.joblog.domain.board.repository.PostLikeRepository;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int id;

    //연관관계 주인(=외래키 갖고있음)
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private PostCategory category; //게시판 종류 [WORKER, APPLICANT, QNA]

    private String title;
    private String content;

    @ColumnDefault("0")
    private int hit;

    //cascade를 하면 post만 persist하면됨
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostLike> postLikes = new ArrayList<>();

    //cascade를 하면 post만 persist하면됨
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostComment> comments = new ArrayList<>();

    //조회수 증가 비즈니스 로직
    //도메인 주도 설계에서 엔티티 자체가 해결할 수 있다면 엔티티 안에 비즈니스 로직 추가
    @Transactional
    public void addHit(int countHit) {
        this.hit += countHit;
    }

    //댓글 개수 비즈니스 로직
    public int getCommentCount() { return comments.size(); }

    //좋아요 개수 비즈니스 로직
    public int getLikeCount() {
        return postLikes.size();
    }

    //더티 체킹을 통해 변경사항을 db로 update 쿼리 전송
    public void updatePost(PostUpdateRequestDto postUpdateRequestDto) {
        this.title = postUpdateRequestDto.getTitle();
        this.content = postUpdateRequestDto.getContent();
        this.category = postUpdateRequestDto.getCategory();
    }

    // 생성자
    public Post(int id, User user, PostCategory category, String title, String content, int hit) {
        this.id = id;
        this.user = user;
        this.category = category;
        this.title = title;
        this.content = content;
        this.hit = hit;
    }

}