package byyoun.board.articleread.service.event.handler;

import byyoun.board.articleread.repository.ArticleQueryModelRepository;
import byyoun.board.common.event.Event;
import byyoun.board.common.event.EventType;
import byyoun.board.common.event.payload.ArticleLikedEventPayload;
import byyoun.board.common.event.payload.ArticleUnlikedEventPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArticleUnlikedEventHandler implements EventHandler<ArticleUnlikedEventPayload> {

    private final ArticleQueryModelRepository articleQueryModelRepository;

    @Override
    public void handle(Event<ArticleUnlikedEventPayload> event) {
        articleQueryModelRepository.read(event.getPayload().getArticleId())
            .ifPresent(articleQueryModel -> {
                articleQueryModel.updatedBy(event.getPayload());
                articleQueryModelRepository.update(articleQueryModel);
            });
    }

    @Override
    public boolean supports(Event<ArticleUnlikedEventPayload> event) {
        return EventType.ARTICLE_UNLIKED == event.getType();
    }
}
