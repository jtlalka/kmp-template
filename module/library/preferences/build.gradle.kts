plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.gradleBuildLogic)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kermit)
            implementation(libs.koin.core)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.datetime)
            implementation(libs.sqldelight.runtime)
        }
        commonTest.dependencies {
            implementation(libs.bundles.unitTest)
        }
        androidMain.dependencies {
            implementation(libs.sqldelight.android.driver)
        }
        iosMain.dependencies {
            implementation(libs.sqldelight.native.driver)
        }
        jvmMain.dependencies {
            implementation(libs.sqldelight.sqlite.driver)
        }
        wasmJsMain.dependencies {
            implementation(libs.kotlinx.browser)
            implementation(libs.sqldelight.web.driver)
        }
    }
}

android {
    namespace = "kmp.template.preferences"
}

sqldelight {
    databases {
        create("PreferencesDb") {
            packageName.set("kmp.template.preferences.db")
            generateAsync.set(true)
        }
    }
}
