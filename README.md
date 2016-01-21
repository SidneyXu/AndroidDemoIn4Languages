# AndroidDemoIn4Languages

A simple Android application written in Java, Groovy, Scala and Kotlin in order to find out what is the better language for Android development.

## How to compare?

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

```
-------------------------------------------------------------------------------
Language                     files          blank        comment           code
-------------------------------------------------------------------------------
Java                             3             22              0            165
Groovy                           3             23              9            140
Scala                            3             25              9            110
Kotlin                           3             23              9            133
```

Size Counter

| Language | Disable Proguard (bytes) | Enable Proguard (bytes) |
|:--------:|:--------:|:--------:|
| Java       |  1,220,887      | 654,906
| Groovy       |  2,934,236      | 1,674,177
| Scala       |  over 65536 methods      | 1,190,188
| Kotlin       |   1,602,041     | 697,290

## Conclusion

So in my opinion, Kotlin may be a better way to write an Android Application.

