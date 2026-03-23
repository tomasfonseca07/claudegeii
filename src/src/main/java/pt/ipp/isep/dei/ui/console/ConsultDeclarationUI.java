package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.ConsultDeclarationController;
import pt.ipp.isep.dei.domain.DeclarationOfInterests;
import pt.ipp.isep.dei.ui.console.utils.Utils;

import java.util.List;
import java.util.Optional;

public class ConsultDeclarationUI implements Runnable {

    private final ConsultDeclarationController controller;

    public ConsultDeclarationUI() {
        controller = new ConsultDeclarationController();
    }

    @Override
    public void run() {
        System.out.println("\n\n--- MY DECLARATIONS ---");

        List<DeclarationOfInterests> declarations = controller.getMyDeclarations();
        if (declarations.isEmpty()) {
            System.out.println("You have no submitted declarations.");
            return;
        }

        int index = Utils.showAndSelectIndex(declarations, "Select a declaration to view:");
        if (index < 0) return;

        DeclarationOfInterests declaration = declarations.get(index);
        printDeclarationDetails(declaration);
    }

    private void printDeclarationDetails(DeclarationOfInterests d) {
        System.out.println("\n========== DECLARATION DETAILS ==========");
        System.out.println(d);
        System.out.println("\n--- Positions ---");
        if (d.getPositions().isEmpty()) System.out.println("  None");
        d.getPositions().forEach(p -> System.out.println("  " + p));

        System.out.println("\n--- Real Estate Assets ---");
        if (d.getAssets().isEmpty()) System.out.println("  None");
        d.getAssets().forEach(a -> System.out.println("  " + a));

        System.out.println("\n--- Income ---");
        if (d.getIncomeEntries().isEmpty()) System.out.println("  None");
        d.getIncomeEntries().forEach(i -> System.out.println("  " + i));
        System.out.printf("  Total Income: €%.2f%n", d.getTotalIncome());

        System.out.println("\n--- Business Participations ---");
        if (d.getBusinessParticipations().isEmpty()) System.out.println("  None");
        d.getBusinessParticipations().forEach(bp -> System.out.println("  " + bp));

        if (!d.getValidationComment().isBlank()) {
            System.out.println("\n--- Validation Comment ---");
            System.out.println("  " + d.getValidationComment());
        }
        System.out.println("==========================================");
    }
}
