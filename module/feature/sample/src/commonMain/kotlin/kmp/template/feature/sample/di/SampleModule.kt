package kmp.template.feature.sample.di

import kmp.template.feature.sample.presentation.SampleViewModel
import kmp.template.feature.sample.presentation.about.SampleAboutViewModel
import kmp.template.feature.sample.presentation.design.DesignDemoViewModel
import kmp.template.feature.sample.presentation.environment.EnvironmentDemoViewModel
import kmp.template.feature.sample.presentation.home.SampleHomeViewModel
import kmp.template.feature.sample.presentation.network.NetworkDemoViewModel
import kmp.template.feature.sample.presentation.network.repository.ArtworksRepository
import kmp.template.feature.sample.presentation.network.repository.ArtworksRestClient
import kmp.template.feature.sample.presentation.network.repository.ArtworksRestClient.Companion.API_BASE_URL
import kmp.template.feature.sample.presentation.storage.StorageDemoViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val sampleModule: Module = module {

    // ViewModels
    viewModelOf(::DesignDemoViewModel)
    viewModelOf(::EnvironmentDemoViewModel)
    viewModelOf(::NetworkDemoViewModel)
    viewModelOf(::SampleAboutViewModel)
    viewModelOf(::SampleHomeViewModel)
    viewModelOf(::SampleViewModel)
    viewModelOf(::StorageDemoViewModel)

    // Network
    single { ArtworksRestClient(httpClient = get { parametersOf(API_BASE_URL) }) }
    factoryOf(::ArtworksRepository)
}
