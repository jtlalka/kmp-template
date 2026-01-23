package kmp.template.preferences.di

import kmp.template.preferences.Preferences
import kmp.template.preferences.internal.PreferencesEditor
import kmp.template.preferences.internal.db.DatabaseQueryProvider
import kmp.template.preferences.internal.db.dao.PreferencesDao
import kmp.template.preferences.internal.db.dao.PreferencesDbDao
import kmp.template.preferences.internal.observer.DataFlowObserver
import kmp.template.preferences.internal.observer.DataObserver
import kmp.template.preferences.internal.serializer.DataSerializer
import kmp.template.preferences.internal.timer.LocalSystemTimer
import kmp.template.preferences.internal.timer.LocalTimer
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
    singleOf(::DataSerializer)

    factoryOf(::PreferencesDbDao) bind PreferencesDao::class
    factoryOf(::DataFlowObserver) bind DataObserver::class
    factoryOf(::LocalSystemTimer) bind LocalTimer::class

    single<Preferences> {
        PreferencesEditor(
            dao = get(),
            dataSerializer = get(),
            dataObserver = get(),
            localTimer = get(),
            dispatcher = Dispatchers.Default
        )
    }
}
