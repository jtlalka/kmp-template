package kmp.template.preferences.internal.timer

import kotlin.time.Clock
import kotlin.time.ExperimentalTime

internal class LocalSystemTimer : LocalTimer {

    @OptIn(ExperimentalTime::class)
    override fun getCurrentTime(): Long = Clock.System.now().epochSeconds
}
