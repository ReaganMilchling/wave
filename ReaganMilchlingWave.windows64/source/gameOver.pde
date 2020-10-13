
void gameOver(){
  
  
  fill(100, 200, 230);
  stroke(255);
  rect(width/10, height/4, width*3/4, height/2);
  fill(255);
  textSize(width / 30);
  textAlign(LEFT);
  text("YOU DIED, Your score:" + playerScore, width/8, height*3/7);
  text("Press 'P' to go to the welcome screen or 'Q' to quit.", width/8, height *7/14, width*3/4, height);
}
