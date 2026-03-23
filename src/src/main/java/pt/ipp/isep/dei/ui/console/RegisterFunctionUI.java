package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.RegisterFunctionController;
import pt.ipp.isep.dei.domain.Function;
import pt.ipp.isep.dei.ui.console.utils.Utils;

import java.util.Optional;

public class RegisterFunctionUI implements Runnable {

    private final RegisterFunctionController controller;

    public RegisterFunctionUI() {
        controller = new RegisterFunctionController();
    }

    @Override
    public void run() {
        System.out.println("\n\n--- REGISTER FUNCTION ---");

        String description = Utils.readLineFromConsole("Enter function description: ");
        Optional<Function> result = controller.registerFunction(description);

        if (result.isPresent()) {
            System.out.println("\nFunction registered successfully: " + result.get());
        } else {
            System.out.println("\nFailed to register function. It may already exist.");
        }
    }
}
