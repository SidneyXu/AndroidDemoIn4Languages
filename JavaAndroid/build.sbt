lazy val app = project.in(file("app")).settings(androidBuild).settings(
  platformTarget := "android-23",
  libraryDependencies += "com.android.support" % "appcompat-v7" % "23.1.1",
  javacOptions in Compile ++= "-source" :: "1.7" :: "-target" :: "1.7" :: Nil
)
