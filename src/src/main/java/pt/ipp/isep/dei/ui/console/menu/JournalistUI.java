package pt.ipp.isep.dei.ui.console.menu;

import pt.ipp.isep.dei.ui.console.*;
import pt.ipp.isep.dei.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class JournalistUI implements Runnable {

    public JournalistUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Analyse Income Evolution of Political Agent", new AnalyseIncomeUI()));
        options.add(new MenuItem("Consult Assets of Political Agent", new ConsultAssetsUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- JOURNALIST MENU --------------------");
            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
