plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
}

android {
    compileSdk = 30

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.31")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.recyclerview:recyclerview:1.2.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.1")

    implementation(project(":api"))

    implementation("androidx.constraintlayout:constraintlayout:2.1.2")

    implementation("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")
}
