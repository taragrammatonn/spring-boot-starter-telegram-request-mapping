package com.flux.spring.boot.telegram.mapping.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BotRequestMapping {

    @AliasFor("value")
    String[] command() default {};

    @AliasFor("command")
    String[] value() default {};
}
