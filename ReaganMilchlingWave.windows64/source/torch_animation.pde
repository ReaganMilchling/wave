
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

  void display(float xpos, float ypos) {
    frame = (frame+1) % imageCount;
    images[frame].resize(int(width/31.37), int(height/8.57)); 
    image(images[frame], xpos, ypos);
  }
}
