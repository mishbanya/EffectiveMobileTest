import com.android.build.api.variant.BuildConfigField

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.mishbanya.effectivemobiletest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mishbanya.effectivemobiletest"
        minSdk = 26
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
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}


androidComponents {
    onVariants {
        it.buildConfigFields.put(
            "GET_DATA_URL", BuildConfigField(
                "String", "\"${properties["GET_DATA_URL"]}\"", "GET_DATA_URL"
            )
        )
        it.buildConfigFields.put(
            "BASE_URL", BuildConfigField(
                "String", "\"${properties["BASE_URL"]}\"", "BASE_URL"
            )
        )
    }
}

dependencies {
    implementation(libs.maps.mobile)
    implementation(libs.automotivenavigation)
    implementation(libs.roadevents)
    kapt(libs.compiler)
    implementation(libs.glide)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.room.runtime)
    kapt(libs.room.compiler)
    implementation(libs.room.rxjava3)

    implementation(libs.viewbindingpropertydelegate.full)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.parent)
    implementation(libs.cicerone)
    implementation(libs.rxandroid)
    implementation(libs.adapter.rxjava3)
    implementation(libs.gson)
    implementation(libs.circle.view)

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // Activity ktx
    implementation(libs.activity.ktx)
    //Encryption
    implementation(libs.security.crypto)
}
kapt {
    correctErrorTypes = true
}