
void HUD(){
  
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
