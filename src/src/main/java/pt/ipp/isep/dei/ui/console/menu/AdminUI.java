package pt.ipp.isep.dei.ui.console.menu;

import pt.ipp.isep.dei.ui.console.*;
import pt.ipp.isep.dei.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class AdminUI implements Runnable {

    public AdminUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Manage Registration Requests", new ManageRegistrationUI()));
        options.add(new MenuItem("Register Institution", new RegisterInstitutionUI()));
        options.add(new MenuItem("Register Function", new RegisterFunctionUI()));
        options.add(new MenuItem("List Institutions", new ListInstitutionsUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- ADMINISTRATOR MENU -----------------");
            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
