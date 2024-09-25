import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)

    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.gradleBuildConfig)

//    Firebase
//    alias(libs.plugins.google.services)
//    alias(libs.plugins.crashlytics)

    alias(libs.plugins.skie)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = false
//            linkerOpts("-F<path_to_frameworks>")

        }
    }

    sourceSets {

        androidMain.dependencies {
//            implementation(compose.preview)

//            Para hacer la vita en el módulo android
//            implementation(compose.runtime)
//            implementation(compose.foundation)
//            implementation(compose.material3)
//            implementation(compose.ui)

            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.koin.android)

//            implementation(project.dependencies.platform(libs.firebase.android.bom))
//    implementation("com.google.firebase:firebase-analytics")
//            implementation(libs.firebase.android.analytics)
//            implementation(libs.firebase.android.crashlytics.ktx)
//            implementation(libs.firebase.android.auth)
//            implementation(libs.firebase.firestore)
//            implementation(libs.google.gson)
//
//            implementation(libs.mlkit.textrecognition)
//            implementation(libs.mlkit.barcoderecognition)
//            implementation(libs.androidx.camera.core)
//            implementation(libs.androidx.camera.lifecycle)
//            implementation(libs.androidx.camera.view)
//            implementation(libs.androidx.camera.camera2)

        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)

            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.contentnegotiation)
            implementation(libs.ktor.serialization.json)
            implementation(libs.ktor.auth)
            implementation(libs.androidx.navigation.compose)

            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.coroutines.core)

            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            implementation(libs.moko.permissions.compose)

            implementation(libs.kotlinx.datetime)



        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)

//            implementation("co.touchlab:kotlinx-cinterop")
        }
    }

    task("testClasses")
}

android {
    namespace = "org.example.project"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "org.example.project"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0.1-beta"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}

buildConfig {
    packageName("org.example.project")
//    val properties = Properties()
//    properties.load(project.rootProject.file("local.properties").reader())
//    val apiKey = properties.getProperty("API_KEY")

}


skie {
    features {
        enableSwiftUIObservingPreview = true
        enableFlowCombineConvertorPreview = true
        enableFutureCombineExtensionPreview = true
    }
}
