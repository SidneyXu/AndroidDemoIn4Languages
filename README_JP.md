# AndroidDemoIn4Languages

どの言語はアンドロイド開発に似合うを探すのために、JavaやGroovyやScala やKotlinを使用して同じアプリを作る。

## 比較方法

依存を最小限に利用する。

## 依存情報報告

- Java
  - インポート `com.android.support:appcompat-v7:23.3.0`
- Groovy
  - インポート `com.android.support:appcompat-v7:23.3.0`
  - インポート `org.codehaus.groovy:groovy:2.4.6:grooid`
  - インポート `org.codehaus.groovy:groovy-json:2.4.6`
- Scala
  - インポート `com.android.support:appcompat-v7:23.3.0`
  - インポート `org.scala-lang:scala-library:2.11.7`
- Kotlin
  - インポート `com.android.support:appcompat-v7:23.3.0`
  - インポート `org.jetbrains.kotlin:kotlin-stdlib:1.0.0`

## 分析報告

行数統計

| 言語 | ファイル数 | 空行数 | 注釈行数 | ソースコード行数 |
|:--------:|:--------:|:--------:|:--------:|:--------:|
| Java       |  3     |  20    |  0    |  157    |
| Groovy       |  3    |  23     |  9     |  140     |
| Scala       |  3     |  32    |  9     |  127     |
| Kotlin       |  3    |  23    |  9     |  136    |

サイズ統計

| 言語 | Proguard を利用しない（バイト） | Proguard を使用して（バイト） |
|:--------:|:--------:|:--------:|
| Java       |  1,228,376      | 772,571
| Groovy       |  3,003,235      | 1,759,722
| Scala       |  over 65536 methods      | 1,026,688
| Kotlin       |   1,595,108     | 778,629

メソッド数

| 言語 | Proguard を利用しない | Proguard を使用して |
|:--------:|:--------:|:--------:|
| Java       |  163,06 | 7,065
| Groovy       |  46,791 | 23,775
| Scala       |  over 65536 methods | 12,180
| Kotlin       |   23,236 |  7,193

コンパイルスピード

MacBook Pro (Retina, 15-inch, Mid 2014 & APPLE SSD SM0256F Media)でテストする

Gradle タスク: `./gradlew :app:clean :app:assembleDebug`
SBT タスク: `sbt app/clean app/android:package`

| 言語 | Gradle プラグイン バージョン | 時間かかる（秒） | SBT | 時間かかる（秒）
|:--------:|:--------:|:--------:|:--------:|:--------:|
| Java       |  2.1.0-rc1      | ≈ 7	| 0.13.11 | ≈ 9
| Groovy       |  1.5.0      | ≈ 21 | 0.13.11 | -
| Scala       |  1.3.1      | ≈ 23 | 0.13.11 | ≈ 21
| Kotlin       |   2.1.0-rc1      | ≈ 8 | 0.13.11 | ≈ 16


## ソースコード対比

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

[Kotlin Android Extensions](https://kotlinlang.org/docs/tutorials/android-plugin.html) を利用して (ほか依存は追加しない):

``` kotlin
val title = view.text1
```

メモ：普通的な文法も使える

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

### コールバック

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

## コミュニティサポート

- Java 原生サポート
- Groovy 公式サポート
- Scala　なし
- Kotlin 公式サポート

## 結論

もっと比較を続く。。。
