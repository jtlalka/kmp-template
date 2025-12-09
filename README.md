# KMP Project Template

Kotlin multimodule gradle project template for KMP project.

## Supported targets
- Android 🤖
- iOS 🍎
- Desktop 🖥️
- WASM 🌐

## Project rename wizard

This template includes a shell script that safely renames the project's base package across source directories and configuration files.

### What it does
- Renames Kotlin package directories under `src/*/kotlin` (depth-first) and cleans up empty old directories.
- Updates `package` and `import` lines in Kotlin sources, handling exact matches and prefixes.
- Adjusts Gradle `*.kts` files by replacing all occurrences of the old package name.

### Usage
```sh
./template.wizard.sh old.package.name new.package
```

Notes:
- After running, sync Gradle and rebuild. For iOS, re-open the Xcode project if needed.
