plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
//    id("com.tencent.matrix-plugin")

}
//matrix {
//    trace {
//        setEnable(true)
//        baseMethodMapFile = "${project.buildDir}/matrix_output/Debug.methodmap"
//        blackListFile = "${project.projectDir}/matrixTrace/blackMethodList.txt"
//    }
//}
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
    //matrix
//    val MATRIX_VERSION = "2.1.0"
//    implementation(
//        group = "com.tencent.matrix",
//        name = "matrix-android-lib",
//        version = MATRIX_VERSION,
//    )
//
//    implementation(
//        group = "com.tencent.matrix",
//        name = "matrix-android-commons",
//        version = MATRIX_VERSION,
//    )
//    implementation(
//        group = "com.tencent.matrix",
//        name = "matrix-trace-canary",
//        version = MATRIX_VERSION,
//    )
//    implementation(
//        group = "com.tencent.matrix",
//        name = "matrix-resource-canary-android",
//        version = MATRIX_VERSION,
//    )
//    implementation(
//        group = "com.tencent.matrix",
//        name = "matrix-resource-canary-common",
//        version = MATRIX_VERSION,
//    )
//    implementation(
//        group = "com.tencent.matrix",
//        name = "matrix-io-canary",
//        version = MATRIX_VERSION,
//    )
//    implementation(
//        group = "com.tencent.matrix",
//        name = "matrix-sqlite-lint-android-sdk",
//        version = MATRIX_VERSION,
//    )
//    implementation(
//        group = "com.tencent.matrix",
//        name = "matrix-battery-canary",
//        version = MATRIX_VERSION,
//    )
//    implementation(
//        group = "com.tencent.matrix",
//        name = "matrix-hooks",
//        version = MATRIX_VERSION,
//    )

    //multiplestatusview
    implementation(project(":lib_multiplestatusview"))
    //二级联动列表
    implementation("com.kunminx.linkage:linkage-recyclerview:2.7.0")
    //smartLayout
    implementation("androidx.appcompat:appcompat:1.0.0")
    implementation("io.github.scwang90:refresh-layout-kernel:2.1.0")    //核心必须依赖
    implementation("io.github.scwang90:refresh-header-classics:2.1.0")    //经典刷新头
    implementation("io.github.scwang90:refresh-header-radar:2.1.0")       //雷达刷新头
    implementation("io.github.scwang90:refresh-header-falsify:2.1.0")     //虚拟刷新头
    implementation("io.github.scwang90:refresh-header-material:2.1.0")    //谷歌刷新头
    implementation("io.github.scwang90:refresh-header-two-level:2.1.0")   //二级刷新头
    implementation("io.github.scwang90:refresh-footer-ball:2.1.0")        //球脉冲加载
    implementation("io.github.scwang90:refresh-footer-classics:2.1.0")    //经典加载
    //toaster
    implementation("com.github.getActivity:Toaster:12.6")
    //android-support-skin
//    implementation("skin.support:skin-support:4.0.5")    // skin-support
//    implementation("skin.support:skin-support-appcompat:4.0.5")     // skin-support 基础控件支持
//    implementation("skin.support:skin-support-design:4.0.5")            // skin-support-design material design 控件支持[可选]
//    implementation("skin.support:skin-support-cardview:4.0.5")          // skin-support-cardview CardView 控件支持[可选]
//    implementation("skin.support:skin-support-constraint-layout:4.0.5") // skin-support-constraint-layout ConstraintLayout 控件支持[可选]
//    implementation("skin.support:skin-support-appcompat:4.0.5")     // skin-support 基础控件支持
    //okhttp
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    //recyclerviewAnimation
    implementation("jp.wasabeef:recyclerview-animators:4.0.2")
    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.11.0")
    //rxjava
    implementation("io.reactivex.rxjava3:rxjava:3.1.8")
    //RxJava依赖
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
    //easyPermission
    implementation("pub.devrel:easypermissions:3.0.0")
    //leakCanary
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.14")

    implementation("com.google.android.material:material:1.12.0")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}