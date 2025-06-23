package byyoun.board.hotarticle.service.response;

import byyoun.board.hotarticle.client.ArticleClient;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class HotArticleResponse {
    private Long articleId;
    private String title;
    private LocalDateTime createAt;

    public static HotArticleResponse from(ArticleClient.ArticleResponse articleResponse) {
        HotArticleResponse response = new HotArticleResponse();
        response.articleId = articleResponse.getArticleId();
        response.title = articleResponse.getTitle();
        response.createAt = articleResponse.getCreatedAt();
        return response;
    }
}
