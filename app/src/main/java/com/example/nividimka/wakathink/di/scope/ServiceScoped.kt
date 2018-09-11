package com.example.nividimka.wakathink.di.scope

import java.lang.annotation.ElementType
import javax.inject.Scope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@kotlin.annotation.Target(AnnotationTarget.CLASS,AnnotationTarget.FUNCTION)
annotation class ServiceScoped