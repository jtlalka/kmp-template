package kmp.template.network.di

import kmp.template.network.client.HttpClientCache
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule: Module = module {

    factory { (baseUrl: String) -> HttpClientCache.getOrCreate(baseUrl) }
}
