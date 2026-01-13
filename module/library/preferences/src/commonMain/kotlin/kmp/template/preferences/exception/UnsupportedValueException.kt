package kmp.template.preferences.exception

class UnsupportedValueException(value: Any): IllegalArgumentException("Unsupported value type: ${value::class}")
