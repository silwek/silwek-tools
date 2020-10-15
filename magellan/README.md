
To use Magellan and Android Navigation :

1 - Include the following classpath in your top level build.gradle file:

```
buildscript {
    repositories {
        google()
    }
    dependencies {
        def nav_version = "2.3.0"
        classpath "androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version"
    }
}
```

2 - Add this line to your app or module's build.gradle file:

JAVA
```
apply plugin: "androidx.navigation.safeargs"
```

Kotlin only
```
apply plugin: "androidx.navigation.safeargs.kotlin"
```