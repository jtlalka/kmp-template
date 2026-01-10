import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.gradleBuildLogic)
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.module.feature.sample)
            implementation(projects.module.library.design)
            implementation(projects.module.library.environment)
            implementation(projects.module.library.foundation)
            implementation(projects.module.library.network)

            implementation(libs.bundles.compose)
            implementation(libs.koin.compose)
        }
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}

android {
    namespace = "kmp.template.app"

    defaultConfig {
        applicationId = "kmp.template.app"
        versionCode = 1
        versionName = "1.0.0"
    }
    signingConfigs {
        // IMPORTANT! Template certificate should be replaced by secure product certificate.
        create("build") {
            storeFile = file("certs/template.jks")
            storePassword = "kmp#template"
            keyAlias = "build"
            keyPassword = "kmp#template"
        }
    }
    buildTypes {
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("build")
        }
        release {
            isDebuggable = false
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("build")
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "META-INF/AL2.0"
            excludes += "META-INF/LGPL2.1"
        }
    }
    lint {
        abortOnError = true
        checkAllWarnings = true
        checkDependencies = true
        checkReleaseBuilds = false
        ignoreTestSources = true
        warningsAsErrors = false
        disable += listOf(
            "InvalidPackage"
        )
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

compose.desktop {
    application {
        mainClass = "kmp.template.app.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "kmp.template.app"
            packageVersion = "1.0.0"
        }
    }
}
