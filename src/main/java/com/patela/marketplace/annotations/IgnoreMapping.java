package com.patela.marketplace.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation to be used to ignore the back reference properties
 * during update mapping procedure
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface IgnoreMapping {
}
