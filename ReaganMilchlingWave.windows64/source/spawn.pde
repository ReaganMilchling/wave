//function for counting time, spawning cubes, and keeping score

void spawn() {
  
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
