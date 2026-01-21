package kmp.template.preferences.exception

import kmp.template.preferences.model.Key

class NoPreferencesKeyException(key: Key<*>) : NoSuchElementException("Preferences key: '${key.name}' not found.")
