## Design module

Basic design system for application provides UI theme and components.

### Colors Pallet

Colors pallet is presented in M3 convention in presented contrast, eg:

```kotlin
    val neutral100 = Color(0xFFFFFFFF)
    val neutral99  = Color(0xFFF5F5F7)
    val neutral95  = Color(0xFFE6E6EB)
    val neutral90  = Color(0xFFD1D1D8)
    val neutral80  = Color(0xFFB8B8C0)
    val neutral70  = Color(0xFF9E9EAA)
    val neutral60  = Color(0xFF858594)
    val neutral50  = Color(0xFF6C6C7D)
    val neutral40  = Color(0xFF545466)
    val neutral30  = Color(0xFF3C3C4F)
    val neutral20  = Color(0xFF252538)
    val neutral10  = Color(0xFF121212)
    val neutral0   = Color(0xFF000000)
```

### Colors Shema

Colors shema use specific color contrast from colors palette.

| Role               | Light (bg/on) | Dark (bg/on) |
|:-------------------|:-------------:|:------------:|
| primary            |   40 / 100    |   80 / 20    |
| primaryContainer   |    90 / 10    |   30 / 90    |
| secondary          |   40 / 100    |   80 / 20    |
| secondaryContainer |    90 / 10    |   30 / 90    |
| tertiary           |   40 / 100    |   80 / 20    |
| tertiaryContainer  |    90 / 10    |   30 / 90    |
| background         |    99 / 10    |   10 / 90    |
| surface            |   95* / 10    |   20* / 90   |
| surfaceVariant     |    90 / 30    |   30 / 70    |
| error              |   40 / 100    |   80 / 20    |
| errorContainer     |    90 / 10    |   30 / 90    |

(*) surface used dedicated background contrast instead of alpha/elevation.
