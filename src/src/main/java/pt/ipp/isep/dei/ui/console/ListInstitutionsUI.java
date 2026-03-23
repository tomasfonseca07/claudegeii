package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.ListInstitutionsController;
import pt.ipp.isep.dei.domain.Institution;
import pt.ipp.isep.dei.domain.InstitutionType;

import java.util.List;

public class ListInstitutionsUI implements Runnable {

    private final ListInstitutionsController controller;

    public ListInstitutionsUI() {
        controller = new ListInstitutionsController();
    }

    @Override
    public void run() {
        System.out.println("\n\n--- INSTITUTIONS ---");

        List<Institution> institutions = controller.getInstitutionsSortedByTypeAndName();
        if (institutions.isEmpty()) {
            System.out.println("No institutions registered.");
            return;
        }

        String currentType = null;
        for (Institution institution : institutions) {
            String type = institution.getType().getDescription();
            if (!type.equals(currentType)) {
                currentType = type;
                System.out.println("\n[ " + currentType + " ]");
            }
            System.out.println("  - " + institution.getName());
        }
        System.out.println();
    }
}
