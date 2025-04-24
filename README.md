# "NecroPuzzles" - A Turn-Based 8-Bit Puzzle Stealth Survival Game 🧩

## Overview ✨

**NecroPuzzles**, a retro-styled 8-bit stealth puzzle game where survival is your only mission. Navigate through a deadly facility swarming with zombies, dodge traps, and outsmart the undead to escape. With four levels of increasing difficulty, dynamic pixel art, and immersive sound effects, NecroPuzzles delivers a thrilling mix of strategy & suspense. Whether you're unlocking doors, avoiding spikes, or planning your next stealthy move, every step counts. Victory awaits—but only for the clever & the strongest. Good luck, and may the best player win.

## "NecroPuzzles" Gameplay Mechanics 🧟
**1. 1️⃣ Start Up**

- The game starts at a **Main Menu** screen.
- It contains the **New Game** button, which is where users will play the game from the start.
- The **Level Selection** button, which is where users will select any levels from 1-4.
- The **Quit** button, which will exit the system, ending the application.

<p align="center">
<img src="https://github.com/user-attachments/assets/eb908c16-144b-4150-bb34-0fd25ff8f1e3" alt="Image" width="500"/>
<br/>
<strong><em>Figure 1: Main Menu Screen</em></strong>
</p>

**2. 🎚️ Level Selection**

- Player can choose any level to start from, it can be level 1 or level 4. Each with increasing difficulty & unique map designs.

<p align="center">
<img src="https://github.com/user-attachments/assets/8d1fbc95-9de6-4bc7-9ffc-ade10f56c0f6" alt="Image" width="500"/>
<br/>
<strong><em>Figure 2: Game Level 1</em></strong>
</p>

<p align="center">
<img src="https://github.com/user-attachments/assets/26938d94-21b1-4a8c-9720-441ba5a65b33" alt="Image" width="500"/>
<br/>
<strong><em>Figure 3: Game Level 2</em></strong>
</p>

<p align="center">
<img src="https://github.com/user-attachments/assets/c071ee7f-9a48-4ba9-84d6-8d546798f301" alt="Image" width="500"/>
<br/>
<strong><em>Figure 4: Game Level 3</em></strong>
</p>

<p align="center">
<img src="https://github.com/user-attachments/assets/eeb17161-38f0-4207-a3dd-e62b4bc11fc2" alt="Image" width="500"/>
<br/>
<strong><em>Figure 5: Game Level 4</em></strong>
</p>

**3. 📈 Difficulty**
- Each level is harder than the last (zombie placement, traps, etc).
- Players can pick any level from the **Level Selection** menu.

**4. ⏸️ Pause**
- Pressing `ESC` opens a pause menu with options: **Resume**, **Restart**, or **Back to Menu**.

<p align="center">
<img src="https://github.com/user-attachments/assets/85ce6705-73c4-481d-95af-4fbf22b4b161" width="500"/>
<br/>
<strong><em>Figure 6: Pause Screen</em></strong>
</p>

**5. 🏁 Win**
- Escape through the door while avoiding zombies = Victory Screen.
- Options to **Next Level** or **Quit** are shown.

<p align="center">
<img src="https://github.com/user-attachments/assets/ecc4df65-a38d-4624-8c9f-526ff691c8fd" width="500"/>
<br/>
<strong><em>Figure 7: Win Screen</em></strong>
</p>

- After beating the last level, a special **Thank You Screen** is shown.

<p align="center">
<img src="https://github.com/user-attachments/assets/eb069eac-d3e8-46a8-a2a9-eb8c3878f9ab" width="500"/>
<br/>
<strong><em>Figure 8: Thank You Screen</em></strong>
</p>

**6. ❌ Lose**
- Touching or being detected by zombies = Death Screen.
- Options to **Restart** or **Quit** are shown.

<p align="center">
<img src="https://github.com/user-attachments/assets/8d2829eb-85a8-49be-8bcd-f1ccba2ab3b7" width="500"/>
<br/>
<strong><em>Figure 9: Death/Lose Screen</em></strong>
</p>

**7. 🎨 Retro Pixel Art** 
- Custom 8-bit art for characters, environments, and menus to give nostalgic visuals.

**8. 🔊 Sound Effects**
- Dynamic background music & sound effects enhance the experience.

## How to Play 🎮
- Use the **A**, **W**, **S**, and **D** keys to move your character. 
- Avoid zombies & traps like spikes to go to the next level.
- Press buttons to deactivate traps.
- Reach the exit door to win the level.
- Press `ESC` to pause the game.
- Failing to avoid zombies = Game Over.

## Coding Information 🛠
Frontend:
- JavaFX (Stage, Scene, JButton, Label, Button, EventHandler, MediaPlayer, etc).
- Custom 8-bit pixel art for UI & entities (Player, Zombies, Buttons, etc).

Backend:
- Java (if-else, switch, ArrayList, try-catch, etc).
- Object Oriented Programming (OOP) Concepts: Classes, Inheritance, etc.
- Entity Management using `gamePanel`, `Level`, and `Entity` class structure.
- Handling all the data & levels using `Array`.

## Contributors 👥
1. Jerry Wingsky ([@jrywsky](https://linktr.ee/JerryWingsky))
- Menu & Level Selection Codes.
- Victory & Death Screen & Codes.
- Debugging the Code.
- Proofreading All Documents & Codes.

2. Kelvin ([Kelvin](https://github.com/posturecheck))
- Pause Screen.
- Debugging the Code.
- Proofreading All Codes.

3. Ricky Tanuarta ([Ricky](https://github.com/mrtanuarta))
- Zombie Codes.
- Button & Spike Codes.
- Level Loader Codes.
- UI Screen Integration.
- Major Debugging in UI Screens & Entities.
- Game Sound Implementation.

4. Eins Herbert Takawijaya Tee ([Eins](https://github.com/Estody))
- Map & Level Designer.
- Asset Artist
- Player, Tile, Moving, and Hitbox Logic.
