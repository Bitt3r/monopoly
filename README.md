# Monopoly
Monopoly is a multiplayer economics-themed board game. <br /> 
In the game, players roll two dice to move around the game board, <br />
buying and trading properties and developing them with houses and hotels. <br />
Players collect rent from their opponents, aiming to drive them into bankruptcy.
### Commands
#### When app is started you will be prompted to enter info, and you will get 2 choices

- ``start - to start monopoly``
- ``exit - terminate app``

Once start is typed, game is initiated:
- ``Enter number of players``
- ``Enter each player name``

Once the game start and it is first player turn you have the following commands: <br />
***Note: You must enter name of the player whose turn it is followed by command***

[playerName] {command} (option)
- ``name roll - roll the dice.`` 
- ``name buy - buy property you are on.``
- ``name auction - auction the property you are on.``
- ``name bid # - bid $# in the current auction.``
- ``name pass - remove yourself from the current auction.``
- ``name pay - get out of jail by paying $50.``
- ``name card - use a \"Get Out of Jail Free\" card.``
- ``name end - end your turn.``
- ``name money - see how much money you have.``
- ``name mortgage \"property name\" - mortgage a property.``
- ``name unmortgage \"property name\" - unmortgage a property.``
- ``name build \"property name\" - build a house/hotel on a property.``
- ``name unbuild \"property name\" - unbuild a house/hotel from a property.``
- ``name forfeit - forfeit the game.``
- ``board - see the game board.``
- ``exit - quit the game.``


There is one extra command that will not show, cheat command that will give you 1000$ <br />
Command is playerName cheat

TODO: 
- [ ] Add more options when starting app
- [ ] Optimize code
- [ ] Refactor some classes
- [ ] Create and flat project hieararchy
- [ ] Add docker
- [ ] Add tests
