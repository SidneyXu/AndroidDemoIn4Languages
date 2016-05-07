lazy val app = project.in(file("app")).settings(androidBuild).settings(
  platformTarget := "android-23",
  buildToolsVersion := Option("23.0.3"),
  minSdkVersion := "16",
  libraryDependencies += "com.android.support" % "appcompat-v7" % "23.3.0",
  javacOptions in Compile ++= "-source" :: "1.7" :: "-target" :: "1.7" :: Nil
)
