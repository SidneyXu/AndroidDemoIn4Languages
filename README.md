[中文版](https://github.com/SidneyXu/AndroidDemoIn4Languages/blob/master/README_CN.md)
[日本語](https://github.com/SidneyXu/AndroidDemoIn4Languages/blob/master/README_JP.md)

# AndroidDemoIn4Languages

A simple Android application written in Java, Groovy, Scala and Kotlin in order to find out what is the better language for Android development.

## ChangeLog

- 2017-03-30 Update versions: Android Sdk -> v25,  Gradle Plugin -> v2.2.0, Kotlin -> v1.1.1, Groovy -> 2.4.10
- 2016-07-30 Update Scala, SBT, sbt-android versions
- 2016-05-19 Update Kotlin from v1.0.0 to v1.0.2

## How to Compare?

Only import the minimal dependencies.

## Dependence Report

- In Java
  - Import `com.android.support:appcompat-v7:25.2.0`
- In Groovy
  - Import `com.android.support:appcompat-v7:25.2.0`
  - Import `org.codehaus.groovy:groovy:2.4.10:grooid`
  - Import `org.codehaus.groovy:groovy-json:2.4.10`
- In Scala
  - Import `com.android.support:appcompat-v7:25.2.0`
  - Import `org.scala-lang:scala-library:2.11.8`
- In Kotlin
  - Import `com.android.support:appcompat-v7:25.2.0`
  - Import `org.jetbrains.kotlin:kotlin-stdlib:1.1.1`

## Analysis Report

Line Counter

| Language | Files | Blank Lines | Comment Lines | Code Lines |
| :------: | :---: | :---------: | :-----------: | :--------: |
|   Java   |   3   |     20      |       9       |    157     |
|  Groovy  |   3   |     23      |       9       |    140     |
|  Scala   |   3   |     32      |       9       |    127     |
|  Kotlin  |   3   |     23      |       9       |    136     |

Size Counter

| Language | Disable Proguard (bytes) | Enable Proguard (bytes) |
| :------: | :----------------------: | :---------------------: |
|   Java   |        1,438,673         |         893,137         |
|  Groovy  |        3,395,936         |        1,982,125        |
|  Scala   |        3,277,007         |        1,349,352        |
|  Kotlin  |        1,833,258         |         903,566         |

Method Counter

| Language | Disable Proguard | Enable Proguard |
| :------: | :--------------: | :-------------: |
|   Java   |      17,416      |      7,608      |
|  Groovy  |      47,982      |     24,379      |
|  Scala   |      67,608      |     20,109      |
|  Kotlin  |      23,587      |      7,656      |

Build Speed

Test on MacBook Pro (Retina, 15-inch, Mid 2014 & APPLE SSD SM0256F Media)

Gradle task: `./gradlew :app:clean :app:assembleDebug`
SBT task: `sbt app/clean app/android:package`

| Language | Gradle Plugin | Time (secs) |   SBT   | Time (secs) |
| :------: | :-----------: | :---------: | :-----: | :---------: |
|   Java   |     2.2.0     |     ≈ 8     | 0.13.12 |    ≈ 10     |
|  Groovy  |     2.2.0     |    ≈ 20     | 0.13.12 |      -      |
|  Scala   |     1.3.1     |    ≈ 28     | 0.13.12 |    ≈ 17     |
|  Kotlin  |     2.2.0     |     ≈ 9     | 0.13.12 |    ≈ 20     |


## Coding Comparison

### Find View

Java

```java
TextView title = (TextView)view.findViewById(android.R.id.text1);
```

Groovy

```groovy
def title = view.findViewById(android.R.id.text1) as TextView
```

Scala

With Typed Resources enabled (include in Android SDK Plugin for SBT)

```scala
val title = view.findView(TR.text1)
```

Note: as a fallback, one can use the more traditional cast-based approach: 

```scala
val title = view.findViewById(android.R.id.text1).asInstanceOf[TextView]
```

Kotlin

With [Kotlin Android Extensions](https://kotlinlang.org/docs/tutorials/android-plugin.html) enabled (no extra dependencies needed):

``` kotlin
val title = view.text1
```

Note: as a fallback, one can use the more traditional cast-based approach:

```kotlin
val title = view.findViewById(android.R.id.text1) as TextView
```

### OnClickListener

Java

```java
button.setOnClickListener(new View.OnClickListener() {
    @Override
    void onClick(final View v) {
      //  do something
    }
})
```

Groovy

```groovy
button.onClickListener = {
    //  do something
}
```

Scala

```scala
button.onClick((v: View) =>
    //  do something
)
```

Kotlin

```kotlin
button.setOnClickListener {
    //  do something
}
```

### Callback

Java

```java
public interface FindCallback {
    void onFinish(List<String> results, Exception e);
}
private void findCountries(FindCallback doneCallback) {
    try {
        //  a long time mission
        doneCallback.onFinish(results, null);
    } catch (Exception e) {
        doneCallback.onFinish(null, e);
    }
}
findCountries(new FindCallback(){
    void onFinish(List<String> results, Exception e){
      //  handle result
    }
});
```

Groovy

```groovy
def findCountries(Closure doneCallback) {
    try {
        //  a long time mission
        doneCallback(results, null)
    } catch (e) {
        doneCallback(null, e)
    }
}
findCountries{ List<String> results, Exception e ->
  //  handle result
});
```

Scala

```scala
def findCountries(doneCallback: (ArrayBuffer[String], Exception) => Unit):Unit = {
    try {
        //  a long time mission
        doneCallback(results, null)
    } catch {
        case e: Exception => doneCallback(null, e)
    }
}
findCountries((names: ArrayBuffer[String], e: Exception) =>
  //  handle result
)
```

Kotlin

```kotlin
fun findCountries(doneCallback: (List<String>?, Exception?) -> Unit) {
    try {
        //  a long time mission
        doneCallback(results, null)
    } catch (e: Exception) {
        doneCallback(null, e)
    }
}
findCountries{ list, e ->
  //  handle result
}
```

### Extension

Scala

```scala
object AndroidContext {

  implicit class RichContext(ctx: Context) {
    def toast(msg: String) = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()

    def progress(): ProgressDialog = {
      val progressDialog = new ProgressDialog(ctx)
      progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
      progressDialog.setCancelable(false)
      progressDialog
    }
  }

}

import AndroidContext._
this.toast(msg)
this.progress().show()
```



Kotlin

```kotlin
fun Activity.toast(message: CharSequence?, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Activity.progress() = ProgressDialog(this).apply {
    setProgressStyle(ProgressDialog.STYLE_SPINNER)
    setCancelable(false)
}
```

### Switch

Java

```java
String result;
switch (obj) {
    case 1:
        result = "Result is 1";
        break;
    case 10:
        result = "Result is 10";
        break;
    default:
        result = "Default result";
        break;
}
```

Groovy

```groovy
def result
switch (obj) {
    case 0:
        result = "Object equals"
        break
    case 11..20:
        result = "Range contains"
        break
    case [1, 2, 3]:
        result = "List contains"
        break
    case Float:
        result = "Class instance"
        break
    case { it % 3 == 0 }:
        result = "Closure"
        break
    case ~'[0-9]{3}':
        result = "Pattern match"
        break
    default:
        result = "Default"
        break
}
```

Scala

```scala
val result = foo match {
  case 0 => "Object equals"
  case i if i == 10 || i == 11 => "Expression"
  case i: Int => s"Class instance holds $i"
  case List(1, 2, _*) => "List contains"
  case Number(n) => s"Case class holds $n"
  case t: {def length: Int} => "Duck type"
  case _ => "Default"
}
```

Kotlin

```kotlin
var result = when (foo) {
    0 -> "Object equals"
    3, 10 -> "Or"
    in 11..20 -> "Range contains"
    is Date -> "Class instance"
    !in 4..30 -> "Range not contain"
    else -> "Default"
}
```

## 3rd Party Libraries

Scala

- [Scaloid](https://github.com/pocorall/scaloid)

Kotlin

- [Anko](https://github.com/Kotlin/anko)

## Who use these languages?

TODO

## Community Support

- Java Native Support
- Groovy Official Support
- Scala No Support
- Kotlin Official Support

## Conclusion

The futher comparison to be continue...
