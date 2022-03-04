# WordMasterWithGUI

# Big Stuff to do
- [] all scenes
- [] connect scenes to word master game engine

# Program Flow
1. Enter the game
2. FileIo should load all player into players arraylist
3. Register: call getPlayerData
   1. Get the username and password
   2. Check both fields are not empty; username: at least 2 char, password: regex
   3. check if username exist, if exists, tell him/her to login, not register
   4. if not exist, we double-check if password equals confirmed password
   5. Add player into arrayList
   6. Transit to lobby
4. Login: call getPlayerData
   1. Get the username and password
   2. Check both fields are not empty; username: at least 2 char, password: regex
   3. check if username exist, if not exists, tell him/her to register
   4. if exist, we check if password equals to the password the related player in players arraylist
   5. if yes, transit to lobby; if no, alert Wrong Password
5. Guest: call getPlayerData
   1. Transit to lobby, disable save for them
6. Lobby:
   1. NEW GAME: we should call Game.playGame()

# Class to modify
- [ ] static players' arraylist to allow all class to use? name should be different from players arraylist in Players class
- [ ] Game.playGame
  - [ ] getPlayerData
    - [ ] getUserName: it should collect username either from Login/Register Controller
    - [ ] getUserPassword: same as above
    - [ ] We should validate all things in Login/Register Form, so we should just start the game
  - [ ] playTurn: 
    - [ ] getNumTurns: get from Lobby form
    - [ ] getWordLength: get from Lobby form
    - [ ] wordlist.print...WordArray: need to interact with Game form