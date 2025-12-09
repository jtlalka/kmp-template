package kmp.template.app.di

import org.koin.core.module.Module

internal expect val appModule: Module

internal val featureModules: List<Module> = listOf(
)

internal val libraryModules: List<Module> = listOf(
)

internal val serviceModules: List<Module> = listOf(
)
