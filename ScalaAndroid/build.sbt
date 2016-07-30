lazy val app = project.in(file("app")).settings(androidBuild).settings(
  platformTarget := "android-23",
  buildToolsVersion := Option("23.0.3"),
  minSdkVersion := "16",
  scalaVersion := "2.11.8",
  libraryDependencies += "com.android.support" % "appcompat-v7" % "23.3.0",
  javacOptions in Compile ++= "-source" :: "1.7" :: "-target" :: "1.7" :: Nil,
  typedResources := false
  /* Settings to benchmark with ProGuard deactivated:
  useProguard in Android := false,
  useProguardInDebug in Android := (useProguard in Android).value,
  dexMulti in Android := true
  */
)
