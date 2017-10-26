# Android app coding challenge
## The task
Your task is to implement an Advert details screen for the Gumtree app. You can check an example at the link/image below. Additionally, feel free to have a look at some other Advert details screen examples by downloading the Gumtree application in google play (https://play.google.com/store/apps/details?id=com.gumtree.android)

![Preview](https://s18.postimg.org/qmqdzhrdl/gumtreechallange.png)


Features:

- Mock the data endpoints in a way that you feel more comfortable with (e.g. use Content Providers or simple Java objects) so the screen can get the data from them.
- Support Adverts having more than one picture
- Support the feature of sharing an Advert
- Handle rotation
- Make sure screen is optimized for different android screen sizes

Keep in mind:

- Use of new android design patterns and/or libraries
- Gumtree app is built on: MVP, RXJava, Dagger2, Gradle, Espresso, Glide, etc.
- Easy to read and testable code


### Application Structure ###

The Application implemented and structured bases on the MVP pattern best practice, contributed by [Antonio Leiva](http://antonioleiva.com/mvp-android/).

Whole application functionality is implemented in "Core" module using pure Java, and .the "App" module contain all codes required for Android Application to load on Android OS, which can be replace by any other interface (e.g. console app or web app)

The **view** (MainActivity), contain one fragments. Advisor fragments  contain their own presenter and implement View interface and the only thing that the view will do is calling a method from the presenter every time there is an interface action.

The **presenter** (Advisor Presenters), are responsible to act as the middle man between views and models. They retrieves data from the Model or Database and returns it formatted to the view. It also decides what happens when user interact with the view.

The **models** (Advisor Interactor), would only be the gateway to the service domain layer or business logic. In this case it provide the data needed to be displayed in the view from Network.

The networking and API call are managed by [Retrofit](http://square.github.io/retrofit/) and OkHttp as its httpclient, contributed by [Square](http://square.github.io). It also shows decent logs while application is running in Debug mode. 

Layers communications are managed by [RxJava](https://github.com/ReactiveX/RxJava) & [RxAndroid](https://github.com/ReactiveX/RxAndroid) contributed by [ReactiveX](http://reactivex.io).

Dependency Injections are being managed by [Dagger](https://github.com/google/dagger) created by [Square](http://square.github.io) and now maintained by [Google](http://google.github.io/dagger/).

Used new DataBinding library contributed by Google in Adapters for faster development, and added CustomBindingAdapter to handle downloading and caching images using [Picasso](http://square.github.io/picasso/) library.

The Android Log system is replaced with [Timber](https://github.com/JakeWharton/timber) contributed by Jake Wharton, which avoid logging in release version.

