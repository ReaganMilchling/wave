
void Menu() {
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
