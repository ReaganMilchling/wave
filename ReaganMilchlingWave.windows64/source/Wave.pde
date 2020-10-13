import processing.sound.*;
import static javax.swing.JOptionPane.*;
SoundFile menuMusic, healthLoss, buttonClick;

//sound
//high score

//intitializing global variables
int healthVal;
int gameState;
int storedTime = millis();
int timeDelay = 500;
String backgroundSize;
float w, h;
int keepScore;
int playerScore;
int playerLevel;
PImage gameBackground;
PImage characterMenuBackground;
PImage menuBackground;

//setting up classes
Cube cube;
BasicEnemy basicEnemy[] = new BasicEnemy[15];
Torch torch[] = new Torch[7];
Cat cat;
Button butt1, butt2, butt3, butt4;


void settings() {
  //for getting user to set own dimensions
  String backgroundSize = showInputDialog("How large would you like the height of the program to be?");
  float h = int(backgroundSize);
  float w = 1.7777*h;
  size(int(w), int(h));
}


void setup() {
  frameRate(30);

  //Initializing button values
  butt1 = new Button(width/4, height / 2 - 2 * height / 11, width / 2, height / 15, 125);
  butt2 = new Button(width/4, height / 2 - height / 11, width / 2, height / 15, 125);
  butt3 = new Button(width/4, height / 2, width / 2, height / 15, 125);
  butt4 = new Button(width/4, height / 2 + height / 11, width / 2, height / 15, 125);

  //global variables
  gameState = 0;
  healthVal = 75;
  playerScore = 0;
  playerLevel = 1;
  keepScore = 0;

  //setting up one class for each
  cat = new Cat();
  cube = new Cube(); 

  //creating objects from classes for use later
  for (int i = 1; i < basicEnemy.length; i++) {
    basicEnemy[i] = new BasicEnemy();
  }
  for (int i = 1; i < torch.length; i++) {
    torch[i] = new Torch();
  }

  //initiating sound
  healthLoss = new SoundFile(this, "losehealth.wav");
  menuMusic = new SoundFile(this, "Blocks.mp3");
  buttonClick = new SoundFile(this, "buttonClick.mp3");

  //setting up background for gameboard
  gameBackground = loadImage("pixelgamebackground.png");
  gameBackground.resize(width, height);

  //background for character menu
  characterMenuBackground = loadImage("pixelmoon.png");
  characterMenuBackground.resize(width, height);

  //menu background
  menuBackground = loadImage("linkjpg.jpg");
  menuBackground.resize(width, height);

  //game music
  menuMusic.play();
  menuMusic.loop();
}

void draw() {
  //gamestate 0 = menu
  if (gameState == 0) {
    Menu();
  }

  //gamestate 1 = play game
  if (gameState == 1) {
    Handler();
  }

  //gamestate 2 = character menu
  if (gameState == 2) {
    characterMenu();
  }

  //gamestate 3 = help
  if (gameState == 3) {
    gameHelp();
  }

  //gamestate 4 = end game s creen
  if (gameState == 4) {
    gameOver();
    //options to reset important variables for replayability
    healthVal = 100;
    cube.cubeX = width / 2;
    cube.cubeY = height / 2;
    for (int i = 0; i>12; i++) {
      basicEnemy[i].x = int(random(basicEnemy[i].cubeSize, width - basicEnemy[i].cubeSize));
      basicEnemy[i].y = int(random(basicEnemy[i].cubeSize, height - basicEnemy[i].cubeSize));
    }
  }
}

void keyPressed() {
  //wasd pressed keys for boolean value to move cube
  if (key == 'd') {
    cube.MoveCubeRight = true;
    cube.MoveCubeLeft = false;
  }
  if (key == 'a') {
    cube.MoveCubeLeft = true;
    cube.MoveCubeRight = false;
  }
  if (key == 's') {
    cube.MoveCubeDown = true;
    cube.MoveCubeUp = false;
  }
  if (key == 'w') {
    cube.MoveCubeUp = true;
    cube.MoveCubeDown = false;
  }  

  //get back to menu from end game
  if (key == 'p' && gameState == 4) {
    gameState = 0; 
    buttonClick.play();
    gameState = 0;
    healthVal = 75;
    playerScore = 0;
    playerLevel = 1;
    keepScore = 0;
  }

  //get back to menu from characterMenu
  if (key == 'p' && gameState == 2) {
    gameState = 0;
    buttonClick.play();
  }

  //get back to menu from help
  if (key == 'p' && gameState == 3) {
    buttonClick.play();
  }
}

void keyReleased() {
  //wasd release keys for boolean value to move cube
  if (key == 'd') {
    cube.MoveCubeRight = false;
  }
  if (key == 'a') {
    cube.MoveCubeLeft = false;
  }
  if (key == 's') {
    cube.MoveCubeDown = false;
  }
  if (key == 'w') {
    cube.MoveCubeUp = false;
  }
}
