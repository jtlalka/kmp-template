package kmp.template.app.di

import kmp.template.environment.Environment
import kmp.template.environment.EnvironmentType
import kmp.template.environment.NoOpEnvironment
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val appModule: Module = module {
    single<Environment> { NoOpEnvironment(type = EnvironmentType.JVM) }
}
