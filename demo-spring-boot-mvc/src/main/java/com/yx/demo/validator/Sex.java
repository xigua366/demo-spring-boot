package com.yx.demo.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
// 指定注解的实现类
@Constraint(validatedBy = SexValidator.class)
@Target( { METHOD, FIELD })
@Retention(RUNTIME)
public @interface Sex {
    String message() default "请输入正确的sex";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        Sex[] value();
    }
}
