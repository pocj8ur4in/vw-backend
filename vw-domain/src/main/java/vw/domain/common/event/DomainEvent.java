package vw.domain.common.event;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public abstract class DomainEvent {
    private final LocalDateTime timestamp; // 이벤트 발생 시간

    public DomainEvent() {
        this.timestamp = LocalDateTime.now();
    }
}
