import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

/**
 *
 * @author Arthur Kazemi<bidadh@gmail.com>
 * @since 15/10/2023 23:50
 */
fun PluginDependenciesSpec.appPropertiesPlugin(): PluginDependencySpec =
  id("app-properties-conventions")
