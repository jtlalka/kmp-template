package kmp.template.preferences.exception

import kotlin.reflect.KClass

class UnsupportedTypeException(
    key: String,
    expected: KClass<*>,
    actual: KClass<*>,
) : IllegalArgumentException("Preference key '$key' expects $expected but was requested as $actual.")
