# AndroidDemoIn4Languages

どの言語はアンドロイド開発に似合うを探すのために、JavaやGroovyやScala やKotlinを使用して同じアプリを作る。

## 比較方法

依存を最小限に利用する。

## 依存情報報告

- Java
  - インポート `com.android.support:appcompat-v7:25.2.0`
- Groovy
  - インポート `com.android.support:appcompat-v7:25.2.0`
  - インポート `org.codehaus.groovy:groovy:2.4.10:grooid`
  - インポート `org.codehaus.groovy:groovy-json:2.4.10`
- Scala
  - インポート `com.android.support:appcompat-v7:25.2.0`
  - インポート `org.scala-lang:scala-library:2.11.8`
- Kotlin
  - インポート `com.android.support:appcompat-v7:25.2.0`
  - インポート `org.jetbrains.kotlin:kotlin-stdlib:1.1.1`

## 分析報告

行数統計

|   言語   | ファイル数 | 空行数  | 注釈行数 | ソースコード行数 |
| :----: | :---: | :--: | :--: | :------: |
|  Java  |   3   |  20  |  9   |   157    |
| Groovy |   3   |  23  |  9   |   140    |
| Scala  |   3   |  32  |  9   |   127    |
| Kotlin |   3   |  23  |  9   |   136    |

サイズ統計

|   言語   | Proguard を利用しない（バイト） | Proguard を使用して（バイト） |
| :----: | :------------------: | :-----------------: |
|  Java  |      1,438,673       |       893,137       |
| Groovy |      3,395,936       |      1,982,125      |
| Scala  |      3,277,007       |      1,349,352      |
| Kotlin |      1,833,258       |       903,566       |

メソッド数

|   言語   | Proguard を利用しない | Proguard を使用して |
| :----: | :-------------: | :------------: |
|  Java  |     17,416      |     7,608      |
| Groovy |     47,982      |     24,379     |
| Scala  |     67,608      |     20,109     |
| Kotlin |     23,587      |     7,656      |

コンパイルスピード

MacBook Pro (Retina, 15-inch, Mid 2014 & APPLE SSD SM0256F Media)でテストする

Gradle タスク: `./gradlew :app:clean :app:assembleDebug`
SBT タスク: `sbt app/clean app/android:package`

|   言語   | Gradle プラグイン バージョン | 時間かかる（秒） |   SBT   | 時間かかる（秒） |
| :----: | :----------------: | :------: | :-----: | :------: |
|  Java  |       2.2.0        |   ≈ 8    | 0.13.12 |   ≈ 10   |
| Groovy |       2.2.0        |   ≈ 20   | 0.13.12 |    -     |
| Scala  |       1.3.1        |   ≈ 28   | 0.13.12 |   ≈ 17   |
| Kotlin |       2.2.0        |   ≈ 9    | 0.13.12 |   ≈ 20   |


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

Typed Resources を利用して (ほか依存は追加しない):

```scala
val title = view.findView(TR.text1)
```

メモ：普通的な文法も使える

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

続く。。。
