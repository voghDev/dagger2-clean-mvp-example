# Dagger2-CLEAN-MVP Example

Sample project with the first steps of my approach to Android CLEAN architecture, based on elegant solutions from various developers.
Features a ListView containing users, obtained from randomuser API.
Some of the aspects covered on this project are:

* Dependency Injection using [Dagger2][6]. Includes two modules with different scopes.
* Correct **ListView** implementation using [Renderers][15], from [@pedrovgs][1].
* Elegant View management using Jake Wharton's [ButterKnife][11].
* Image loading using [picasso][12], round transformations using the **PorterDuff** technique.
* Model-View-Presenter + Interactor + DataSource layers, where a Dagger-injected **Interactor** can query different **DataSources** (REST Api using [retrofit][13], local JSON file, mock generator class)
* A less-boilerplate **MVP** implementation using generics (splitted into Presenter / APresenter / APresenterImpl).

This project has taken the following **talks**, **articles**, and **repos** as inspiration (it wouldn't have been possible without them)

* [Jorge Barroso - Forgetting Android][3]
* [Pedro Vicente Gómez Sánchez - EffectiveAndroidUI][2]
* [Dependency Injection on Android - Pedro Gómez][4]
* [Dagger2 Example by David Lázaro (dlazaro66)][5]
* [Google's Dagger Example][6]
* [Antonio Leiva's MVP Example][7] and [Blog article][8]
* [protohipster - Jorge Barroso][9]
* [UpcomingMoviesMVP - Jose Luis Martín][14]

Desirable TODOs

* A better Thread management to do heavier operations outside the UI Thread
* RecyclerViews+Renderers2.0, Fragments, Material, CustomViews...
* More DataSources like parse, Google Cloud, Azure, protobuf...

Developed By
------------

* Olmo Gallegos Hernández - [@voghDev][9] - [mobiledevstories.com][10]

<a href="http://twitter.com/voghDev">
  <img alt="Follow me on Twitter" src="http://imageshack.us/a/img812/3923/smallth.png" />
</a>
<a href="https://www.linkedin.com/profile/view?id=91543271">
  <img alt="Find me on Linkedin" src="http://imageshack.us/a/img41/7877/smallld.png" />
</a>

# License

    Copyright 2015 Olmo Gallegos Hernández

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


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
