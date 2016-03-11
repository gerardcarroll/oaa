package com.overstock.android.prototype.module.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;
import javax.inject.Singleton;

/**
 * Created by itowey on 10/03/16.
 */
@Scope
@Singleton
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationScope {
}
