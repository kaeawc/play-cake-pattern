# Example Play! Scala app with Cake Pattern

I was looking at these tutorials:

* http://playlatam.wordpress.com/tag/cake-pattern/
* http://www.cakesolutions.net/teamblogs/2011/12/19/cake-pattern-in-depth/
* http://jonasboner.com/2008/10/06/real-world-scala-dependency-injection-di/

... and got curious enough to implement it myself in the latest release version of Play! Scala to date (2.2.0-M2).  It makes use of some 2.2 additions like Action.async, database evolutions including generating data, has tests on different layers of the cake pattern in order to look at how dependency injection might be used, actually uses a real entity with implicit json read/writes (all the examples I found lacked the actual definition of the entity they were passing around) and lastly I configured the app to run an H2 in memory database with MySQL compatibility, so if you want to check this out and run it on something else that should be easy enough.

## How this is organized

    /app
      /components
        Default.scala - trait to extend all the default services and repositories you would want to use by default.
      /controllers
        Application - defines app config (namely inheriting components.Default) for all other controllers
        Landing - renders HTML for landing route
        Users - JSON API for Users, makes use of UserService (and thereby the UserRepository)
      /entities
        User - the only entity in the entire app, has implicit JSON serialization and Anorm SQL Result mapping
      /repositories
        UserRepository - traits and classes for handling data layer for Users, implementation of Anorm
      /services
    /conf - some configs
    /project
      Build.scala - note that while Play 2.x bundles specs2 and mockito for testing it does not do so for simply running the app
    /test
      /repositories
        UserRepositorySpec - bit redundant to write tests on this with a FakeApplication since the routes spec covers the same code, was just being thorough :)
      /routes
        LandingRouteSpec - just being thorough, covers the only HTML route
        UserRouteSpec - covers the JSON Users API with FakeApplication instances
      /services
        UserServiceSpec - unit tests the UserService by mocking UserRepository with Mockito (doesn't really test anything, just showing for demo purposes)
      package.scala - helper method for FakeApplication config