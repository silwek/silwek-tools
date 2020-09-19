[![](https://jitpack.io/v/silwek/silwek-tools.svg)](https://jitpack.io/#silwek/silwek-tools)

To use libraries to your project, add dependencies in your app build.gradle
```
implementation "com.github.silwek.silwek-tools:magellan:version"
implementation "com.github.silwek.silwek-tools:ui:version"
implementation "com.github.silwek.silwek-tools:picturepicker:version"
implementation "com.github.silwek.silwek-tools:location:version"
implementation "com.github.silwek.silwek-tools:binding:version"
```

And in your project build.gradle
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```