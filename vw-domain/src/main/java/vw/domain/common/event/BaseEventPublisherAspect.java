package vw.domain.common.event;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Aspect //  해당 클래스가 AOP의 Aspect 역할을 수행함을 표시
@Component // 해당 클래스를 스프링의 컴포넌트로 등록
@ConditionalOnExpression(
		"${ableDomainEvent:true}") // 지정된 표현식 ${ableDomainEvent:true}이 true인 경우에만 해당 Aspect가 활성화
public class BaseEventPublisherAspect
		implements ApplicationEventPublisherAware { // AOP를 사용해 트랜잭션과 관련된 메서드 실행 시 도메인 이벤트 처리

	private ApplicationEventPublisher publisher;
	private final ThreadLocal<Boolean> threadLocal = new ThreadLocal<>();

	@Around(
			"@annotation(org.springframework.transaction.annotation.Transactional)") // @Transactional이 적용된 메서드를 감싸는 Aspect를 정의
	public Object handleEvent(ProceedingJoinPoint joinPoint)
			throws Throwable { // @Transactional이 적용된 메서드를 감싸는 방식으로 도메인 이벤트를 처리하는 Aspect 클래스

		Boolean appliedValue = threadLocal.get();
		boolean nested;

		if (appliedValue != null && appliedValue) { // 중첩된 트랙잭션이 있는지 확인
			nested = true;
		} else {
			nested = false;
			threadLocal.set(Boolean.TRUE);
		}

		// 중첩된 트랜잭션에 속하지 않으면, 이벤트 발행을 위한 ApplicationEventPublisher를 설정
		if (!nested) BaseEventPublisher.set(publisher);

		try {
			// 원본 메서드 실행
			return joinPoint.proceed();
		} finally {
			// 중첩된 트랜잭션에 속하지 않은 경우, 설정을 초기화
			if (!nested) {
				BaseEventPublisher.reset();
				threadLocal.remove();
			}
		}
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
		// Spring으로부터 ApplicationEventPublisher를 주입받아 멤버 변수에 할당
		this.publisher = eventPublisher;
	}
}
