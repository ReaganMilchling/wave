import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.sound.*; 
import static javax.swing.JOptionPane.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Wave extends PApplet {



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


public void settings() {
  //for getting user to set own dimensions
  String backgroundSize = showInputDialog("How large would you like the height of the program to be?");
  float h = PApplet.parseInt(backgroundSize);
  float w = 1.7777f*h;
  size(PApplet.parseInt(w), PApplet.parseInt(h));
}


public void setup() {
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

public void draw() {
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
      basicEnemy[i].x = PApplet.parseInt(random(basicEnemy[i].cubeSize, width - basicEnemy[i].cubeSize));
      basicEnemy[i].y = PApplet.parseInt(random(basicEnemy[i].cubeSize, height - basicEnemy[i].cubeSize));
    }
  }
}

public void keyPressed() {
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

public void keyReleased() {
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

class Button {
  float x, y, h, w;
  int c;
  boolean mouseover;
  
  Button(float tempX, float tempY, float tempW, float tempH, int tempC) {
    //sets temp value for button shadow - deleted but left here
    x = tempX;
    y = tempY;
    h = tempH;
    w = tempW;
    c = tempC;
  }
  
  public void render() {
    // call mouseover method
    this.checkMouseover();
    
    stroke(1);
    //fill(0, 0, 0, 100);
    //rect(x - 4, y - 4, w, h);
    if (mouseover == true) {
      fill(0, 0, 0, 100);
    }
    else {
      fill(c, c, c, 100);
    }
    rect(x, y, w, h);
  }
  
  //checks for mouseover
  public void checkMouseover() {
    if (// x collision 
        mouseX > x && mouseX < x + w
        // y collision
        && mouseY > y && mouseY < y + h) {
        mouseover = true;
    }
    else {
      mouseover = false;
    }
  }
}

public void HUD(){
  
  //background health bar
  rectMode(CORNER);
  fill(255);
  strokeWeight(2);
  rect(width * 1/4, height * 9/10, width / 2, height / 18);
  
  //actual green bar displaying health
  noStroke();
  fill(0, 255, 0);
  healthVal = constrain(healthVal, 0, 255);
  rect(width * 1/4, height * 9/10, width / 2 * healthVal * 4/3 / 100, height / 18);
  
  //score\level
  fill(0);
  textSize(width/40);
  textAlign(LEFT);
  text("Score: " + playerScore, width/19, height*23/25);
  text("Level: " + playerLevel, width/19, height*24/25);
}

//keeps things ordered
public void Handler(){
  background(gameBackground);
  
  //calling functions to set up game
  HUD();
  cube.moveCube();
  cube.renderCube();
  spawn();
  
  //calling torches/cat
  torch[1].display(width / 5, height * 1/5);
  torch[2].display(width / 5, height * 2/5);
  torch[3].display(width / 5, height * 3/5);
  torch[4].display(width - width / 5, height * 1/5);
  torch[5].display(width - width / 5, height * 2/5);
  torch[6].display(width - width / 5, height * 3/5);
  cat.display(width*4/5, height*6/7);
  
  //changes gamestate if you lose
  if (healthVal == 0){
   gameState = 4; 
  }
}

public void Menu() {
  strokeWeight(1);
  background(menuBackground);
  
  //title
  fill(0, 0, 150);
  textSize(height/5);
  textAlign(CENTER);
  text("WAVE", width/2, height/4);
  
  //text for the buttons
  fill(0, 0, 0);
  textSize(width/30);
  text("PLAY GAME", width / 2, height / 2 - 2 * height / 11 + height / 18);
  text("CHARACTER OPTIONS", width / 2, height / 2 - height / 11 + height / 18);
  text("HELP", width / 2, height / 2 + height / 18);
  text("QUIT GAME", width / 2, height / 2 + height / 11 + height / 18);
  
  //render buttons
  butt1.render();
  butt2.render();
  butt3.render();
  butt4.render(); 
  
  // moving from menu to gamestates
  if (mousePressed){
    if (mouseX > width / 4 && mouseX < width / 4 + width / 2 && mouseY > height / 2 - 2 * height / 11 && mouseY <  height / 2 - 2 * height / 11 + height / 15 && gameState == 0) {
      gameState = 1;
      buttonClick.play();
    }
    if (mouseX > width / 4 && mouseX < width / 4 + width / 2 && mouseY > height / 2 - height / 11 && mouseY <  height / 2 - height / 11 + height / 15 && gameState == 0) {
      gameState = 2;
      buttonClick.play();
    }
    if (mouseX > width / 4 && mouseX < width / 4 + width / 2 && mouseY > height / 2 && mouseY <  height / 2 + height / 15 && gameState == 0) {
      gameState = 3;
      buttonClick.play();
    }
    if (mouseX > width / 4 && mouseX < width / 4 + width / 2 && mouseY > height / 2 + height / 11 && mouseY <  height / 2 + height / 11 + height / 15 && gameState == 0) {
      exit();
      buttonClick.play();
    }
  }
}
public class BasicEnemy {

  //attributes/fields
  float cubeSize;
  float y, x;
  float velX;
  float velY;
  float speed1;
  float speed2;
  float speed3;
  
  //Constructor
  BasicEnemy() {
    speed1 = width / 45;
    speed2 = width / 40;
    speed3 = width / 35;
    velY = speed1;
    velX = speed1;
    x = PApplet.parseInt(random(cubeSize, width - cubeSize));
    y = PApplet.parseInt(random(cubeSize, height - cubeSize));
    cubeSize = width / 28;
  }
  
  //Methods
  public void collisions(){
    
    if (x > cube.cubeX - cube.cubeSize) {
      if (x < cube.cubeX + cube.cubeSize) {
        if (y > cube.cubeY - cube.cubeSize) {
          if (y < cube.cubeY + cubeSize) {
            healthVal--;
            healthLoss.play();
          }
        }
      }
    }
  }
  
  public void basicEnemyUpdate(){
    x += velX;
    y += velY;
    
    if (y <= 2 || y >= height - cubeSize - 2) {
      velY *= -1;
    }
    if (x <= 2 || x >= width - cubeSize - 2) {
      velX *= -1;
    }
  }
  
  public void renderbasicEnemy(){
    
    rectMode(CORNER);
    fill(150, 10, 10);
    x = constrain(x, 0, width - cubeSize);
    y = constrain(y, 0, height - cubeSize);
    rect(x, y, cubeSize, cubeSize);

  }
}

class Cat {
  PImage[] images;
  int imageCount;
  int frame;
  
  Cat() {
    imageCount = 13;
    images = new PImage[imageCount];
    for (int i = 0; i < imageCount; i++) {
      //assigns 
      String filename = "cat" + i + ".png";
      images[i] = loadImage(filename);
    }
  }

  public void display(float xpos, float ypos) {
    frame = (frame+1) % imageCount;
    images[frame].resize(width/6, height/8); 
    image(images[frame], xpos, ypos);
  }
}
PImage character1;
PImage character2;
PImage character3;
PImage character4;
PImage character5;


public void characterMenu(){
  background(characterMenuBackground);
 
 //sets iamges at larger size for easy viewing
  character1 = loadImage("creeper.png");
  character1.resize(PApplet.parseInt(2*cube.cubeSize), PApplet.parseInt(2*cube.cubeSize));
  image(character1, width / 5, height / 3);
  
  character2 = loadImage("Colors.png");
  character2.resize(PApplet.parseInt(2*cube.cubeSize), PApplet.parseInt(2*cube.cubeSize));
  image(character2, width / 5 + 3*cube.cubeSize, height / 3);
  
  character3 = loadImage("Milkyway.png");
  character3.resize(PApplet.parseInt(2*cube.cubeSize), PApplet.parseInt(2*cube.cubeSize));
  image(character3, width / 5 + 6*cube.cubeSize, height / 3);
  
  character4 = loadImage("wall.png");
  character4.resize(PApplet.parseInt(2*cube.cubeSize), PApplet.parseInt(2*cube.cubeSize));
  image(character4, width / 5 + 9*cube.cubeSize, height / 3);
  
  character5 = loadImage("Ice.png");
  character5.resize(PApplet.parseInt(2*cube.cubeSize), PApplet.parseInt(2*cube.cubeSize));
  image(character5, width / 5 + 12*cube.cubeSize, height / 3);
  
  
  //checks if mouse is pressed over a cube
  if (mousePressed) {
    if (mouseX >= width / 5 && mouseX <= width / 5 + cube.cubeSize) {
      character1.resize(PApplet.parseInt(cube.cubeSize), PApplet.parseInt(cube.cubeSize));
      cube.gameCharacter = character1;
    }
    
    if (mouseX >= width / 5 + 3*cube.cubeSize && mouseX <= width / 5 + 3*cube.cubeSize + cube.cubeSize) {
      character2.resize(PApplet.parseInt(cube.cubeSize), PApplet.parseInt(cube.cubeSize));
      cube.gameCharacter = character2;
    }
    
    if (mouseX >= width / 5 + 6*cube.cubeSize && mouseX <= width / 5 + 6*cube.cubeSize + cube.cubeSize) {
      character3.resize(PApplet.parseInt(cube.cubeSize), PApplet.parseInt(cube.cubeSize));
      cube.gameCharacter = character3;
    }
    
    if (mouseX >= width / 5 + 9*cube.cubeSize && mouseX <= width / 5 + 9*cube.cubeSize + cube.cubeSize) {
      character4.resize(PApplet.parseInt(cube.cubeSize), PApplet.parseInt(cube.cubeSize));
      cube.gameCharacter = character4;
    }
    
    if (mouseX >= width / 5 + 12*cube.cubeSize && mouseX <= width / 5 + 12*cube.cubeSize + cube.cubeSize) {
      character5.resize(PApplet.parseInt(cube.cubeSize), PApplet.parseInt(cube.cubeSize));
      cube.gameCharacter = character5;
    }
  }
  
}
public class Cube {
 
  // attributes/fields
  float cubeSize;
  float cubeX, cubeY;
  float cubeMove;
  float zCubeMove;
  float cubeSpeed;
  
  PImage gameCharacter;
  
  boolean MoveCubeRight;
  boolean MoveCubeLeft;
  boolean MoveCubeUp;
  boolean MoveCubeDown;
  
  
  Cube() {
    cubeSize = width / 29;
    cubeX = width / 2;
    cubeY = height / 2;
    cubeMove = width / 50;
    zCubeMove = (cubeMove / sqrt(2));
    cubeSpeed = cubeMove;
    gameCharacter = loadImage("Ice.png");
    gameCharacter.resize(PApplet.parseInt(cubeSize), PApplet.parseInt(cubeSize));
    
  }
  
  // methods
  public void moveCube() {
    if ((MoveCubeRight||MoveCubeLeft) && (MoveCubeUp||MoveCubeDown)){
      cubeSpeed = zCubeMove;
    }  
    else{
      cubeSpeed = cubeMove;
    }
        if (MoveCubeRight == true){
      cubeX += cubeSpeed;
    }
    
    if (MoveCubeLeft == true){
      cubeX -= cubeSpeed;
    }
    
    if (MoveCubeUp == true){
      cubeY -= cubeSpeed;
    }
    
    if (MoveCubeDown == true){
      cubeY += cubeSpeed;
    }
  }
  
  public void renderCube() {
    strokeWeight(2);
    rectMode(CORNER);
    fill(255);
    cubeX = constrain(cubeX, +cubeSize / 2, width - cubeSize * 3/2);
    cubeY = constrain(cubeY, +cubeSize / 2, height - cubeSize * 3/2);
    rect(cubeX, cubeY, PApplet.parseInt(cubeSize) - 2, PApplet.parseInt(cubeSize) - 2);
    image(gameCharacter, cubeX, cubeY);
    
  
  }
}

public void gameHelp(){
  background(characterMenuBackground);
  
  fill(0);
  textSize(width/35);
  text("Use the WASD keys to dodge the enemy cubes. Everytime you reach a new level, more enemies will spawn or they will increase their speed. Good Luck.", width/4, height/4, width/2, height*3/5);
  
  
}

public void gameOver(){
  
  
  fill(100, 200, 230);
  stroke(255);
  rect(width/10, height/4, width*3/4, height/2);
  fill(255);
  textSize(width / 30);
  textAlign(LEFT);
  text("YOU DIED, Your score:" + playerScore, width/8, height*3/7);
  text("Press 'P' to go to the welcome screen or 'Q' to quit.", width/8, height *7/14, width*3/4, height);
}
//function for counting time, spawning cubes, and keeping score

public void spawn() {
  
  playerScore++;
  keepScore++;
  
  
  basicEnemy[1].basicEnemyUpdate();
  basicEnemy[1].renderbasicEnemy();
  basicEnemy[1].collisions();
  
  //leveling up and adding new enemies with different speeds
  if (playerLevel >= 2) {
      basicEnemy[2].basicEnemyUpdate();
      basicEnemy[2].renderbasicEnemy();
      basicEnemy[2].collisions();
   }
  if (playerLevel == 3) {
    for (int i = 0; i > 2; i++) {
      basicEnemy[i].velX = basicEnemy[i].speed2;
      basicEnemy[i].velY = basicEnemy[i].speed2;
     }
   }
   if (playerLevel >= 4) {
      basicEnemy[3].basicEnemyUpdate();
      basicEnemy[3].renderbasicEnemy();
      basicEnemy[3].collisions();
   }
   if (playerLevel >= 5) {
      basicEnemy[4].basicEnemyUpdate();
      basicEnemy[4].renderbasicEnemy();
      basicEnemy[4].collisions();
   }
   if (playerLevel == 6) {
    for (int i = 2; i > 4; i++) {
      basicEnemy[i].velX = basicEnemy[i].speed2;
      basicEnemy[i].velY = basicEnemy[i].speed2;
     }
   }
   if (playerLevel >= 7) {
      basicEnemy[5].basicEnemyUpdate();
      basicEnemy[5].renderbasicEnemy();
      basicEnemy[5].collisions();
   }
   if (playerLevel >= 8) {
      basicEnemy[6].basicEnemyUpdate();
      basicEnemy[6].renderbasicEnemy();
      basicEnemy[6].collisions();
   }
   if (playerLevel == 9) {
    for (int i = 4; i > 6; i++) {
      basicEnemy[i].velX = basicEnemy[i].speed2;
      basicEnemy[i].velY = basicEnemy[i].speed2;
     }
   }
   if (playerLevel >= 10) {
      basicEnemy[7].basicEnemyUpdate();
      basicEnemy[7].renderbasicEnemy();
      basicEnemy[7].collisions();
   }
   if (playerLevel >= 11) {
      basicEnemy[8].basicEnemyUpdate();
      basicEnemy[8].renderbasicEnemy();
      basicEnemy[8].collisions();
   }
   if (playerLevel == 12) {
    for (int i = 6; i > 8; i++) {
      basicEnemy[i].velX = basicEnemy[i].speed2;
      basicEnemy[i].velY = basicEnemy[i].speed2;
     }
   }
   if (playerLevel >= 13) {
      basicEnemy[9].basicEnemyUpdate();
      basicEnemy[9].renderbasicEnemy();
      basicEnemy[9].collisions();
   }
   if (playerLevel >= 14) {
      basicEnemy[10].basicEnemyUpdate();
      basicEnemy[10].renderbasicEnemy();
      basicEnemy[10].collisions();
   }
   if (playerLevel == 15) {
    for (int i = 8; i > 10; i++) {
      basicEnemy[i].velX = basicEnemy[i].speed2;
      basicEnemy[i].velY = basicEnemy[i].speed2;
     }
   }
   if (playerLevel >= 16) {
      basicEnemy[11].basicEnemyUpdate();
      basicEnemy[11].renderbasicEnemy();
      basicEnemy[11].collisions();
   }
   if (playerLevel >= 17) {
      basicEnemy[12].basicEnemyUpdate();
      basicEnemy[12].renderbasicEnemy();
      basicEnemy[12].collisions();
   }
   if (playerLevel == 18) {
    for (int i = 10; i > 12; i++) {
      basicEnemy[i].velX = basicEnemy[i].speed2;
      basicEnemy[i].velY = basicEnemy[i].speed2;
     }
   }
   if (playerLevel >= 19) {
      basicEnemy[13].basicEnemyUpdate();
      basicEnemy[13].renderbasicEnemy();
      basicEnemy[13].collisions();
   }
   if (playerLevel >= 20) {
      basicEnemy[14].basicEnemyUpdate();
      basicEnemy[14].renderbasicEnemy();
      basicEnemy[14].collisions();
   }
   if (playerLevel >= 21) {
      basicEnemy[15].basicEnemyUpdate();
      basicEnemy[15].renderbasicEnemy();
      basicEnemy[15].collisions();
   }
   if (playerLevel == 22) {
    for (int i = 12; i > 15; i++) {
      basicEnemy[i].velX = basicEnemy[i].speed2;
      basicEnemy[i].velY = basicEnemy[i].speed2;
     }
   }
   if (playerLevel == 25) {
    for (int i = 0; i > 15; i++) {
      basicEnemy[i].velX = basicEnemy[i].speed3;
      basicEnemy[i].velY = basicEnemy[i].speed3;
     }
   }
   
  
  //for incrementing levels 
  if (keepScore >= 100) {
    keepScore = 0; 
    playerLevel += 1;
  }
}

class Torch {
  PImage[] images;
  int imageCount;
  int frame;
  
  Torch() {
    imageCount = 11;
    images = new PImage[imageCount];
    for (int i = 0; i < imageCount; i++) {
      //assigns 
      String filename = "a" + i + ".png";
      images[i] = loadImage(filename);
    }
  }

  public void display(float xpos, float ypos) {
    frame = (frame+1) % imageCount;
    images[frame].resize(PApplet.parseInt(width/31.37f), PApplet.parseInt(height/8.57f)); 
    image(images[frame], xpos, ypos);
  }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Wave" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
