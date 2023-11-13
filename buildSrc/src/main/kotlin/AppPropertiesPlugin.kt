import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra
import java.io.File
import java.util.Properties

data class AppVersion(
  val version: Int,
  val name: String
)
data class AppProperties(
  val `package`: String,
  val name: String,
  val dbName: String,
  val version: AppVersion,
  val resourcesSharedPackage: String,
  val dbPackage: String = "$`package`.database",
  val applicationId: String = "$`package`.$name"
)

class AppPropertiesPlugin: Plugin<Project> {
  lateinit var appProps: AppProperties

  override fun apply(target: Project) {
    val file = appPropertiesFile()
    file.reader().use {
      val props = Properties()
      props.load(it)
      props.stringPropertyNames().forEach { prop ->
        target.extra.set(prop, props.getProperty(prop))
      }
      val value = AppProperties(
        `package` = props.getProperty("package"),
        name = props.getProperty("name"),
        dbName = props.getProperty("db.name"),
        version = AppVersion(
          version = props.getProperty("version").toInt(),
          name = props.getProperty("version.name")
        ),
        resourcesSharedPackage = props.getProperty("resources.shared.package")
      )
      appProps = value
    }
  }

  private fun appPropertiesFile(): File {
    val path = "composeApp/app.properties"
    val file = File(path)
    return if (file.exists()) {
      file
    } else {
      File("../$path") // this is to support calling gradle script from iosApp scripts
    }
  }
}

val Project.appProperties: AppProperties
  get() = project.plugins.apply(AppPropertiesPlugin::class.java).appProps

