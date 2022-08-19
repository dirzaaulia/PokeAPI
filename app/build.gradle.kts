plugins {
  id("com.android.application")
  kotlin("android")
  kotlin("kapt")
  id("kotlin-parcelize")
  id("dagger.hilt.android.plugin")
}

android {
  compileSdk = AppConfig.compileSdk

  defaultConfig {
    applicationId = "com.dirzaaulia.pokeapi"
    minSdk = AppConfig.minSdk
    targetSdk = AppConfig.targetSdk
    versionCode = AppConfig.versionCode
    versionName = AppConfig.versionName

    testInstrumentationRunner = AppConfig.testInstrumentationRunner
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
      )
    }
  }

  viewBinding {
    android.buildFeatures.viewBinding = true
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  kotlinOptions {
    jvmTarget = "11"
  }
}

dependencies {
  implementation(project(":data"))
  implementation(project(":domain"))

  //Implementation
  //AndroidX
  implementation(Dependencies.AndroidX.implementation)
  //AndroidX - Lifecycle
  implementation(Dependencies.AndroidX.Lifecycle.implementation)
  kapt(Dependencies.AndroidX.Lifecycle.kapt)

  //Glide
  implementation(Dependencies.Glide.implementation)
  kapt(Dependencies.Glide.kapt)

  //Hilt
  implementation(Dependencies.Hilt.implementation)
  kapt(Dependencies.Hilt.kapt)

  //Material
  implementation(Dependencies.Material.implementation)

  //Paging3
  implementation(Dependencies.Paging3.implementation)

  //Room
  implementation(Dependencies.Room.implementation)
  kapt(Dependencies.Room.kapt)

  //TestImplementation
  //JUnit
  testImplementation(Dependencies.JUnit.testImplementation)

  //AndroidTestImplementation
  androidTestImplementation(Dependencies.AndroidX.Test.androidTestImplementation)
}