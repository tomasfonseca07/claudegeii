package pt.ipp.isep.dei;

import pt.ipp.isep.dei.controller.AuthenticationController;
import pt.ipp.isep.dei.domain.Institution;
import pt.ipp.isep.dei.domain.InstitutionType;
import pt.ipp.isep.dei.domain.Function;
import pt.ipp.isep.dei.repository.AuthenticationRepository;
import pt.ipp.isep.dei.repository.InstitutionRepository;
import pt.ipp.isep.dei.repository.FunctionRepository;
import pt.ipp.isep.dei.repository.Repositories;

public class Bootstrap implements Runnable {

    public void run() {
        addRoles();
        addUsers();
        addInstitutions();
        addFunctions();
    }

    private void addRoles() {
        AuthenticationRepository auth = Repositories.getInstance().getAuthenticationRepository();
        auth.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        auth.addUserRole(AuthenticationController.ROLE_POLITICAL_AGENT, AuthenticationController.ROLE_POLITICAL_AGENT);
        auth.addUserRole(AuthenticationController.ROLE_ETHICS_COMMITTEE, AuthenticationController.ROLE_ETHICS_COMMITTEE);
        auth.addUserRole(AuthenticationController.ROLE_JOURNALIST, AuthenticationController.ROLE_JOURNALIST);
        auth.addUserRole(AuthenticationController.ROLE_CITIZEN, AuthenticationController.ROLE_CITIZEN);
    }

    private void addUsers() {
        AuthenticationRepository auth = Repositories.getInstance().getAuthenticationRepository();
        auth.addUserWithRole("System Admin", "admin@crystalclear.pt", "Admin1A2", AuthenticationController.ROLE_ADMIN);
        auth.addUserWithRole("Maria Costa", "maria@gov.pt", "Agent1A2", AuthenticationController.ROLE_POLITICAL_AGENT);
        auth.addUserWithRole("Ana Ferreira", "ana@ethics.pt", "Ethic1A2", AuthenticationController.ROLE_ETHICS_COMMITTEE);
        auth.addUserWithRole("João Silva", "joao@press.pt", "Press1A2", AuthenticationController.ROLE_JOURNALIST);
        auth.addUserWithRole("Carlos Nunes", "carlos@citizen.pt", "Citiz1A2", AuthenticationController.ROLE_CITIZEN);
    }

    private void addInstitutions() {
        InstitutionRepository repo = Repositories.getInstance().getInstitutionRepository();
        repo.add(new Institution("Assembleia da República", InstitutionType.POLITICAL_PARTY));
        repo.add(new Institution("Partido Socialista", InstitutionType.POLITICAL_PARTY));
        repo.add(new Institution("Partido Social Democrata", InstitutionType.POLITICAL_PARTY));
        repo.add(new Institution("EDP", InstitutionType.COMPANY));
        repo.add(new Institution("Galp Energia", InstitutionType.COMPANY));
        repo.add(new Institution("Fundação Champalimaud", InstitutionType.FOUNDATION));
        repo.add(new Institution("Instituto Superior Técnico", InstitutionType.INSTITUTE));
        repo.add(new Institution("Associação Portuguesa de Imprensa", InstitutionType.ASSOCIATION));
    }

    private void addFunctions() {
        FunctionRepository repo = Repositories.getInstance().getFunctionRepository();
        repo.add(new Function("Deputy"));
        repo.add(new Function("Minister"));
        repo.add(new Function("Mayor"));
        repo.add(new Function("Councillor"));
        repo.add(new Function("President"));
        repo.add(new Function("Board Member"));
        repo.add(new Function("Consultant"));
    }
}
