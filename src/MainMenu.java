import java.util.*;
import java.io.*;
public class MainMenu {
    Scanner input = new Scanner(System.in);
    FilAdministering fil = new FilAdministering();
    MenuSystem ms=new MenuSystem();
    boolean run = true;

void Menu()throws IOException{


        while (run) {

            switch (ms.loginMenu(input)) {

                case Rolle.FORMAND: run=ms.formandMenu(input);
                break;

                case Rolle.TRAENER: run=ms.traenerMenu(input);
                break;

                case Rolle.KASSERER: run=ms.kassererMenu(input);
                break;

            }
        }
    }

    public static void main(String[] args)throws IOException {
    MainMenu m=new MainMenu();
        m.Menu();

    }
}


