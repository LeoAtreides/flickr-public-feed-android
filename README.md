
# Flickr public feed Android
This project loads data from [Flicker public feed](https://www.flickr.com/services/feeds/docs/photos_public) and displays the list of items.

## Libraries
A few technologies where applyied to create this project:
- [RxJava](https://github.com/ReactiveX/RxJava): Used for threading, data flow and architecture purpouses.
- [Android Architecture Components - ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel.html): Used for improving life cycle management in ViewModel.
- [ConstraintLayout](https://developer.android.com/training/constraint-layout/index.html): A better and simpler way to creating layouts.
- [Retrofit](http://square.github.io/retrofit/): Great library for simple REST communication from Android.
- [Data Binding](https://developer.android.com/topic/libraries/data-binding/index.html): Simplified integration between layouts and data. Most of interactions where created using custom binding.
- [AutoValue](https://github.com/google/auto/tree/master/value) and extensions: Reduces boilerplate and improves performance of REST requests.

## MVVM
This project uses Model-View-ViewModel structure for UI, making the UI simpler and more responsive.

## Building the project
This project was build using Android Studio 3.0 Beta 7. To open the project, use the import option from Android Studio, since the `idea` files are in gitignore.
To build using command line, just clone the project and run `./gradlew assembleDebug`. An APK will be generated at `app/build/output/apk/debug`.
