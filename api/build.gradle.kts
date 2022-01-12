plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
}

android {
    compileSdk = 30
}

dependencies {
    api("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
}
