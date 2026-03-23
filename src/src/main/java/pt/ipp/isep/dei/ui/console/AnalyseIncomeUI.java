package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.AnalyseIncomeController;
import pt.ipp.isep.dei.domain.DeclarationOfInterests;
import pt.ipp.isep.dei.ui.console.utils.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class AnalyseIncomeUI implements Runnable {

    private final AnalyseIncomeController controller;

    public AnalyseIncomeUI() {
        controller = new AnalyseIncomeController();
    }

    @Override
    public void run() {
        System.out.println("\n\n--- ANALYSE INCOME EVOLUTION ---");

        String agentEmail = Utils.readLineFromConsole("Enter Political Agent email: ");
        String fromStr = Utils.readLineFromConsole("Enter start date (YYYY-MM-DD): ");
        String toStr = Utils.readLineFromConsole("Enter end date (YYYY-MM-DD): ");

        LocalDate from;
        LocalDate to;
        try {
            from = LocalDate.parse(fromStr);
            to = LocalDate.parse(toStr);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Use YYYY-MM-DD.");
            return;
        }

        if (from.isAfter(to)) {
            System.out.println("Start date must be before end date.");
            return;
        }

        List<DeclarationOfInterests> declarations = controller.getDeclarationsBetweenDates(agentEmail, from, to);
        System.out.println("\nIncome evolution for " + agentEmail + " from " + from + " to " + to + ":");
        controller.printIncomeEvolution(declarations);
    }
}
