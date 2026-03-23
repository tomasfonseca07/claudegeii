package pt.ipp.isep.dei.ui.console.menu;

import pt.ipp.isep.dei.ui.console.*;
import pt.ipp.isep.dei.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class EthicsCommitteeUI implements Runnable {

    public EthicsCommitteeUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Validate Declaration of Interests", new ValidateDeclarationUI()));
        options.add(new MenuItem("Consult Integrated Situation of Political Agent", new IntegratedSituationUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- ETHICS COMMITTEE MENU --------------");
            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
