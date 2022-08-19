plugins {
  id("com.android.library")
  kotlin("android")
  kotlin("kapt")
  id("kotlin-parcelize")
  id("dagger.hilt.android.plugin")
}

android {

  compileSdk = AppConfig.compileSdk

  defaultConfig {
    minSdk = AppConfig.minSdk
    targetSdk = AppConfig.targetSdk

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

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  kotlinOptions {
    jvmTarget = "11"
  }
}

dependencies {
  //Implementation
  //Coroutines
  implementation(Dependencies.Coroutines.implementation)

  //Hilt
  implementation(Dependencies.Hilt.implementation)
  kapt(Dependencies.Hilt.kapt)

  //Paging3
  implementation(Dependencies.Paging3.implementation)

  //Room
  implementation(Dependencies.Room.implementation)
  kapt(Dependencies.Room.kapt)

  //Test Implementation
  //JUnit
  testImplementation(Dependencies.JUnit.testImplementation)

  //Android Test Implementation
  androidTestImplementation(Dependencies.AndroidX.Test.androidTestImplementation)
}