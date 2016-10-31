# Dagger2-CLEAN-MVP Example

[![Build Status](https://travis-ci.org/voghDev/dagger2-clean-mvp-example.svg?branch=master)](https://travis-ci.org/voghDev/dagger2-clean-mvp-example)

Sample project with the first steps of my approach to Android CLEAN architecture, based on elegant solutions from respected developers.
Features a List of users, obtained from randomuser API.
Some of the aspects covered on this project are:

* Dependency Injection using [Dagger2][6].
* Elegant ~~ListView~~ **RecyclerView** implementation using [Renderers][15].
* View binding using Jake Wharton's [ButterKnife 6.x][11] (current is 8.x).
* Image loading using [picasso][12], round transformations using **PorterDuff**.
* Creating an image cache with [picasso][12].
* Model-View-Presenter + Interactor + DataSource layers, with different **DataSource**s:
  * a REST Api using [retrofit][13]
  * a local **JSON file**
  * a class generating **mock** instances
* Code-style rules using [checkstyle][16]. [This blogpost][17] indicates how checkstyle has been imported
* Android Unit testing using JUnit 4 and [Mockito][18].
* UI Testing using [Espresso][19]
* Low-boilerplate **MVP** implementation using generics (splitted into Presenter / APresenter / APresenterImpl).
* [Travis CI][22] integration including instrumentation tests ran on an AVD. see [.travis.yml script][23]

![Screenshot][firstScreenshot]

This project has taken the following **talks**, **articles**, and **repos** as inspiration (it wouldn't have been possible without them)

* [Jorge Barroso - Forgetting Android][3]
* [Pedro Vicente Gómez Sánchez - EffectiveAndroidUI][2]
* [Dependency Injection on Android - Pedro Gómez][4]
* [Dagger2 Example by David Lázaro][5]
* [Google's Dagger Example][6]
* [Antonio Leiva's MVP Example][7] and [Blog article][8]
* [protohipster - Jorge Barroso][21]
* [UpcomingMoviesMVP - Jose Luis Martín][14]
* [KataSuperHeroesAndroid - Karumi][20]

TODOs
-----

- [X] A better Thread management to do heavier operations outside the UI Thread
- [X] RecyclerViews, Renderers2.0
- [ ] More DataSources like ~~parse~~, Google Cloud, Azure, protobuf...
- [X] Unit tests
- [X] UI tests
- [X] Refactor to add Repository layer
- [X] Refactor Presenter layer to make it testable with JUnit
- [ ] Add retrofit2-based API layer, keeping the retrofit1 one
- [ ] Redesign UI to a more attractive one

Developed By
------------

* Olmo Gallegos Hernández - [@voghDev][9] - [mobiledevstories.com][10]

<a href="http://twitter.com/voghDev">
  <img alt="Follow me on Twitter" src="https://image.freepik.com/iconos-gratis/twitter-logo_318-40209.jpg" height="60" width="60" />
</a>
<a href="https://www.linkedin.com/profile/view?id=91543271">
  <img alt="Find me on Linkedin" src="https://image.freepik.com/iconos-gratis/boton-del-logotipo-linkedin_318-84979.png" height="60" width="60" />
</a>

# License

    Copyright 2016 Olmo Gallegos Hernández

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


[firstScreenshot]: ./screenshots/sample1.gif
[1]: https://github.com/pedrovgs/
[2]: https://github.com/pedrovgs/EffectiveAndroidUI
[3]: https://www.youtube.com/watch?v=ROdIvrLL1ao
[4]: https://www.youtube.com/watch?v=ONziKX93iTM
[5]: https://github.com/dlazaro66/Dagger2-example
[6]: https://github.com/google/dagger
[7]: https://github.com/antoniolg/androidmvp
[8]: http://www.limecreativelabs.com/mvp-android/
[9]: http://twitter.com/voghDev
[10]: http://www.mobiledevstories.com
[11]: https://github.com/JakeWharton/butterknife
[12]: https://github.com/square/picasso
[13]: https://github.com/square/retrofit
[14]: https://github.com/jlmd/UpcomingMoviesMVP
[15]: https://github.com/pedrovgs/Renderers
[16]: http://checkstyle.sourceforge.net/
[17]: https://mobiledevstories.wordpress.com/2016/06/17/android-configuring-checkstyle-for-your-android-project/
[18]: http://mockito.org/
[19]: https://google.github.io/android-testing-support-library/docs/espresso/
[20]: https://github.com/Karumi/KataSuperHeroesAndroid
[21]: https://github.com/flipper83/protohipster
[22]: http://travis-ci.org
[23]: https://github.com/voghDev/dagger2-clean-mvp-example/blob/master/.travis.yml
