[![](https://jitpack.io/v/silwek/silwek-tools.svg)](https://jitpack.io/#silwek/silwek-tools)

To use libraries to your project, add dependencies in your app build.gradle
```
com.github.silwek.silwek-tools:location:$tools_version
com.github.silwek.silwek-tools:ui:$tools_version
com.github.silwek.silwek-tools:magellan:$tools_version
com.github.silwek.silwek-tools:picturepicker:$tools_version
com.github.silwek.silwek-tools:binding:$tools_version
com.github.silwek.silwek-tools:network:$tools_version

//For Java compatibility add also
com.github.silwek.silwek-tools:ui-java:$tools_version
com.github.silwek.silwek-tools:magellan-java:$tools_version
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