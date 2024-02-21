The dice game is played with two dice. If the sum of the two dice results in 7, the game is won; otherwise, it is lost. A player can view a list of all the rolls they have made and the success rate.

To play the game and make a roll, a user must register with a non-repeating name. Upon registration, a unique numeric identifier and a registration date are assigned. If the user wishes, they can choose not to add any name and will be labeled "ANONYMOUS". There may be more than one "ANONYMOUS" player.

Each player can view a list of all the rolls they have made, including the value of each die and whether the game was won or lost. Additionally, they can see their success rate for all the rolls they have made.

A specific game cannot be deleted, but the entire list of rolls for a player can be deleted.

The software must allow listing all the players in the system, each player's success rate, and the average success rate of all players in the system.

The software must adhere to the main design patterns.

NOTES:

You must consider the following construction details:

URLs:

POST: /players: creates a player.
PUT /players: modifies the player's name.
POST /players/{id}/games/: a specific player makes a dice roll.
DELETE /players/{id}/games: deletes the rolls of a player.
GET /players/: returns a list of all players in the system with their average success rate.
GET /players/{id}/games: returns the list of rolls for a player.
GET /players/ranking: returns the average ranking of all players in the system, i.e., the average success rate.
GET /players/ranking/loser: returns the player with the worst success rate.
GET /players/ranking/winner: returns the player with the best success rate.
Phase 1:
Persistence: use MySQL as the database.
Phase 2:
Change whatever is necessary and use MongoDB to persist data.

Phase 3:
Add security: include JWT authentication for all accesses to the microservice's URLs.
