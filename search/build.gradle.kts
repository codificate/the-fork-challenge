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
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.recyclerview:recyclerview:1.2.1")

    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.2")

    testImplementation("androidx.test.ext:junit:1.1.3")
    testImplementation("com.nhaarman:mockito-kotlin:1.5.0")
    testImplementation("org.mockito:mockito-core:3.3.3")
    testImplementation("org.mockito:mockito-inline:2.25.0")
    testImplementation("androidx.test.espresso:espresso-core:3.4.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.1")

    implementation(project(":api"))
    implementation(project(":user"))

    testImplementation("junit:junit:4.13.2")
}
