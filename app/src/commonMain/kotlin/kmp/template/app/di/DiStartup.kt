package kmp.template.app.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

internal fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)

        modules(appModule)
        modules(featureModules)
        modules(libraryModules)
        modules(serviceModules)
    }
}
