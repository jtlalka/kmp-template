package kmp.template.app.di

import kmp.template.feature.sample.di.sampleModule
import kmp.template.network.di.networkModule
import kmp.template.preferences.di.preferencesModule
import org.koin.core.module.Module

internal expect val appModule: Module

internal val featureModules: List<Module> = listOf(
    sampleModule
)

internal val libraryModules: List<Module> = listOf(
    networkModule,
    preferencesModule
)

internal val serviceModules: List<Module> = listOf(
)
