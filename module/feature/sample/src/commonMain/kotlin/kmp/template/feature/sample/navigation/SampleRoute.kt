package kmp.template.feature.sample.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
internal sealed interface SampleRoute : NavKey {

    @Serializable
    data object SampleLaunch : SampleRoute, NavKey

    @Serializable
    data class SampleDesignSystem(val todo: String) : SampleRoute, NavKey

    @Serializable
    data object SampleEnvironment : SampleRoute, NavKey
}
