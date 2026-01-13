package kmp.template.preferences.di

import kmp.template.preferences.internal.db.DatabaseDriverFactory
import kmp.template.preferences.internal.db.IosDatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal actual val preferencesPlatformModule: Module = module {

    singleOf(::IosDatabaseDriverFactory) bind DatabaseDriverFactory::class
}
