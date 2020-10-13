
class Cat {
  PImage[] images;
  int imageCount;
  int frame;
  
  Cat() {
    imageCount = 13;
    images = new PImage[imageCount];
    for (int i = 0; i < imageCount; i++) {
      //assigns 
      String filename = "cat" + i + ".png";
      images[i] = loadImage(filename);
    }
  }

  void display(float xpos, float ypos) {
    frame = (frame+1) % imageCount;
    images[frame].resize(width/6, height/8); 
    image(images[frame], xpos, ypos);
  }
}
