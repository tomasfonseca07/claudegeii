package pt.ipp.isep.dei.ui.console.menu;

import pt.ipp.isep.dei.ui.console.*;
import pt.ipp.isep.dei.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class PoliticalAgentUI implements Runnable {

    public PoliticalAgentUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Submit Declaration of Interests", new SubmitDeclarationUI()));
        options.add(new MenuItem("Consult My Declarations", new ConsultDeclarationUI()));
        options.add(new MenuItem("List Institutions", new ListInstitutionsUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- POLITICAL AGENT MENU ---------------");
            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
