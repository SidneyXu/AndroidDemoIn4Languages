[中文版](https://github.com/SidneyXu/AndroidDemoIn4Languages/blob/master/README_CN.md)

# AndroidDemoIn4Languages

A simple Android application written in Java, Groovy, Scala and Kotlin in order to find out what is the better language for Android development.

## How to Compare?

Only import the minimal dependencies.

## Dependence Report

- In Java
  - Import `com.android.support:appcompat-v7:23.1.1`
- In Groovy
  - Import `com.android.support:appcompat-v7:23.1.1`
  - Import `org.codehaus.groovy:groovy:2.4.5:grooid`
  - Import `org.codehaus.groovy:groovy-json:2.4.5`
- In Scala
  - Import `com.android.support:appcompat-v7:23.1.1`
  - Import `org.scala-lang:scala-library:2.11.7`
  - Import `org.scaloid:scaloid_2.11:4.0`
- In Kotlin
  - Import `com.android.support:appcompat-v7:23.1.1`
  - Import `org.jetbrains.kotlin:kotlin-stdlib:1.0.0-beta-4584`

## Analysis Report

Line Counter

| Language | Files | Blank Lines | Comment Lines | Code Lines |
|:--------:|:--------:|:--------:|:--------:|:--------:|
| Java       |  3     |  22    |  0    |  165    |
| Groovy       |  3    |  23     |  9     |  140     |
| Scala       |  3     |  25    |  9     |  110     |
| Kotlin       |  3    |  23    |  9     |  133    |

Size Counter

| Language | Disable Proguard (bytes) | Enable Proguard (bytes) |
|:--------:|:--------:|:--------:|
| Java       |  1,220,887      | 654,906
| Groovy       |  2,934,236      | 1,674,177
| Scala       |  over 65536 methods      | 1,190,188
| Kotlin       |   1,602,041     | 697,290

Build Speed

| Language | Gradle Plugin | Spend (secs) |
|:--------:|:--------:|:--------:|
| Java       |  2.0.0-alpha7      | ≈ 35
| Groovy       |  2.0.0-alpha7      | > 120
| Scala       |  1.3.1      | > 160
| Kotlin       |   1.3.1     | ≈ 45

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

## Community Support

- Java Native Support
- Groovy Official Support
- Scala No Support
- Kotlin Official Support

## Conclusion

The futher comparasion to be continue...

