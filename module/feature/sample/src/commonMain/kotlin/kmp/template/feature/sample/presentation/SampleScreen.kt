package kmp.template.feature.sample.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kmp.template.design.annotation.ScreenPreview
import kmp.template.design.theme.AppTheme
import kmp.template.feature.sample.navigation.NavigationRoot
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SampleScreen() {

    val viewModel: SampleViewModel = koinViewModel()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    SampleScreen(
        viewState = viewState,
    )
}

@Composable
private fun SampleScreen(
    viewState: SampleViewState
) {
    NavigationRoot()
}

@ScreenPreview
@Composable
private fun ScreenPreview() = AppTheme {
    SampleScreen(
        viewState = SampleViewState()
    )
}
