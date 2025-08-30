package byyoun.board.articleread.service.event.handler;

import byyoun.board.articleread.repository.ArticleQueryModelRepository;
import byyoun.board.common.event.Event;
import byyoun.board.common.event.EventType;
import byyoun.board.common.event.payload.CommentCreatedEventPayload;
import byyoun.board.common.event.payload.CommentDeletedEventPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentDeletedEventHandler implements EventHandler<CommentDeletedEventPayload> {

    private final ArticleQueryModelRepository articleQueryModelRepository;

    @Override
    public void handle(Event<CommentDeletedEventPayload> event) {
        articleQueryModelRepository.read(event.getPayload().getArticleId())
            .ifPresent(articleQueryModel -> {
                articleQueryModel.updatedBy(event.getPayload());
                articleQueryModelRepository.update(articleQueryModel);
            });
    }

    @Override
    public boolean supports(Event<CommentDeletedEventPayload> event) {
        return EventType.COMMENT_DELETED == event.getType();
    }
}
