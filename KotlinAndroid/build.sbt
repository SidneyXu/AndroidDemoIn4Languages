lazy val androidClasspath = taskKey[Classpath]("android classpath")

lazy val app = project.in(file("app")).settings(androidBuild).settings(
  platformTarget := "android-23",
  libraryDependencies += "com.android.support" % "appcompat-v7" % "23.1.1",
  javacOptions in Compile ++= "-source" :: "1.7" :: "-target" :: "1.7" :: Nil,
  kotlincPluginOptions in Compile ++= {
    val plugin = KotlinPluginOptions("org.jetbrains.kotlin.android")
    val layout = (projectLayout in Android).value
    plugin.option("package", applicationId.value) ::
    plugin.option("variant", "main;" + layout.res.getCanonicalPath) ::
      Nil
  },
  androidClasspath := (bootClasspath in Android).value,
  kotlinClasspath(Compile, androidClasspath),
  kotlinPlugin("android-extensions"),
  kotlinLib("stdlib")
)
