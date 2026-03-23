package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.RegisterUserController;
import pt.ipp.isep.dei.domain.RegistrationRequest;
import pt.ipp.isep.dei.ui.console.utils.Utils;

import java.util.List;
import java.util.Optional;

public class RegisterUserUI implements Runnable {

    private final RegisterUserController controller;

    public RegisterUserUI() {
        controller = new RegisterUserController();
    }

    @Override
    public void run() {
        System.out.println("\n\n--- REGISTRATION REQUEST ---");

        String name = Utils.readLineFromConsole("Enter your full name: ");
        String email = Utils.readLineFromConsole("Enter your email: ");

        List<String> roles = controller.getAvailableRoles();
        int roleIndex = Utils.showAndSelectIndex(roles, "Select the role you want to request:");
        if (roleIndex < 0) {
            System.out.println("Registration cancelled.");
            return;
        }

        String role = roles.get(roleIndex);
        Optional<RegistrationRequest> result = controller.registerUser(email, name, role);

        if (result.isPresent()) {
            System.out.println("\nRegistration request submitted successfully!");
            System.out.println("Your request will be reviewed by an Administrator.");
            System.out.println(result.get());
        } else {
            System.out.println("\nFailed to submit registration. A request for this email may already exist.");
        }
    }
}
