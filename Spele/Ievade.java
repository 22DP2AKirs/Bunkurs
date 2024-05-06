
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;


public class Ievade implements NativeKeyListener {
  volatile static String ievade = "";
  private String taustins;

  public static void ieslegtIevadi() {
    try {
      GlobalScreen.registerNativeHook();
    }
    catch (NativeHookException ex) {
      System.err.println("There was a problem registering the native hook.");
      System.err.println(ex.getMessage());

      System.exit(1);
    }
    GlobalScreen.addNativeKeyListener(new Ievade());
  }

  public void nativeKeyPressed(NativeKeyEvent e) {
    // ? Darbības, kad nospiež taustiņu.
    // 1. Nosaka, kāds taustiņš tika nospiests.
    taustins = NativeKeyEvent.getKeyText(e.getKeyCode());
    if (!taustins.equals("")) {
      ievade = taustins.toUpperCase();

      if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
        try {
          GlobalScreen.unregisterNativeHook(); // Atāķē klaviatūras klausītāju.
          System.exit(0); // Iziet no programmas.
        } catch (NativeHookException nativeHookException) {
          nativeHookException.printStackTrace();
        }
      }
    }
  }

  public static void nodzestIevadi() {
    ievade = "";
  }

  public static void nolemtVaiDzestIevadi() {
    // Ja ir, tad nodzēš ievadi.
    if (!ievade.equals("")) {
      nodzestIevadi();
    }
  }


  // public void nativeKeyReleased(NativeKeyEvent e) {
    
  // }

  // public void nativeKeyTyped(NativeKeyEvent e) {
  //     System.out.println("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
  // }
}