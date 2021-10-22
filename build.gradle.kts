// プラグインの追加（参照などのサポート）
plugins {
    kotlin("jvm") version "1.5.31"
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = "site.saba123"
version = "1.0-SNAPSHOT"

// 依存関係の追加
repositories {
    jcenter()
    mavenCentral()
    maven {
        url = uri("https://repo.nukkitx.com/main/")
    }
}

// プロジェクトコンパイル時の設定
val exposedVersion: String by project
dependencies {
    // exposed
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jodatime:$exposedVersion")
    implementation("org.xerial:sqlite-jdbc:3.30.1")
    // nukkit
    implementation(kotlin("stdlib"))
    compileOnly("cn.nukkit:nukkit:1.0-SNAPSHOT")
    testCompileOnly("cn.nukkit:nukkit:1.0-SNAPSHOT")
}

// タスクの定義
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}