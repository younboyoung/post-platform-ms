package byyoun.board.common.outboxmessagerelay;

import byyoun.board.common.event.Event;
import byyoun.board.common.event.EventPayload;
import byyoun.board.common.event.EventType;
import byyoun.board.common.snowflake.Snowflake;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OutboxEventPublisher {
    private final Snowflake outboxIdSnowflake = new Snowflake();
    private final Snowflake eventIdSnowflake = new Snowflake();
    private final ApplicationEventPublisher applicationEventPublisher;

//    public void publish(EventType type, EventPayload payload, Long shardKey) {
//        // articleId=10, shardKey == articleId
//        // 10 % 4 = 물리적 샤드 2
//        Outbox outbox = Outbox.create(
//                outboxIdSnowflake.nextId(),
//                type,
//                Event.of(
//                        eventIdSnowflake.nextId(), type, payload
//                ).toJson(),
//                shardKey % MessageRelayConstants.SHARD_COUNT
//        );
//        applicationEventPublisher.publishEvent(OutboxEvent.of(outbox));
//    }

    public void publish(EventType type, EventPayload payload, Long shardKey) {
        Outbox outbox = Outbox.create(
                outboxIdSnowflake.nextId(),
                type,
                Event.of(
                        eventIdSnowflake.nextId(), type, payload
                ).toJson(),
                shardKey % MessageRelayConstants.SHARD_COUNT
        );
        applicationEventPublisher.publishEvent(OutboxEvent.of(outbox));
    }
}
