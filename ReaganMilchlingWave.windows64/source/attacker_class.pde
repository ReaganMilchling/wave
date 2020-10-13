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
    x = int(random(cubeSize, width - cubeSize));
    y = int(random(cubeSize, height - cubeSize));
    cubeSize = width / 28;
  }
  
  //Methods
  void collisions(){
    
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
  
  void basicEnemyUpdate(){
    x += velX;
    y += velY;
    
    if (y <= 2 || y >= height - cubeSize - 2) {
      velY *= -1;
    }
    if (x <= 2 || x >= width - cubeSize - 2) {
      velX *= -1;
    }
  }
  
  void renderbasicEnemy(){
    
    rectMode(CORNER);
    fill(150, 10, 10);
    x = constrain(x, 0, width - cubeSize);
    y = constrain(y, 0, height - cubeSize);
    rect(x, y, cubeSize, cubeSize);

  }
}
