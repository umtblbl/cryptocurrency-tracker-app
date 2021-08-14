package scripts


/**
 * Precompiled [variants.gradle.kts][scripts.Variants_gradle] script plugin.
 *
 * @see scripts.Variants_gradle
 */
class VariantsPlugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("scripts.Variants_gradle")
                .getDeclaredConstructor(org.gradle.api.Project::class.java, org.gradle.api.Project::class.java)
                .newInstance(target, target)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }
}
