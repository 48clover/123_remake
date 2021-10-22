// プラグインの追加（参照などのサポート）
plugins {
    kotlin("jvm") version "1.4.32"
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = "site.saba123"
version = "1.0-SNAPSHOT"

// 依存関係の追加
repositories {
    jcenter()
    maven {
        url = uri("https://repo.nukkitx.com/main/")
    }
}

// プロジェクトコンパイル時の設定
dependencies {
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