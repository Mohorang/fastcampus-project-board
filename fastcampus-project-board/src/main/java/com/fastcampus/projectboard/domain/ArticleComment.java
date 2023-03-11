package com.fastcampus.projectboard.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;


@Entity
@Getter
@ToString
@Table(indexes ={
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
} )
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class ArticleComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @Setter
    private Article article;    // 게시글 (ID)

    @Column(nullable = false,length = 500)
    private String content;     // 본문

    @CreatedDate @Column(nullable = false)
    private LocalDateTime createdAt;    //생성 일시

    @CreatedBy @Column(nullable = false ,length = 100)
    private String createdBy;   // 생성자

    @LastModifiedDate @Column(nullable = false ,length = 10000)
    private LocalDateTime modifiedAt;   // 수정 일시

    @LastModifiedBy @Column(nullable = false ,length = 100)
    private String modifiedBy;  // 수정자

    private ArticleComment(Article article,String content){
        this.article = article;
        this.content = content;
    }
    public static ArticleComment of(Article article,String content){
        return new ArticleComment(article,content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComment)) return false;
        ArticleComment that = (ArticleComment) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
