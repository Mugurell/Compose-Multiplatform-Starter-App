import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.compose)
    alias(libs.plugins.mockoResources)
    `maven-publish`
}

group = "m.m.m"
version = "1.0.0"

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }

        publishLibraryVariants("release")
    }

    val xcf = XCFramework()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "Shared"
            isStatic = true
            xcf.add(this)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)

                api(libs.moko.resources)
                api(libs.moko.resources.composes)

                //put your multiplatform dependencies here
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}

android {
    namespace = "com.example.mmm"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "com.example.mmm" // required
}

afterEvaluate {
    tasks.findByName("iosX64SourcesJar")?.dependsOn(tasks.findByName("generateMRiosX64Main"))
    tasks.findByName("iosArm64SourcesJar")?.dependsOn(tasks.findByName("generateMRiosArm64Main"))
    tasks.findByName("iosSimulatorArm64SourcesJar")?.dependsOn(tasks.findByName("generateMRiosSimulatorArm64Main"))
    tasks.findByName("androidDebugSourcesJar")?.dependsOn(tasks.findByName("generateMRandroidMain"))
    tasks.findByName("androidReleaseSourcesJar")?.dependsOn(tasks.findByName("generateMRandroidMain"))
    tasks.findByName("sourcesJar")?.dependsOn(tasks.findByName("generateMRcommonMain"))
}
