
//keeps things ordered
void Handler(){
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
