package kmp.template.feature.sample.di

import kmp.template.feature.sample.presentation.SampleViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sampleModule: Module = module {

    // ViewModels
    viewModelOf(::SampleViewModel)
}
