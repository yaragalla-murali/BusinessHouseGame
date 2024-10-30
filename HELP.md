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
public Game createGame(){return Game}

@GetMapping(/games/{id})
public Game getGame(int gameId){return Game}

@PostMapping(/games/{id}/boards)
public Board createBoard(String boardCords, int gameId){return Board}

@GetMapping(/boards/{id)
public Board getBoard(int boardId){return Board}

@PostMapping(/games/{id}/players
public Player createPlayer(int gameId){returns Player}

@PutMapping(/players)
public Player updatePlayer(Player player){}

@GetMapping(/players/{id})
public Player getPlayer(int playerId){return Player}

@PostMapping(/games/{id}/dices)
public Dice createDice(String diceoutputs, int gameId){return Dice}

@GetMapping(/dices/{id})
public int getDice(int diceId){return diceOutput}

@PutMapping(/players/{id}/{diceOutput}
public int movePlayer(int playerId, int diceOutput){return currentPositionOnBoard}

@PutMapping(/boards/{id}/{playersCurrentPositionOnBoard})
public int handleLandingCell(int boardId, int playersCurrentPositionOnBoard){return balanceAmt)