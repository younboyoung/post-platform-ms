package byyoun.board.articleread.service.event.handler;

import byyoun.board.common.event.Event;
import byyoun.board.common.event.EventPayload;

public interface EventHandler<T extends EventPayload> {
    void handle(Event<T> event);
    boolean supports(Event<T> event);
}
