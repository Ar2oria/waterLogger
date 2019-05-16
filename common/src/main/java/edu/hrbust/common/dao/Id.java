package edu.hrbust.common.dao;

import java.lang.annotation.*;

/**
 * primary key mark
 *
 * @author zhengweichao
 * @since 1.0.0.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Id {

}
