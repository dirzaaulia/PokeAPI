buildscript {
  repositories {
    google()
    mavenCentral()
  }

  dependencies {
    classpath("com.android.tools.build:gradle:${Version.toolsBuildGradle}")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlinGradle}")
    classpath("com.google.dagger:hilt-android-gradle-plugin:${Version.hilt}")
  }
}

subprojects {
  repositories {
    google()
    mavenCentral()
  }
}

tasks.register("clean", Delete::class) {
  delete(rootProject.buildDir)
}