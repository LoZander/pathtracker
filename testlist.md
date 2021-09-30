# Test-list
- Ally character added to tracker should have the correct type.
- Enemy character added to tracker should have the correct type.
- Character named "Test" has the name "Test"
- Character named "Test2" has the name "Test2"
- Character created with initiative 10 actually has initiative 10.
- Character created with initiative 20 actually has initiative 20.
- NextTurn should change character in turn to the next character.
- Character after last character of the tracker
  is the first character of the tracker.
- Characters are sorted by initiative
- Characters are secondly sorted by type (ally then enemy)
- Characters are thirdly sorted by name (A-Z)
- Characters can be removed from the tracker
- Removing a character should not remove anything else
- getCharacter returns a character by the given name
- getCharacter returns null if there is no character by the given name
- getPlayerInTurn returns null if there are no players in the tracker
- Adding a player with a higher initiative,
  should not change the player in turn.