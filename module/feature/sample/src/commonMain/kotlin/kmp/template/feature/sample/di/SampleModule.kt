package kmp.template.feature.sample.di

import kmp.template.feature.sample.presentation.SampleViewModel
import kmp.template.feature.sample.presentation.about.SampleAboutViewModel
import kmp.template.feature.sample.presentation.design.SampleDesignViewModel
import kmp.template.feature.sample.presentation.environment.SampleEnvironmentViewModel
import kmp.template.feature.sample.presentation.home.SampleHomeViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sampleModule: Module = module {

    // ViewModels
    viewModelOf(::SampleAboutViewModel)
    viewModelOf(::SampleDesignViewModel)
    viewModelOf(::SampleEnvironmentViewModel)
    viewModelOf(::SampleHomeViewModel)
    viewModelOf(::SampleViewModel)
}
