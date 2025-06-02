package byyoun.board.like.controller;

import byyoun.board.like.service.ArticleLikeService;
import byyoun.board.like.service.response.ArticleLikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ArticleLikeController {
    private final ArticleLikeService articleLikeService;

    @GetMapping("/v1/article-like/articles/{articleId}/users/{userId}")
    public ArticleLikeResponse read(
            @PathVariable("articleId") Long articleId,
            @PathVariable("userId") Long userId
    ) {
        return articleLikeService.read(articleId, userId);
    }

    @GetMapping("/v1/article-like/articles/{articleId}/count")
    public Long  count(
            @PathVariable("articleId") Long articleId
    ) {
        return articleLikeService.count(articleId);
    }

    @PostMapping("/v1/article-like/articles/{articleId}/users/{userId}/pessimistic-lock-1")
    public void likePessmisticLock1(
            @PathVariable("articleId") Long articleId,
            @PathVariable("userId") Long userId
    ) {
        articleLikeService.likePessimisticLock1(articleId, userId);
    }

    @DeleteMapping("/v1/article-like/articles/{articleId}/users/{userId}/pessimistic-lock-1")
    public void unlikePessmisticLock1(
            @PathVariable("articleId") Long articleId,
            @PathVariable("userId") Long userId
    ) {
        articleLikeService.unlikePessimisticLock1(articleId, userId);
    }

    @PostMapping("/v1/article-like/articles/{articleId}/users/{userId}/pessimistic-lock-2")
    public void likePessmisticLock2(
            @PathVariable("articleId") Long articleId,
            @PathVariable("userId") Long userId
    ) {
        articleLikeService.likePessimisticLock2(articleId, userId);
    }

    @DeleteMapping("/v1/article-like/articles/{articleId}/users/{userId}/pessimistic-lock-2")
    public void unlikePessmisticLock2(
            @PathVariable("articleId") Long articleId,
            @PathVariable("userId") Long userId
    ) {
        articleLikeService.unlikePessimisticLock2(articleId, userId);
    }

    @PostMapping("/v1/article-like/articles/{articleId}/users/{userId}/optimistic-lock")
    public void likeOptimisticLock1(
            @PathVariable("articleId") Long articleId,
            @PathVariable("userId") Long userId
    ) {
        articleLikeService.likeOptimisticLock(articleId, userId);
    }

    @DeleteMapping("/v1/article-like/articles/{articleId}/users/{userId}/optimistic-lock")
    public void unlikeOptimisticLock1(
            @PathVariable("articleId") Long articleId,
            @PathVariable("userId") Long userId
    ) {
        articleLikeService.unlikeOptimisticLock(articleId, userId);
    }
}
