package etu1889.framework.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

public @interface AnnotationUrl {
    String url() default "/";
}
