package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.MakeComplaintController;
import pt.ipp.isep.dei.domain.Complaint;
import pt.ipp.isep.dei.ui.console.utils.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class MakeComplaintUI implements Runnable {

    private final MakeComplaintController controller;

    public MakeComplaintUI() {
        controller = new MakeComplaintController();
    }

    @Override
    public void run() {
        System.out.println("\n\n--- MAKE COMPLAINT ---");

        String agentEmail = Utils.readLineFromConsole("Enter Political Agent email: ");
        String politicalRole = Utils.readLineFromConsole("Enter the Political Agent's role (e.g. Deputy, Minister): ");
        String dateStr = Utils.readLineFromConsole("Enter reference date (YYYY-MM-DD): ");

        LocalDate date;
        try {
            date = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Use YYYY-MM-DD.");
            return;
        }

        String description = Utils.readLineFromConsole("Describe your complaint: ");

        Optional<Complaint> result = controller.makeComplaint(agentEmail, politicalRole, date, description);

        if (result.isPresent()) {
            System.out.println("\nComplaint submitted successfully!");
            System.out.println(result.get());
        } else {
            System.out.println("\nFailed to submit complaint.");
        }
    }
}
