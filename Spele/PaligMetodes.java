
import java.util.ArrayList;

import javax.management.RuntimeErrorException;


public class PaligMetodes {
  // Šeit ir metodes, kuras atvieglos koda saprašanu un manu dzīvi.

  public static ArrayList<ArrayList<Object>> apmainitSarakstaElementu(ArrayList<Object> pirmaisElements, ArrayList<Object> otraisElements, ArrayList<ArrayList<Object>> saraksts) {
    // * Metode pareizi izpildās tikai tad, ja masīvā nav identisku elementu.
    // * Apmaina ar vietām norādītos elementus. Nav domāts milzīgiem sarakstiem.
    
    int pirmaIndekss = -1, otraIndekss = -1;
    // 1. Atrod sarakstā meklējamo elementu.
    for (int i = 0; i < saraksts.size(); i++) {
      // 1.1. Atrod jau elementu.
      if (saraksts.get(i).equals(pirmaisElements)) {
        pirmaIndekss = i;
        continue;
      }

      // 1.2. Atrod maināmo elementu.
      if (saraksts.get(i).equals(otraisElements)) {
        otraIndekss = i;
        continue;
      }
    }

    // 2. Pārbauda vai atrada abus elementus.
    if (pirmaIndekss == -1 || otraIndekss == -1) {
      throw new RuntimeErrorException(null);
    }

    // 3. Apmaina elementus.
    saraksts.set(pirmaIndekss, otraisElements);
    saraksts.set(otraIndekss, pirmaisElements);

    // Atgriež sarakstu ar nomainīto elementu.
    return saraksts;
  }
  
  public static String[] nomainitMasivaElementu(String[] masivs, String mainamaisElements, String jaunaisElements) {
    // * Metode pareizi izpildās tikai tad, ja masīvā nav identisku elementu.
    // Atrod masīvā meklējamo elementu.
    for (int i = 0; i < masivs.length; i++) {
      // Kad atrod elementu, tad to nomaina.
      if (masivs[i].equals(mainamaisElements)) {
        // Ja nomaina elementu, tad iet ārā no cikla.
        masivs[i] = jaunaisElements;
        break;
      }
    }
    // Atgriež masīvu ar nomainīto elementu.
    return masivs;
  }

  public static boolean masivaIrElementuDuplikati(String[] masivs) {
    // piem { 1 , 2 , 4 , 4 }
    for (int i = 0 ; i < masivs.length ; i++) {
      for (int j = 0 ; j < masivs.length ; j++) {
        // 1. Ja pārbauda to pašu elementu.
        if (j == i) {
          continue;
        }
        // 2. Pārbauda vai ir duplikāti.
        if (masivs[i].equals(masivs[j])) {
          return true;
        }
      }
    }
    return false;
  }

  public static String booleanVertiba(boolean bools) {
    // Atgriež pirmo burtu no vērtības, lai to varētu saglabāt failos.
    return (bools) ? "T" : "F"; // Tas pats, kas // if (bools) { return "T"; } else { return "F"; }
  }
  
  public static String atgriestProgresaLiniju(double tagadejaisSkaitlis, int lielakaisSkaitlis, int kolonnuSkaits, boolean raditProcentus) {
    String progLinija = "";
    // 1. Aprēķina skaitli, kurš apzīmēs 1 iedaļu.
    double vienaIedala = lielakaisSkaitlis * 1.0 / kolonnuSkaits;
    // 2. Pieliek 'baterijas iedaļas', kamēr tagadējais sk. nav mazāks par 1 iedaļu.
    if (tagadejaisSkaitlis <= lielakaisSkaitlis) {
      for (double i = tagadejaisSkaitlis ; i >= vienaIedala ; i -= vienaIedala) {
        progLinija += "▒";
      }
    }
    else {
      for (int i = 0; i < kolonnuSkaits; i++) {
        progLinija += "▒";
      }
    }
    int progressaGarums = progLinija.length(); // Lai noteiktu, cik tumšās līnijas pielikt pie progressa.
    // 3. Pievieno atlikušās kolonnas ar tumšu krāsu (lai vizuāli varētu redzēt, cik līdz beigām).
    progLinija += K.TPELEKS; // Nokrāso atlikuša progressa līniju pelēku.
    for (int i = progressaGarums + 1; i <= kolonnuSkaits ; i++)  {
      progLinija += "▒";
    }
    progLinija += K.RESET; // Atjauno progress līnijas krāsu.
    // 4. Pievieno procentus pirms progress līnijas.
    if (raditProcentus) {
      progLinija = (int) (tagadejaisSkaitlis / lielakaisSkaitlis * 100) + "% " + progLinija;
    }

    return progLinija;
  }

  public static String saliktAtstarpesSimboluVirkne(String vards, int atstarpes) {
    // Atgriež vārdu ar atstarpēm starp burtiem jeb simboliem.
    String atstarpe = "", apstradataVirkne = "";
    char[] virknesSimboli = vards.toCharArray();

    // 1. Izveido atstarpi pēc pieprasījuma.
    for (int i = atstarpes ; i > 0 ; i --) {
      atstarpe += " ";
    }

    // 2. Saliek atstarpes starp simboliem.
    for (char simbols : virknesSimboli) {
      apstradataVirkne += simbols + atstarpe;
    }

    // 3. Nogriež liekās atstarpes ' ' un atgriež rezultātu.
    return apstradataVirkne.strip();
  }

  public static String nonemtAtstarpes(String ievade) {
    int garums = ievade.length();
    String jaunaisVards = "";

    // Pārbauda visu vārdu.
    for (int i = 0 ; i < garums ; i++) {
      // Ja simbols nav atstarpe, tad ... .
      if (!(ievade.charAt(i) == ' ')) {
        jaunaisVards += ievade.charAt(i);
      }
    }

    return jaunaisVards;
  }

  public static void izvaditArrayListElementus(ArrayList<?> saraksts, boolean izvaditElementuSkaitu) {
    for (Object object : saraksts) {
      System.out.println(object);
    }

    if (izvaditElementuSkaitu) {
      System.out.println("--Elementu skaits: " + saraksts.size() + "--");
    }
  }

  public static boolean irSkaitlis(String ievade) {
    // * Pārbauda vai ievadi var pārveidot par skaitli.
    try {
      Integer.parseInt(ievade);
      return true;
    } 
    catch (Exception e) {
      return false;
    }
  }
  
  public static void gulet(int sekundes) {
    // * Metode atvieglo dzīvi visu laiku, kad man vajag izmantot
    // * Thread.sleep(); Vairs nevajag uztraukties par try_catch 
    // * rakstīšanu.
    try {
      Thread.sleep(sekundes * 1000);
    } catch (Exception e) {}
  }

  public static void masivuIzvade(String[] masivs) {
    // Izvada visus masīva elementus.
    for (int i = 0 ; i < masivs.length ; i++) {
      System.out.println(masivs[i] + "\033[0K");
    }
    System.out.println("\033[0J"); // Izdzēš visu tekstu līdz ekrāna beigām.
    System.out.print("\033[H"); // Noliek kursoru sākuma pozīcijā 0,0 jeb pirmās rindas pirmajā kolonnā.
  }
}