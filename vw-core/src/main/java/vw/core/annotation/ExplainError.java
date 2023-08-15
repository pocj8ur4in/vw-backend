package vw.core.annotation;

import java.lang.annotation.*;
import org.springframework.stereotype.Component;

@Target({ElementType.FIELD}) // Field에 적용
@Retention(RetentionPolicy.RUNTIME) // 런타임 시에도 유지
@Documented // Java Documents에도 포함
@Component
public @interface ExplainError {
	String value() default "";
}
