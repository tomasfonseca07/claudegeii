package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.IntegratedSituationController;
import pt.ipp.isep.dei.domain.DeclarationOfInterests;
import pt.ipp.isep.dei.ui.console.utils.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class IntegratedSituationUI implements Runnable {

    private final IntegratedSituationController controller;

    public IntegratedSituationUI() {
        controller = new IntegratedSituationController();
    }

    @Override
    public void run() {
        System.out.println("\n\n--- INTEGRATED SITUATION OF POLITICAL AGENT ---");

        String agentEmail = Utils.readLineFromConsole("Enter Political Agent email: ");
        String dateStr = Utils.readLineFromConsole("Enter reference date (YYYY-MM-DD): ");

        LocalDate date;
        try {
            date = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Use YYYY-MM-DD.");
            return;
        }

        Optional<DeclarationOfInterests> result = controller.getIntegratedSituation(agentEmail, date);

        if (result.isEmpty()) {
            System.out.println("No validated declaration found for " + agentEmail + " on " + date);
            return;
        }

        DeclarationOfInterests d = result.get();
        System.out.println("\n========== INTEGRATED SITUATION ==========");
        System.out.println("Agent: " + agentEmail);
        System.out.println("Reference Date: " + date);
        System.out.println("Last Valid Declaration: " + d.getSubmissionDate());
        System.out.println("\n--- Positions ---");
        d.getPositions().forEach(p -> System.out.println("  " + p));
        System.out.println("\n--- Assets (Total: €" + String.format("%.2f", d.getTotalAssetValue()) + ") ---");
        d.getAssets().forEach(a -> System.out.println("  " + a));
        System.out.println("\n--- Income (Total: €" + String.format("%.2f", d.getTotalIncome()) + ") ---");
        d.getIncomeEntries().forEach(i -> System.out.println("  " + i));
        System.out.println("\n--- Business Participations ---");
        d.getBusinessParticipations().forEach(bp -> System.out.println("  " + bp));
        System.out.println("==========================================");
    }
}
