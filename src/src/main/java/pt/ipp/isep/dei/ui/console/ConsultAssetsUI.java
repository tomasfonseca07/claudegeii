package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.ConsultAssetsController;
import pt.ipp.isep.dei.domain.DeclarationOfInterests;
import pt.ipp.isep.dei.ui.console.utils.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class ConsultAssetsUI implements Runnable {

    private final ConsultAssetsController controller;

    public ConsultAssetsUI() {
        controller = new ConsultAssetsController();
    }

    @Override
    public void run() {
        System.out.println("\n\n--- CONSULT ASSETS ---");

        String agentEmail = Utils.readLineFromConsole("Enter Political Agent email: ");
        String dateStr = Utils.readLineFromConsole("Enter reference date (YYYY-MM-DD): ");

        LocalDate date;
        try {
            date = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Use YYYY-MM-DD.");
            return;
        }

        Optional<DeclarationOfInterests> result = controller.getAssetsOnDate(agentEmail, date);

        if (result.isEmpty()) {
            System.out.println("No validated declaration found for " + agentEmail + " on " + date);
            return;
        }

        boolean isJournalist = controller.currentUserIsJournalist();
        System.out.println("\nAssets of " + agentEmail + " on " + date + ":");
        controller.printAssets(result.get().getAssets(), isJournalist);
        System.out.printf("Total Asset Value: €%.2f%n", result.get().getTotalAssetValue());
    }
}
