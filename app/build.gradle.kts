plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "id.our.pintarplus"
    compileSdk = 34

    defaultConfig {
        applicationId = "id.our.pintarplus"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Gson converter for Retrofit
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // OkHttp (optional, for logging requests/responses)
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    implementation ("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.1")

}