package com.example.nividimka.wakathink.di.scope

import javax.inject.Scope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@kotlin.annotation.Target(AnnotationTarget.CLASS,AnnotationTarget.FUNCTION)
annotation class FragmentScoped