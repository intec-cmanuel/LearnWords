# Use Case diagram

```plantuml
@Startuml test

:user: as appUser
(add word) as addWord
(guess word) as guessWord
(delete word) as deleteWord

appUser --> addWord
appUser --> guessWord
appUser --> deleteWord

@Enduml 
```
