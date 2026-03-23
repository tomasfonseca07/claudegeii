package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.DeclarationOfInterests;
import pt.ipp.isep.dei.repository.DeclarationRepository;
import pt.ipp.isep.dei.repository.Repositories;

import java.time.LocalDate;
import java.util.Optional;

/**
 * US09 - As a member of the Ethics Committee, I want to consult the Integrated Situation
 * of a Political Agent on a given date.
 */
public class IntegratedSituationController {

    private final DeclarationRepository declarationRepository;

    public IntegratedSituationController() {
        this.declarationRepository = Repositories.getInstance().getDeclarationRepository();
    }

    public IntegratedSituationController(DeclarationRepository declarationRepository) {
        this.declarationRepository = declarationRepository;
    }

    public Optional<DeclarationOfInterests> getIntegratedSituation(String agentEmail, LocalDate date) {
        return declarationRepository.getByAgentEmailOnDate(agentEmail, date);
    }
}
