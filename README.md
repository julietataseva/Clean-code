## Rules:

The board consists of a grid with twenty-four intersections or points. Each player has nine pieces, or "men", usually coloured black and white. Players try to form 'mills' (three of their own men lined horizontally or vertically) allowing a player to remove an opponent's man from the game. A player wins by reducing the opponent to two pieces (where they could no longer form mills and thus be unable to win), or by leaving them without a legal move.

The game proceeds in three phases:

1. Placing men on vacant points
2. Moving men to adjacent points
3. Moving men to any vacant point when the player has been reduced to three men (optional phase)

### Phase 1: Placing pieces

The game begins with an empty board. The players determine who plays first, then take turns placing their men one per play on empty points. If a player is able to place three of their pieces on contiguous points in a straight line, vertically or horizontally, they have formed a mill and may remove one of their opponent's pieces from the board and the game, with the caveat that a piece in an opponent's mill can only be removed if no other pieces are available. After all men have been placed, phase two begins.

### Phase 2: Moving pieces

Players continue to alternate moves, this time moving a man to an adjacent point. A piece may not "jump" another piece. Players continue to try to form mills and remove their opponent's pieces as in phase one. A player can "break" a mill by moving one of his pieces out of an existing mill, then moving it back to form the same mill a second time (or any number of times), each time removing one of his opponent's men. The act of removing an opponent's man is sometimes called "pounding" the opponent. When one player has been reduced to three men, phase three begins.

### Phase 3: "Flying"

When a player is reduced to three pieces, there is no longer a limitation on that player of moving to only adjacent points: The player's men may "fly" from any point to any vacant point.

## Game interface:

This is what an empty checkers board looks like, printed on a console.

```
·-----------·-----------·
|           |           |
|   ·-------·-------·   |
|   |       |       |   |
|   |   ·---·---·   |   |
|   |   |       |   |   |
·---·---·       ·---·---·
|   |   |       |   |   |
|   |   ·---·---·   |   |
|   |       |       |   |
|   ·-------·-------·   |
|           |           |
·-----------·-----------·
```

- A place not occupied by a piece is marked with: `·`
- White checkers are marked with the special symbol: `○`
- Black checkers are marked with: `●`
- The horizontal lines along which the checkers can move are marked with dashes: `-`
- The vertical lines along which the checkers can move are marked with: `|`

White and black checkers replace the blanks when "placed" on the board.

- Placing on the board can be done by submitting coordinates. The horizontal coordinates can be A, B, C, D, E, F or G. Accordingly, the vertical positions can be 1, 2, 3, 4, 5, 6 or 7. Note that positions such as A2 are invalid. For more information, please see this visualization on a coordinate board:

```
    A   B   C   D   E   F   G
   
1   ·-----------·-----------·
    |           |           |
2   |   ·-------·-------·   |
    |   |       |       |   |
3   |   |   ·---·---·   |   |
    |   |   |       |   |   |
4   ·---·---·       ·---·---·
    |   |   |       |   |   |
5   |   |   ·---·---·   |   |
    |   |       |       |   |
6   |   ·-------·-------·   |
    |           |           |
7   ·-----------·-----------·
```

- Moving a piece is done by writing two consecutive coordinates. Example: A1A4

Source rules: [Wikipedia](https://en.wikipedia.org/wiki/Nine_men%27s_morris)
