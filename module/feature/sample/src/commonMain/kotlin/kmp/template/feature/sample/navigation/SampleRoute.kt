package kmp.template.feature.sample.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
internal sealed interface SampleRoute : NavKey {

    @Serializable
    data object HomeDestination : SampleRoute

    @Serializable
    data object DesignDemoDestination : SampleRoute

    @Serializable
    data object EnvironmentDemoDestination : SampleRoute

    @Serializable
    data object StorageDemoDestination : SampleRoute

    @Serializable
    data object NetworkDemoDestination : SampleRoute

    @Serializable
    data object AboutDestination : SampleRoute
}
