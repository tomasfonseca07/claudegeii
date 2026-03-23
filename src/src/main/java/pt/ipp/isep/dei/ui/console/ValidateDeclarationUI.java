package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.ValidateDeclarationController;
import pt.ipp.isep.dei.domain.DeclarationOfInterests;
import pt.ipp.isep.dei.ui.console.utils.Utils;

import java.util.List;

public class ValidateDeclarationUI implements Runnable {

    private final ValidateDeclarationController controller;

    public ValidateDeclarationUI() {
        controller = new ValidateDeclarationController();
    }

    @Override
    public void run() {
        System.out.println("\n\n--- VALIDATE DECLARATION OF INTERESTS ---");

        List<DeclarationOfInterests> pending = controller.getPendingDeclarations();
        if (pending.isEmpty()) {
            System.out.println("No pending declarations to validate.");
            return;
        }

        int index = Utils.showAndSelectIndex(pending, "Select a declaration to validate:");
        if (index < 0) return;

        DeclarationOfInterests declaration = pending.get(index);
        System.out.println("\nDeclaration: " + declaration);
        System.out.println("\nPositions: " + declaration.getPositions().size());
        System.out.println("Assets: " + declaration.getAssets().size());
        System.out.printf("Total Income: €%.2f%n", declaration.getTotalIncome());
        System.out.println("Business Participations: " + declaration.getBusinessParticipations().size());

        List<String> actions = List.of("Validate (correct)", "Reject (with comment)");
        int action = Utils.showAndSelectIndex(actions, "Choose action:");
        if (action < 0) return;

        if (action == 0) {
            boolean success = controller.validateDeclaration(declaration);
            System.out.println(success ? "Declaration validated successfully." : "Failed to validate declaration.");
        } else {
            String comment = Utils.readLineFromConsole("Enter comment describing the inconsistency: ");
            boolean success = controller.rejectDeclaration(declaration, comment);
            System.out.println(success ? "Declaration rejected with comment." : "Failed to reject declaration.");
        }
    }
}
