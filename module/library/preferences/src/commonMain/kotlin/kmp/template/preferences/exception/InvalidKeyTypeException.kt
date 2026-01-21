package kmp.template.preferences.exception

import kmp.template.preferences.model.Key
import kmp.template.preferences.model.KeyType

class InvalidKeyTypeException(
    key: Key<*>,
    expected: KeyType
) : IllegalArgumentException("Preference key '${key.name}' has type ${key.type} but expected $expected.")
