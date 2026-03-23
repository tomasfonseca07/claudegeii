package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.ManageRegistrationController;
import pt.ipp.isep.dei.domain.RegistrationRequest;
import pt.ipp.isep.dei.ui.console.utils.Utils;

import java.util.List;

public class ManageRegistrationUI implements Runnable {

    private final ManageRegistrationController controller;

    public ManageRegistrationUI() {
        controller = new ManageRegistrationController();
    }

    @Override
    public void run() {
        System.out.println("\n\n--- MANAGE REGISTRATION REQUESTS ---");

        List<RegistrationRequest> pending = controller.getPendingRequests();
        if (pending.isEmpty()) {
            System.out.println("No pending registration requests.");
            return;
        }

        int index = Utils.showAndSelectIndex(pending, "Select a registration request to process:");
        if (index < 0) return;

        RegistrationRequest request = pending.get(index);
        System.out.println("\nSelected: " + request);

        List<String> actions = List.of("Accept", "Reject");
        int action = Utils.showAndSelectIndex(actions, "Choose action:");
        if (action < 0) return;

        if (action == 0) {
            String password = Utils.readLineFromConsole("Enter initial password for the user: ");
            boolean success = controller.acceptRequest(request, password);
            System.out.println(success ? "Request accepted. User registered successfully." : "Failed to accept request.");
        } else {
            boolean success = controller.rejectRequest(request);
            System.out.println(success ? "Request rejected." : "Failed to reject request.");
        }
    }
}
