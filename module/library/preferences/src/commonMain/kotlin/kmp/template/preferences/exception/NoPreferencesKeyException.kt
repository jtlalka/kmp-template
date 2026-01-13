package kmp.template.preferences.exception

class NoPreferencesKeyException(key: String): NoSuchElementException("Preferences key: '$key' not found.")
