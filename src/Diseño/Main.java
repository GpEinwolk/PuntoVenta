package Diseño;

/**
 *
 * @author Alejandro
 */
public class Main {
    public static void main(String[] args) {
        InicioSesion login = new InicioSesion();
     Splash splash = new Splash();  
          splash.setVisible(true);
        try {
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(10);
                splash.carga.setText(Integer.toString(i)+"%");
               
                   if (i == 100) {
                    splash.setVisible(false);
                    login.setVisible(true);
                }
            }
  
          
        } catch (Exception e) {
        }
    }
}
