PImage character1;
PImage character2;
PImage character3;
PImage character4;
PImage character5;


void characterMenu(){
  background(characterMenuBackground);
 
 //sets iamges at larger size for easy viewing
  character1 = loadImage("creeper.png");
  character1.resize(int(2*cube.cubeSize), int(2*cube.cubeSize));
  image(character1, width / 5, height / 3);
  
  character2 = loadImage("Colors.png");
  character2.resize(int(2*cube.cubeSize), int(2*cube.cubeSize));
  image(character2, width / 5 + 3*cube.cubeSize, height / 3);
  
  character3 = loadImage("Milkyway.png");
  character3.resize(int(2*cube.cubeSize), int(2*cube.cubeSize));
  image(character3, width / 5 + 6*cube.cubeSize, height / 3);
  
  character4 = loadImage("wall.png");
  character4.resize(int(2*cube.cubeSize), int(2*cube.cubeSize));
  image(character4, width / 5 + 9*cube.cubeSize, height / 3);
  
  character5 = loadImage("Ice.png");
  character5.resize(int(2*cube.cubeSize), int(2*cube.cubeSize));
  image(character5, width / 5 + 12*cube.cubeSize, height / 3);
  
  
  //checks if mouse is pressed over a cube
  if (mousePressed) {
    if (mouseX >= width / 5 && mouseX <= width / 5 + cube.cubeSize) {
      character1.resize(int(cube.cubeSize), int(cube.cubeSize));
      cube.gameCharacter = character1;
    }
    
    if (mouseX >= width / 5 + 3*cube.cubeSize && mouseX <= width / 5 + 3*cube.cubeSize + cube.cubeSize) {
      character2.resize(int(cube.cubeSize), int(cube.cubeSize));
      cube.gameCharacter = character2;
    }
    
    if (mouseX >= width / 5 + 6*cube.cubeSize && mouseX <= width / 5 + 6*cube.cubeSize + cube.cubeSize) {
      character3.resize(int(cube.cubeSize), int(cube.cubeSize));
      cube.gameCharacter = character3;
    }
    
    if (mouseX >= width / 5 + 9*cube.cubeSize && mouseX <= width / 5 + 9*cube.cubeSize + cube.cubeSize) {
      character4.resize(int(cube.cubeSize), int(cube.cubeSize));
      cube.gameCharacter = character4;
    }
    
    if (mouseX >= width / 5 + 12*cube.cubeSize && mouseX <= width / 5 + 12*cube.cubeSize + cube.cubeSize) {
      character5.resize(int(cube.cubeSize), int(cube.cubeSize));
      cube.gameCharacter = character5;
    }
  }
  
}
