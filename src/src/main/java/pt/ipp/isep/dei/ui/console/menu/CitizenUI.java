package pt.ipp.isep.dei.ui.console.menu;

import pt.ipp.isep.dei.ui.console.*;
import pt.ipp.isep.dei.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class CitizenUI implements Runnable {

    public CitizenUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Make Complaint about Political Agent", new MakeComplaintUI()));
        options.add(new MenuItem("Consult Assets of Political Agent", new ConsultAssetsUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- CITIZEN MENU -----------------------");
            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
