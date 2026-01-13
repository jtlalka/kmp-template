package kmp.template.preferences.di

import kmp.template.preferences.Preferences
import kmp.template.preferences.internal.DbPreferences
import kmp.template.preferences.internal.db.DatabaseQueryProvider
import kmp.template.preferences.internal.db.dao.PreferencesDao
import kmp.template.preferences.internal.db.dao.PreferencesDbDao
import kmp.template.preferences.internal.serializer.PrimitivesSerializer
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal expect val preferencesPlatformModule: Module

val preferencesModule: Module = module {
    includes(preferencesPlatformModule)

    singleOf(::DatabaseQueryProvider)

    factoryOf(::PrimitivesSerializer)
    factoryOf(::PreferencesDbDao) bind PreferencesDao::class

    single<Preferences> {
        DbPreferences(
            dao = get(),
            serializer = get(),
            dispatcher = Dispatchers.Default
        )
    }
}
