package vw.core.annotation;

import java.lang.annotation.*;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

@Target({ElementType.TYPE}) // Type에 적용
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface UseCase {
	@AliasFor(annotation = Component.class) // 다른 어노테이션의 속성에 대한 별칭 정의
	String value() default "";
}
