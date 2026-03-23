package pt.ipp.isep.dei.ui.console;

import pt.ipp.isep.dei.controller.SubmitDeclarationController;
import pt.ipp.isep.dei.domain.*;
import pt.ipp.isep.dei.ui.console.utils.Utils;

import java.util.List;
import java.util.Optional;

public class SubmitDeclarationUI implements Runnable {

    private final SubmitDeclarationController controller;

    public SubmitDeclarationUI() {
        controller = new SubmitDeclarationController();
    }

    @Override
    public void run() {
        System.out.println("\n\n--- SUBMIT DECLARATION OF INTERESTS ---");

        List<DeclarationType> types = controller.getAvailableDeclarationTypes();
        int typeIndex = Utils.showAndSelectIndex(types, "Select declaration type:");
        if (typeIndex < 0) {
            System.out.println("Operation cancelled.");
            return;
        }

        Optional<DeclarationOfInterests> declOpt = controller.createDeclaration(types.get(typeIndex));
        if (declOpt.isEmpty()) {
            System.out.println("Failed to create declaration.");
            return;
        }

        DeclarationOfInterests declaration = declOpt.get();

        addPositions(declaration);
        addAssets(declaration);
        addIncome(declaration);
        addBusinessParticipations(declaration);

        System.out.println("\nDeclaration submitted successfully!");
        System.out.println(declaration);
    }

    private void addPositions(DeclarationOfInterests declaration) {
        System.out.println("\n--- Positions/Jobs ---");
        while (Utils.confirm("Add a position? (s/n)")) {
            String institution = Utils.readLineFromConsole("Institution name: ");
            String function = Utils.readLineFromConsole("Function/Role: ");
            List<PositionType> posTypes = controller.getAvailablePositionTypes();
            int posTypeIndex = Utils.showAndSelectIndex(posTypes, "Select position type:");
            if (posTypeIndex < 0) break;
            double payment = Utils.readDoubleFromConsole("Payment (€): ");
            controller.addPosition(declaration, institution, function, posTypes.get(posTypeIndex), payment);
        }
    }

    private void addAssets(DeclarationOfInterests declaration) {
        System.out.println("\n--- Real Estate Assets ---");
        while (Utils.confirm("Add a real estate asset? (s/n)")) {
            String description = Utils.readLineFromConsole("Asset description: ");
            List<AssetType> assetTypes = controller.getAvailableAssetTypes();
            int assetTypeIndex = Utils.showAndSelectIndex(assetTypes, "Select asset type:");
            if (assetTypeIndex < 0) break;
            double value = Utils.readDoubleFromConsole("Estimated value (€): ");
            controller.addAsset(declaration, description, assetTypes.get(assetTypeIndex), value);
        }
    }

    private void addIncome(DeclarationOfInterests declaration) {
        System.out.println("\n--- Income ---");
        while (Utils.confirm("Add an income entry? (s/n)")) {
            String source = Utils.readLineFromConsole("Income source: ");
            double amount = Utils.readDoubleFromConsole("Amount (€): ");
            String desc = Utils.readLineFromConsole("Description (optional): ");
            controller.addIncome(declaration, source, amount, desc);
        }
    }

    private void addBusinessParticipations(DeclarationOfInterests declaration) {
        System.out.println("\n--- Business Participations ---");
        while (Utils.confirm("Add a business participation? (s/n)")) {
            String company = Utils.readLineFromConsole("Company name: ");
            String participationType = Utils.readLineFromConsole("Participation type (quota/shares/holdings): ");
            double value = Utils.readDoubleFromConsole("Market value (€): ");
            controller.addBusinessParticipation(declaration, company, participationType, value);
        }
    }
}
