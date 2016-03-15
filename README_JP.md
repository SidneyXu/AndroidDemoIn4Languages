# AndroidDemoIn4Languages

どの言語はアンドロイド開発に似合うを探すのために、Java　や　Groovy　や　Scala や Kotlin を使用して同じアプリを作る。

## 比較方法

依存を最小限に利用する。

## 依存情報報告

- Java
  - インポート `com.android.support:appcompat-v7:23.1.1`
- Groovy
  - インポート `com.android.support:appcompat-v7:23.1.1`
  - インポート `org.codehaus.groovy:groovy:2.4.5:grooid`
  - インポート `org.codehaus.groovy:groovy-json:2.4.5`
- Scala
  - インポート `com.android.support:appcompat-v7:23.1.1`
  - インポート `org.scala-lang:scala-library:2.11.7`
  - インポート `org.scaloid:scaloid_2.11:4.0`
- Kotlin
  - インポート `com.android.support:appcompat-v7:23.1.1`
  - インポート `org.jetbrains.kotlin:kotlin-stdlib:1.0.0-beta-4584`

## 分析報告

行数統計

| 言語 | ファイル数 | 空行数 | 注釈行数 | ソースコード行数 |
|:--------:|:--------:|:--------:|:--------:|:--------:|
| Java       |  3     |  22    |  0    |  165    |
| Groovy       |  3    |  23     |  9     |  140     |
| Scala       |  3     |  25    |  9     |  110     |
| Kotlin       |  3    |  23    |  9     |  133    |

サイズ統計

| 言語 | Proguard を利用しない（バイト） | Proguard を使用して（バイト） |
|:--------:|:--------:|:--------:|
| Java       |  1,220,887      | 654,906
| Groovy       |  2,934,236      | 1,674,177
| Scala       |  over 65536 methods      | 1,190,188
| Kotlin       |   1,602,041     | 697,290

コンパイルスピード

| 言語 | Gradle プラグイン バージョン | 時間かかる（秒） |
|:--------:|:--------:|:--------:|
| Java       |  2.0.0-alpha7      | ≈ 35
| Groovy       |  2.0.0-alpha7      | > 120
| Scala       |  1.3.1      | > 160
| Kotlin       |   1.3.1     | ≈ 45

メソッド数

| 言語 | Proguard を利用しない | Proguard を使用して |
|:--------:|:--------:|:--------:|
| Java       |  164,60 | 6,421
| Groovy       |  46,055 | 23,112
| Scala       |  over 65536 methods | 19,388
| Kotlin       |   24,061 |  6,559

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
