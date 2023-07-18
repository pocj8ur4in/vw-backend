package vw.domain.common.event;

import org.springframework.context.ApplicationEventPublisher;

public class BaseEventPublisher { // ApplicationEventPublisher를 활용하여 도메인 이벤트를 발행 및 처리
    private static ThreadLocal<ApplicationEventPublisher> publisherLocal = new ThreadLocal<>();

    public static void raise(DomainEvent event) { // 도메인 이벤트를 발행
        // 이벤트가 null인 경우에는 종료
        if (event == null) return;

        // 현재 스레드의 ApplicationEventPublisher를 사용하여 이벤트를 발행
        if (publisherLocal.get() != null) {
            publisherLocal.get().publishEvent(event);
        }
    }

    public static void set(
            ApplicationEventPublisher publisher) { // 현재 스레드의 ApplicationEventPublisher를 설정
        publisherLocal.set(publisher);
    }

    public static void reset() { // 현재 스레드의 ApplicationEventPublisher를 제거
        publisherLocal.remove();
    }
}
