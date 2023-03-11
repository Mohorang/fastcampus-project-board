package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.config.JpaConfig;
import com.fastcampus.projectboard.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository
    ) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorsksFine(){
        //Given

        //When
        List<Article> article = articleRepository.findAll();

        //Then
        assertThat(article).isNotNull().hasSize(100);
    }

    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorsksFine(){
        //Given
        long previousCount = articleRepository.count();


        //When
        Article savedArticle = articleRepository.save(Article.of("new article", "new content", "#spring"));

        //Then
        assertThat(articleRepository.count()).isEqualTo(previousCount +1 );
    }

    @DisplayName("update 테스트")
    @Test
    void givenTestData_whenUpdating_thenWorsksFine(){
        //Given
        Article article = articleRepository.findById(1L).orElseThrow();
        String updateHashtag = "#springboot";
        article.setHashtag(updateHashtag);


        //When
        Article savedArticle = articleRepository.save(article);

        //Then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag",updateHashtag);
    }
}