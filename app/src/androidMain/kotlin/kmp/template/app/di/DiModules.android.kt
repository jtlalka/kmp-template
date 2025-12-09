package kmp.template.app.di

import kmp.template.app.init.AndroidEnvironment
import kmp.template.environment.Environment
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal actual val appModule: Module = module {
    singleOf(::AndroidEnvironment) bind Environment::class
}
