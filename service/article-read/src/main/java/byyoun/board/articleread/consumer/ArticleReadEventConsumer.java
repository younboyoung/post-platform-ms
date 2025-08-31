package byyoun.board.articleread.consumer;

import byyoun.board.articleread.service.ArticleReadService;
import byyoun.board.common.event.Event;
import byyoun.board.common.event.EventPayload;
import byyoun.board.common.event.EventType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ArticleReadEventConsumer {
    private final ArticleReadService articleReadService;

    @KafkaListener(topics = {
            EventType.Topic.YBY_BOARD_ARTICLE,
            EventType.Topic.YBY_BOARD_COMMENT,
            EventType.Topic.YBY_BOARD_LIKE,
    })
    public void listen(String message, Acknowledgment ack) {
        log.info("[ArticleReadEventConsumer.listen] message: {}", message);
        Event<EventPayload> event = Event.fromJson(message);
        if(event != null) {
            articleReadService.handleEvent(event);
        }
        ack.acknowledge();
    }
}
