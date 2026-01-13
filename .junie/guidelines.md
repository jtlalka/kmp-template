# Project Guidelines — KMP Template

This document summarizes the conventions, architecture, organization, and testing practices used across the codebase. It is derived from the current source (examples referenced inline).

## 1) Coding conventions

- Language and targets
  - Kotlin Multiplatform with common, Android, iOS, JVM and Wasm targets (see root `build.gradle.kts` with Compose Multiplatform, SQLDelight, Koin, and related plugins).
  - Prefer `expect/actual` for platform integration boundaries; keep common logic in `commonMain`. Example: `preferencesPlatformModule` is `expect` in common and `actual` in `androidMain` providing the SQLDelight driver factory (`module/library/preferences/src/androidMain/.../PreferencesModule.android.kt`).

- Visibility and API surface
  - Default to `internal` for implementation details within a module. Public API surfaces are intentionally small (e.g., `kmp.template.preferences.Preferences` is public, while `DbPreferences` and DAO types live under `.internal`).
  - Keep package visibility coherent with module boundaries, e.g., `kmp.template.preferences.internal.*` for non-API implementation.

- Naming
  - Classes and interfaces: PascalCase.
  - Functions and properties: camelCase.
  - Test classes: `<TypeUnderTest>Test` (see multiple tests in `module/library/preferences/src/commonTest/...`). Behavior-style function names are allowed with backticks.
  - Use meaningful domain terms for navigation keys and events (`NavigatorEvent.NavigateTo`, `ReplaceTo`, `NavigateUp`).

- Coroutines
  - Use structured concurrency via scopes provided by framework types. For ViewModels, use `viewModelScope` (see `MviViewModel`).
  - Offload IO/CPU work via explicit dispatchers and `withContext` (see `DbPreferences` using `Dispatchers.Default` for DAO access).
  - Configuration for coroutine context is injected/configurable for MVI (`MviConfig`/`MviDefaultConfig`).

- Error handling
  - Prefer typed exceptions for domain-specific errors (e.g., `PreferencesKeyNotFound`).
  - Let type mismatches surface at call site when appropriate (serializer decode generics intentionally allow `ClassCastException` if misused — covered by tests).

- Immutability and state
  - UI state managed via `StateFlow` and updated with `.update { }`. One-off effects via `Channel` exposed as `Flow` (see `MviViewModel`).
  - Use data classes and sealed interfaces for events/commands (`NavigatorEvent`).

- Serialization and type safety
  - Minimal, explicit serializers for primitives (`PrimitivesSerializer`) with strict parsing (e.g., booleans via `toBooleanStrict`).

- Compose and Stability
  - Use Compose Multiplatform. Keep navigation facade `@Stable` where it’s observed by UI (`Navigator`).

- Static analysis and style
  - Detekt codestyle plugin is applied at the root. Follow its enforced style guidelines (imports order, naming, formatting). Keep code idiomatic and consistent with the existing style.

## 2) Architecture patterns and best practices

- Layering and modules
  - Clear separation between app, features, and libraries:
    - Libraries: reusable building blocks (e.g., `foundation` for MVI, `navigation` for navigation facade, `preferences` for storage).
    - Feature modules: UI + ViewModels built on top of library layers (e.g., `module/feature/sample`).
    - App modules (Android/iOS) assemble and wire dependencies.

- Dependency Injection
  - Koin is used for DI with module-per-area organization. Example: `preferencesModule` includes platform bindings and provides DAO/Serializer, and exposes `Preferences` implementation (`DbPreferences`). ViewModels are registered via `viewModelOf` in feature modules (see `SampleModule`).
  - Platform-specific services are bound via `expect/actual` Koin modules for each target.

- MVI (Model–View–Intent)
  - Base class `MviViewModel<ViewState>` provides:
    - `StateFlow<ViewState>` for state.
    - `Flow<Any>` for side effects via a `Channel`.
    - `launch {}` helper that uses configured dispatcher and exception handler (`MviConfig`).
    - `transform {}` to apply pure state reducers.
  - Best practices:
    - Keep intents small and deterministic; run business logic inside `launch`.
    - Emit side effects for one-off events; keep state serializable/plain.

- Navigation
  - Cross-platform navigation facade around `androidx.navigation3.runtime` types.
  - `Navigator` exposes a `NavBackStack<NavKey>` and a single entry point `navigate(event: NavigatorEvent)`.
  - `NavigatorController` centralizes back-stack manipulations, including single-instance routing and back-stack clearing policies.
  - Prefer sealed `NavigatorEvent` commands over ad-hoc navigation calls, enabling testability (`NavigatorControllerTest`, `NavigatorTabControllerTest`).

- Data and storage
  - SQLDelight-backed persistence hidden behind simple interfaces/DAO. `DbPreferences` depends on a `PreferencesDao` and a serializer, and runs work on a background dispatcher with `withContext`.
  - Use small, focused models for serialization (`SerializedModel`, `SerializedType`).

- Cross-platform boundaries
  - Use `expect/actual` for drivers and platform services. Keep interfaces in common, implementations per target (e.g., Android provides `AndroidDatabaseDriverFactory`).

## 3) Code organization and package structure

- Source sets per module
  - `src/commonMain/kotlin` for shared code; `src/androidMain`, `src/iosMain`, `src/wasmJsMain` for platform specifics.
  - `src/commonTest/kotlin` for shared tests that do not require platform drivers.

- Packages
  - Root package: `kmp.template.*`.
  - Library modules
    - Foundation: `kmp.template.foundation.*` (e.g., `mvi`, `config`).
    - Navigation: `kmp.template.navigation.*` (`Navigator`, `NavigatorEvent`, internal controller and compose helpers).
    - Preferences: `kmp.template.preferences.*` with `internal` subpackages: `db`, `serializer`, `di`.
  - Feature modules
    - Example feature: `kmp.template.feature.sample.*` organized by presentation (ViewModels) and DI (`SampleModule`).

- Internal vs public
  - Public APIs live at the module root (e.g., `Preferences` interface). Implementation details live under `.internal` and are not exported.

- Build and plugins
  - Root `build.gradle.kts` declares plugin aliases: Android, KMP, Compose Multiplatform, SQLDelight, Koin, Detekt, AtomicFu, Serialization, and Mokkery. Concrete configurations are per-module.

## 4) Unit and integration testing approaches

- Frameworks and assertions
  - Use `kotlin.test` assertions (`assertEquals`, `assertTrue`, `assertFailsWith`, etc.).
  - Use `kotlinx.coroutines.test.runTest {}` for coroutine tests. Do not mark test functions as `suspend`.

- Structure and naming
  - Test class names mirror the type under test with `Test` suffix.
  - Arrange/Act/Assert (AAA) layout; keep tests single-purpose and deterministic.

- Test doubles and DI
  - Prefer simple in-memory fakes in `commonTest` to avoid platform drivers (e.g., fake `PreferencesDataSource`/DAO for `DbPreferences`).
  - Construct SUTs directly in tests. Only wire DI when the test targets DI.

- Concurrency and dispatchers
  - Use test dispatchers (`StandardTestDispatcher`) when verifying ordering/scheduling.
  - For simple cases, `Dispatchers.Unconfined` may be used within tests per guidelines.

- Storage and serialization tests
  - Cover round-trips for all supported primitives in `PrimitivesSerializer`.
  - Validate strict boolean parsing (`toBooleanStrict`) raising `IllegalArgumentException` for invalid inputs.
  - Validate preferences behaviors: `getOrThrow`, `getOrDefault`, `hasKey`, `remove`, `clear`, overwrite semantics, and type safety expectations.

- Navigation tests
  - Verify back-stack behavior using `NavigatorControllerTest` and tab controller tests under `module/library/navigation/src/commonTest`.

- Platform scope
  - Keep tests in `commonTest` free of platform driver dependencies to run on all targets (including Wasm). Avoid direct SQL/web drivers in unit tests.

- Tools
  - Mokkery plugin is available if mocking is needed; prefer fakes where simpler and clearer.

## References (non-exhaustive)

- Foundation MVI
  - `module/library/foundation/src/commonMain/kotlin/kmp/template/foundation/mvi/MviViewModel.kt`
  - `module/library/foundation/src/commonMain/kotlin/kmp/template/foundation/mvi/config/MviConfig.kt`

- Navigation
  - `module/library/navigation/src/commonMain/kotlin/kmp/template/navigation/Navigator.kt`
  - `module/library/navigation/src/commonMain/kotlin/kmp/template/navigation/NavigatorEvent.kt`
  - `module/library/navigation/src/commonMain/kotlin/kmp/template/navigation/internal/NavigatorController.kt`
  - Tests under `module/library/navigation/src/commonTest/...`

- Preferences
  - `module/library/preferences/src/commonMain/kotlin/kmp/template/preferences/internal/DbPreferences.kt`
  - `module/library/preferences/src/commonMain/kotlin/kmp/template/preferences/di/PreferencesModule.kt`
  - `module/library/preferences/src/androidMain/kotlin/kmp/template/preferences/di/PreferencesModule.android.kt`
  - Tests under `module/library/preferences/src/commonTest/...`

- Sample feature
  - `module/feature/sample/src/commonMain/kotlin/kmp/template/feature/sample/di/SampleModule.kt`

- Build
  - Root `build.gradle.kts` for plugin landscape
