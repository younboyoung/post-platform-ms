package byyoun.board.view.service;

import byyoun.board.view.entity.ArticleViewCount;
import byyoun.board.view.repository.ArticleViewCountBackUpRepository;
import byyoun.board.common.event.EventType;
import byyoun.board.common.event.payload.ArticleViewedEventPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import byyoun.board.common.outboxmessagerelay.OutboxEventPublisher;

@Component
@RequiredArgsConstructor
public class ArticleViewCountBackUpProcessor {
    private final OutboxEventPublisher outboxEventPublisher;
    private ArticleViewCountBackUpRepository articleViewCountBackUpRepository;

    @Transactional
    public void backUp(Long articleId, Long viewCount) {
        int result = articleViewCountBackUpRepository.updateViewCount(articleId, viewCount);
        if(result == 0) {
            articleViewCountBackUpRepository.findById(1L)
                    .ifPresentOrElse(ignored -> {},
                        () -> articleViewCountBackUpRepository.save(ArticleViewCount.init(articleId, viewCount))
                    );
        }

        outboxEventPublisher.publish(
                EventType.ARTICLE_VIEWED,
                ArticleViewedEventPayload.builder()
                        .articleId(articleId)
                        .articleViewCount(viewCount)
                        .build(),
                articleId
        );
    }
}
