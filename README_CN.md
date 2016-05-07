# AndroidDemoIn4Languages

使用 Java, Groovy, Scala 和 Kotlin 编写同一个应用来对比哪种语言更适合进行 Android 开发。

## 对比方法

每种语言只使用最小依赖

## 依赖情况报告

- Java
  - 导入 `com.android.support:appcompat-v7:23.3.0`
- Groovy
  - 导入 `com.android.support:appcompat-v7:23.3.0`
  - 导入 `org.codehaus.groovy:groovy:2.4.6:grooid`
  - 导入 `org.codehaus.groovy:groovy-json:2.4.6`
- Scala
  - 导入 `com.android.support:appcompat-v7:23.3.0`
  - 导入 `org.scala-lang:scala-library:2.11.7`
- Kotlin
  - 导入 `com.android.support:appcompat-v7:23.3.0`
  - 导入 `org.jetbrains.kotlin:kotlin-stdlib:1.0.0`

## 分析报告

行数统计

| 语言 | 文件数 | 空行数 | 注释行数 | 代码行数 |
|:--------:|:--------:|:--------:|:--------:|:--------:|
| Java       |  3     |  20    |  0    |  157    |
| Groovy       |  3    |  23     |  9     |  140     |
| Scala       |  3     |  32    |  9     |  127     |
| Kotlin       |  3    |  23    |  9     |  136    |

大小统计

| 语言 | 不使用 Proguard (字节) |  使用 Proguard (字节) |
|:--------:|:--------:|:--------:|
| Java       |  1,228,376      | 772,571
| Groovy       |  3,003,235      | 1,759,722
| Scala       |  超过 65536 方法      | 1,026,688
| Kotlin       |   1,595,108     | 778,629

方法数量

| 语言 | 不使用 Proguard |  使用 Proguard |
|:--------:|:--------:|:--------:|
| Java       |  163,06 | 7,065
| Groovy       |  46,791 | 23,775
| Scala       |  over 65536 methods | 12,180
| Kotlin       |   23,236 |  7,193

编译速度

使用 MacBook Pro (Retina, 15-inch, Mid 2014 & APPLE SSD SM0256F Media) 进行测试

Gradle 测试命令: `./gradlew :app:clean :app:assembleDebug`
SBT 测试命令: `sbt app/clean app/android:package`


| 语言 | Gradle Plugin 版本 | 耗时 (秒) | SBT | 耗时 (秒)
|:--------:|:--------:|:--------:|:--------:|:--------:|
| Java       |  2.1.0-rc1      | ≈ 7	| 0.13.11 | ≈ 9
| Groovy       |  1.5.0      | ≈ 21 | 0.13.11 | -
| Scala       |  1.3.1      | ≈ 23 | 0.13.11 | ≈ 21
| Kotlin       |   2.1.0-rc1      | ≈ 8 | 0.13.11 | ≈ 16

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

使用 [Kotlin Android Extensions](https://kotlinlang.org/docs/tutorials/android-plugin.html) (无需添加依赖)

``` kotlin
val title = view.text1
```

注意：也可以使用以下传统形式

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

## 社区支持

- Java 原生支持
- Groovy 官方支持
- Scala 无
- Kotlin 官方支持

## 结论

有待更多的对比...

