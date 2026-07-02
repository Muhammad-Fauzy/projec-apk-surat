import javax.swing.SwingUtilities;

import controller.SuratController;
import view.Dashboard;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                Dashboard dashboard = new Dashboard();

                new SuratController(dashboard);

                dashboard.setVisible(true);

            }
        }); 

    }

}