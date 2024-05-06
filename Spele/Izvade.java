public class Izvade extends Thread {
  // Izvada visas bildes uz termināli.
  String[][] masivs = new String[10][5];

  @Override
  public void run() {
    while (Main.spele) {
      System.out.println("Ievade : " + Ievade.ievade + "\033[0K");
      masivuIzvade2(masivs);

      try {Thread.sleep(100);} catch (Exception e) {}
    }
  }

  void masivuIzvade2(String[][] masivs) {
    for (int i = 0; i < masivs.length; i++) {
      for (int j = 0; j < masivs[i].length; j++) {
        System.out.print(masivs[i][j] + "   ");
      }
      System.out.println();
    }
    System.out.print("\033[0J\033[H");
  }

  public void nodzestTerminali() {
    System.out.print("\033[H\033[2J"); // Notīra terminālu.
    System.out.flush(); // Kaut kas ar kursora pozīciju.
  }
  
}
