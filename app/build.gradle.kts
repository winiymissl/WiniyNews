plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.winiynews"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.winiynews"
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
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    viewBinding {
        enable = true
    }
}
dependencies {
    //toaster
    implementation("com.github.getActivity:Toaster:12.6")

    //android-support-skin
    implementation("skin.support:skin-support:4.0.5")    // skin-support
    implementation("skin.support:skin-support-appcompat:4.0.5")     // skin-support 基础控件支持
    implementation("skin.support:skin-support-design:4.0.5")            // skin-support-design material design 控件支持[可选]
    implementation("skin.support:skin-support-cardview:4.0.5")          // skin-support-cardview CardView 控件支持[可选]
    implementation("skin.support:skin-support-constraint-layout:4.0.5") // skin-support-constraint-layout ConstraintLayout 控件支持[可选]
    implementation("skin.support:skin-support-appcompat:4.0.5")     // skin-support 基础控件支持
    //okhttp
    implementation("com.squareup.okhttp3:okhttp:4.9.1")
    //recyclerviewAnimation
    implementation("jp.wasabeef:recyclerview-animators:4.0.2")
    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.11.0")
    //rxjava
    implementation("io.reactivex.rxjava3:rxjava:3.1.8")
    //RxJava的依赖包
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")
    //Logger
    implementation("com.orhanobut:logger:2.2.0")
    //Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("jp.wasabeef:glide-transformations:4.3.0")
    //Banner
    implementation("io.github.youth5201314:banner:2.2.3")
    //navigation
    val nav_version = "2.7.7"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
    //multiple-status-view
    implementation("com.classic.common:multiple-status-view:1.7")
    //easyPermission
    implementation("pub.devrel:easypermissions:3.0.0")
    //leakCanary
    //debugImplementation("com.squareup.leakcanary:leakcanary-android:2.14")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}