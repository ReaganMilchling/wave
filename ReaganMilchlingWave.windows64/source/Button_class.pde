
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
  
  void render() {
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
  void checkMouseover() {
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
