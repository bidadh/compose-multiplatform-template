import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.myKapt(dependencyNotation: Any): Dependency? =
  add("kapt", dependencyNotation)
