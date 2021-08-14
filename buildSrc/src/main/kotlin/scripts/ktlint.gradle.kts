package scripts

apply(plugin = "lifecycle-base")

val ktLint: Configuration by project.configurations.creating

dependencies {
    ktLint(Libs.ktLint)
}

tasks {
    val outputDir = "${project.buildDir}/reports/ktlint/"
    val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "**/*.kt"))

    val ktLintCheck by tasks.creating(JavaExec::class) {
        inputs.files(inputFiles)
        outputs.dir(outputDir)

        description = "Check Kotlin code style."
        group = "ktlint"
        classpath = ktLint
        main = "com.pinterest.ktlint.Main"
        args = listOf("src/**/*.kt")
    }

    val ktLintFormat by tasks.creating(JavaExec::class) {
        inputs.files(inputFiles)
        outputs.dir(outputDir)

        description = "Fix Kotlin code style deviations."
        group = "ktlint"
        classpath = ktLint
        main = "com.pinterest.ktlint.Main"
        args = listOf("-F", "src/**/*.kt")
    }

    val preBuild by existing {
        dependsOn(ktLintCheck)
    }
}