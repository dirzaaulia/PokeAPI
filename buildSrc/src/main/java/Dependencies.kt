import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {

  object AndroidX {
    private const val activityKtx = "androidx.activity:activity-ktx:${Version.activity}"
    private const val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
    private const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
    private const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"
    private const val fragmentKtx = "androidx.fragment:fragment-ktx:${Version.fragmentKtx}"
    private const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Version.swipeRefreshLayout}"

    val implementation = arrayListOf<String>().apply {
      add(activityKtx)
      add(appCompat)
      add(constraintLayout)
      add(coreKtx)
      add(fragmentKtx)
      add(swipeRefreshLayout)
    }

    object Lifecycle {
      private const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"
      private const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}"
      private const val compiler = "androidx.lifecycle:lifecycle-compiler:${Version.lifecycle}"

      val implementation = arrayListOf<String>().apply {
        add(runtime)
        add(viewModel)
      }

      val kapt = arrayListOf<String>().apply {
        add(compiler)
      }
    }

    object Test {
      private const val espressoCore = "androidx.test.espresso:espresso-core:${Version.espressoCore}"
      private const val extJUnit = "androidx.test.ext:junit:${Version.extJUnit}"

      val androidTestImplementation = arrayListOf<String>().apply {
        add(espressoCore)
        add(extJUnit)
      }
    }
  }

  object Chucker {
    private const val debug = "com.github.chuckerteam.chucker:library:${Version.chucker}"
    private const val release = "com.github.chuckerteam.chucker:library-no-op:${Version.chucker}"

    val debugImplementation = arrayListOf<String>().apply {
      add(debug)
    }

    val releaseImplementation = arrayListOf<String>().apply {
      add(release)
    }
  }

  object Coroutines {
    private const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"
    private const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"

    val implementation = arrayListOf<String>().apply {
      add(android)
      add(core)
    }
  }

  object Glide {
    private const val glide = "com.github.bumptech.glide:glide:${Version.glide}"
    private const val compiler = "com.github.bumptech.glide:compiler:${Version.glide}"

    val implementation = arrayListOf<String>().apply {
      add(glide)
    }

    val kapt = arrayListOf<String>().apply {
      add(compiler)
    }
  }

  object Hilt {
    private const val hiltAndroid = "com.google.dagger:hilt-android:${Version.hilt}"
    private const val hiltCompiler = "com.google.dagger:hilt-compiler:${Version.hilt}"

    val implementation = arrayListOf<String>().apply {
      add(hiltAndroid)
    }

    val kapt = arrayListOf<String>().apply {
      add(hiltCompiler)
    }
  }

  object JUnit {
    private const val jUnit = "junit:junit:${Version.jUnit}"

    val testImplementation = arrayListOf<String>().apply {
      add(jUnit)
    }
  }

  object Material {
    private const val material = "com.google.android.material:material:${Version.material}"

    val implementation = arrayListOf<String>().apply {
      add(material)
    }
  }

  object OkHttp {
    private const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Version.okhttp}"
    private const val okhttp = "com.squareup.okhttp3:okhttp:${Version.okhttp}"

    val implementation = arrayListOf<String>().apply {
      add(loggingInterceptor)
      add(okhttp)
    }
  }

  object Paging3 {
    private const val runtime = "androidx.paging:paging-runtime-ktx:${Version.paging3}"

    val implementation = arrayListOf<String>().apply {
      add(runtime)
    }
  }

  object Retrofit {
    private const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    private const val converterMoshi = "com.squareup.retrofit2:converter-moshi:${Version.retrofit}"
    private const val moshi = "com.squareup.moshi:moshi-kotlin:${Version.moshi}"

    val implementation = arrayListOf<String>().apply {
      add(retrofit)
      add(converterMoshi)
      add(moshi)
    }
  }

  object Room {
    private const val compiler = "androidx.room:room-compiler:${Version.room}"
    private const val ktx = "androidx.room:room-ktx:${Version.room}"
    private const val paging = "androidx.room:room-paging:${Version.room}"
    private const val runtime = "androidx.room:room-runtime:${Version.room}"

    val implementation = arrayListOf<String>().apply {
      add(ktx)
      add(paging)
      add(runtime)
    }

    val kapt = arrayListOf<String>().apply {
      add(compiler)
    }
  }
}

fun DependencyHandler.kapt(list: List<String>) {
  list.forEach { dependency ->
    add("kapt", dependency)
  }
}

fun DependencyHandler.implementation(list: List<String>) {
  list.forEach { dependency ->
    add("implementation", dependency)
  }
}

fun DependencyHandler.testImplementation(list: List<String>) {
  list.forEach { dependency ->
    add("testImplementation", dependency)
  }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
  list.forEach { dependency ->
    add("androidTestImplementation", dependency)
  }
}

fun DependencyHandler.debugImplementation(list: List<String>) {
  list.forEach { dependency ->
    add("debugImplementation", dependency)
  }
}

fun DependencyHandler.releaseImplementation(list: List<String>) {
  list.forEach { dependency ->
    add("releaseImplementation", dependency)
  }
}