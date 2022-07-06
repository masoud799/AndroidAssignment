# Sample-Search-Application
This is an interview assignment where we had to develop an Android App. Actually we emulated search page of Filimo android application. Users can search movies in the App which result is fetched with the Filimo API.
The user type desired movie's name inside search box and results showing to user automatically without needed to click on search button.
Because it is an interview assignment, UI has been kept simple. You can directly clone the repo and run the app.

### Flow
This app uses MVI (Model View Intent) architecture and consist of modules which each module has it's own functionality and helping us to have discrete units of functionality.

- **App Module**

  `:app` module is an [com.android.application](https://developer.android.com/studio/projects/android-library), which is needed to create the app bundle. It contains dependency graph and UI related classes. It presents data to screen and handle user interactions.

- **Base Module**

  `:base` module contains only framework related base classes that is used in other modules

- **Common Module**

  `:common` module contains code and resources which are shared between other modules

- **Data Module**

  `:data` module contains implementation of repository and remote repository interface adapt

- **Domain Module**

  `:domain` module contains use cases and repository interface adapt

- **Remote Module**

  `:remote` module contains remote data source related classes

- **Presentation Module**

  `:presentation` module contains business logic

### Tech Stack
- [Kotlin](https://kotlinlang.org)
- [Jetpack](https://developer.android.com/jetpack)
  * [Android KTX](https://developer.android.com/kotlin/ktx)
  * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
  * [Data Binding](https://developer.android.com/topic/libraries/data-binding)
  * [View Binding](https://developer.android.com/topic/libraries/view-binding)
  *  [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
  * [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)
- [Coroutines - Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html)
- [Dagger Hilt](https://dagger.dev/hilt/)
- [Retrofit](https://square.github.io/retrofit/)
- [OkHttp](https://github.com/square/okhttp)
- [KotlinX](https://github.com/Kotlin/kotlinx.serialization)
- [KotlinX Serialization Converter](https://github.com/JakeWharton/retrofit2-kotlinx-serialization-converter)
- [Glide](https://github.com/bumptech/glide)
