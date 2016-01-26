# AndroidDemoIn4Languages

使用 Java, Groovy, Scala 和 Kotlin 编写同一个应用来对比哪种语言更适合进行 Android 开发。

## 对比方法

每种语言只使用最小依赖

## 依赖情况报告

- Java
  - 导入 `com.android.support:appcompat-v7:23.1.1`
- Groovy
  - 导入 `com.android.support:appcompat-v7:23.1.1`
  - 导入 `org.codehaus.groovy:groovy:2.4.5:grooid`
  - 导入 `org.codehaus.groovy:groovy-json:2.4.5`
- Scala
  - 导入 `com.android.support:appcompat-v7:23.1.1`
  - 导入 `org.scala-lang:scala-library:2.11.7`
  - 导入 `org.scaloid:scaloid_2.11:4.0`
- Kotlin
  - 导入 `com.android.support:appcompat-v7:23.1.1`
  - 导入 `org.jetbrains.kotlin:kotlin-stdlib:1.0.0-beta-4584`

## 分析报告

行数统计

```
-------------------------------------------------------------------------------
语言                     文件数          空行数        注释行数           代码行数
-------------------------------------------------------------------------------
Java                             3             22              0            165
Groovy                           3             23              9            140
Scala                            3             25              9            110
Kotlin                           3             23              9            133
```

大小统计

| 语言 | 不使用 Proguard (字节) |  使用 Proguard (字节) |
|:--------:|:--------:|:--------:|
| Java       |  1,220,887      | 654,906
| Groovy       |  2,934,236      | 1,674,177
| Scala       |  over 65536 methods      | 1,190,188
| Kotlin       |   1,602,041     | 697,290

编译速度

| 语言 | Gradle Plugin 版本 | 耗时 (秒) |
|:--------:|:--------:|:--------:|
| Java       |  2.0.0-alpha7      | ≈ 35
| Groovy       |  2.0.0-alpha7      | > 120
| Scala       |  1.3.1      | > 160
| Kotlin       |   1.3.1     | ≈ 45

## 代码对比

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

## 结论

根据以上报告，也许 Kotlin 更适合用于 Android 开发。

The futher comparasion to be continue...

