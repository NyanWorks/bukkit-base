plugins {
    java
    kotlin("jvm") version "1.3.61"
}

repositories {
    mavenCentral()
}

dependencies {
    maven { url = URI("https://hub.spigotmc.org/nexus/content/repositories/snapshots")}
    maven { url = URI("https://oss.sonatype.org/content/repositories/snapshots")}
}

tasks.processResources {
    filteringCharset = "UTF-8"
    from(sourceSets.main.get().resources.srcDirs) {
        // なんでも取り込む
        include("**")

        val tokenReplacementMap = mapOf(
                // @キー@がバリューに置き換わるMap
        )

        filter<ReplaceTokens>("tokens" to tokenReplacementMap)
    }
    // プロジェクトのルートからLICENSEというファイルを取り込む
    from(projectDir) { include("LICENSE") }
}


tasks.withType(JavaCompile::class.java).all {
    // OS標準の、特にWindowsではWindows-31J = CP932 = SJISがデフォルトで
    // 使用されてしまうので負けないように指定しておく
    this.options.encoding = "UTF-8"
}
