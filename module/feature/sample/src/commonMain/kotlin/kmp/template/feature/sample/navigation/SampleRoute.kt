package kmp.template.feature.sample.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
internal sealed interface SampleRoute : NavKey {

    @Serializable
    data object HomeDestination : SampleRoute

    @Serializable
    data object SampleDesignDestination : SampleRoute

    @Serializable
    data object SampleEnvironmentDestination : SampleRoute

    @Serializable
    data object StorageDemoDestination : SampleRoute

    @Serializable
    data object AboutDestination : SampleRoute
}
