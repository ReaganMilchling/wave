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
    gameCharacter.resize(int(cubeSize), int(cubeSize));
    
  }
  
  // methods
  void moveCube() {
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
  
  void renderCube() {
    strokeWeight(2);
    rectMode(CORNER);
    fill(255);
    cubeX = constrain(cubeX, +cubeSize / 2, width - cubeSize * 3/2);
    cubeY = constrain(cubeY, +cubeSize / 2, height - cubeSize * 3/2);
    rect(cubeX, cubeY, int(cubeSize) - 2, int(cubeSize) - 2);
    image(gameCharacter, cubeX, cubeY);
    
  
  }
}
