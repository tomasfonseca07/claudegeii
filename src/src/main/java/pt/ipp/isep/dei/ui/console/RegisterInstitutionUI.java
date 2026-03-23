package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.RegisterInstitutionController;
import pt.ipp.isep.dei.domain.Institution;
import pt.ipp.isep.dei.domain.InstitutionType;
import pt.ipp.isep.dei.ui.console.utils.Utils;

import java.util.List;
import java.util.Optional;

public class RegisterInstitutionUI implements Runnable {

    private final RegisterInstitutionController controller;

    public RegisterInstitutionUI() {
        controller = new RegisterInstitutionController();
    }

    @Override
    public void run() {
        System.out.println("\n\n--- REGISTER INSTITUTION ---");

        List<InstitutionType> types = controller.getAvailableTypes();
        int typeIndex = Utils.showAndSelectIndex(types, "Select institution type:");
        if (typeIndex < 0) {
            System.out.println("Operation cancelled.");
            return;
        }

        InstitutionType type = types.get(typeIndex);
        String name = Utils.readLineFromConsole("Enter institution name: ");

        Optional<Institution> result = controller.registerInstitution(name, type);

        if (result.isPresent()) {
            System.out.println("\nInstitution registered successfully: " + result.get());
        } else {
            System.out.println("\nFailed to register institution. It may already exist.");
        }
    }
}
