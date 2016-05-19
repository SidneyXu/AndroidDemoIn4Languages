[中文版](https://github.com/SidneyXu/AndroidDemoIn4Languages/blob/master/README_CN.md)
[日本語](https://github.com/SidneyXu/AndroidDemoIn4Languages/blob/master/README_JP.md)

# AndroidDemoIn4Languages

A simple Android application written in Java, Groovy, Scala and Kotlin in order to find out what is the better language for Android development.

## ChangeLog

- 2016.5.19 Update Kotlin from v1.0.0 to v1.0.2

## How to Compare?

Only import the minimal dependencies.

## Dependence Report

- In Java
  - Import `com.android.support:appcompat-v7:23.3.0`
- In Groovy
  - Import `com.android.support:appcompat-v7:23.3.0`
  - Import `org.codehaus.groovy:groovy:2.4.6:grooid`
  - Import `org.codehaus.groovy:groovy-json:2.4.6`
- In Scala
  - Import `com.android.support:appcompat-v7:23.3.0`
  - Import `org.scala-lang:scala-library:2.11.7`
- In Kotlin
  - Import `com.android.support:appcompat-v7:23.3.0`
  - Import `org.jetbrains.kotlin:kotlin-stdlib:1.0.2`

## Analysis Report

Line Counter

| Language | Files | Blank Lines | Comment Lines | Code Lines |
| :------: | :---: | :---------: | :-----------: | :--------: |
|   Java   |   3   |     20      |       0       |    157     |
|  Groovy  |   3   |     23      |       9       |    140     |
|  Scala   |   3   |     32      |       9       |    127     |
|  Kotlin  |   3   |     23      |       9       |    136     |

Size Counter

| Language | Disable Proguard (bytes) | Enable Proguard (bytes) |
|:--------:|:--------:|:--------:|
| Java       |  1,228,376      | 772,571
| Groovy       |  3,003,235      | 1,759,722
| Scala       |  over 65536 methods      | 1,026,688
| Kotlin       |   1,525,725     | 736,843

Method Counter

| Language |Disable Proguard | Enable Proguard |
|:--------:|:--------:|:--------:|
| Java       |  163,06 | 7,065
| Groovy       |  46,791 | 23,775
| Scala       |  over 65536 methods | 12,180
| Kotlin       |   21,776 |  7,198

>Notice  
>Update Kotlin version  
>v1.0.0 -> v1.0.2  
>method 23236 -> 21776  
>method(proguard) 7124 -> 7198  
>size 1,594,619 -> 1,525,725  
>size(proguard) 776.281 -> 736,843  

Build Speed

Test on MacBook Pro (Retina, 15-inch, Mid 2014 & APPLE SSD SM0256F Media)

Gradle task: `./gradlew :app:clean :app:assembleDebug`
SBT task: `sbt app/clean app/android:package`

| Language | Gradle Plugin | Spend (secs) | SBT | Spend (secs)
|:--------:|:--------:|:--------:|:--------:|:--------:|
| Java       |  2.1.0-rc1      | ≈ 7    | 0.13.11 | ≈ 9
| Groovy       |  1.5.0      | ≈ 21 | 0.13.11 | -
| Scala       |  1.3.1      | ≈ 23 | 0.13.11 | ≈ 21
| Kotlin       |   2.1.0-rc1      | ≈ 8 | 0.13.11 | ≈ 16


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

