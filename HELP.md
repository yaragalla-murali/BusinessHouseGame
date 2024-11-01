# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.4/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.3.4/maven-plugin/build-image.html)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.3.4/reference/htmlsingle/index.html#using.devtools)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

----------------Game rest end points------------------

Game
------
@PostMapping(/games)
public Game createGame(Player player){return Game}

The first player will create the game and will share the game id to the other players who wants to join it.When the game is created the dice and board setup will also be done and the game will be ready to start. if no other players joins for a certain time the game will be aborted.

@PostMapping(/games/)
public Game ConnectToGame(Game game, Player player){return Game}

The player will be added to the game based on the shared game id.

@PutMapping(/players/{id}/{gameId}
public Game movePlayer(int playerId, int gemeId){return Game}

when player asks to move his position on board , first the check will happen whether the requested player is eligible for the turn. if he is eligible then the dice output will be taken and will be added to the player current position on board. based on the current position on the board the cell landing rules will be handled and the player current balance will be updated.

@PutMapping(/games/{id})
public Game endGameAndGetResults(int gameId){return Game)

when any player requested to end the game, the check will happen to see whether all the players have completed their turn. if completed the game status will be changed to "completed" and the game results will be given.