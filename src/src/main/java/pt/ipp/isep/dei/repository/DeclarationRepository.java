package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.DeclarationOfInterests;
import pt.ipp.isep.dei.domain.DeclarationStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DeclarationRepository {
    private final List<DeclarationOfInterests> declarations;

    public DeclarationRepository() {
        declarations = new ArrayList<>();
    }

    public Optional<DeclarationOfInterests> add(DeclarationOfInterests declaration) {
        if (declaration == null || declarations.contains(declaration)) {
            return Optional.empty();
        }
        declarations.add(declaration);
        return Optional.of(declaration);
    }

    public Optional<DeclarationOfInterests> getById(int id) {
        for (DeclarationOfInterests d : declarations) {
            if (d.getId() == id) {
                return Optional.of(d);
            }
        }
        return Optional.empty();
    }

    public List<DeclarationOfInterests> getByAgentEmail(String email) {
        return declarations.stream()
                .filter(d -> d.getPoliticalAgentEmail().equalsIgnoreCase(email))
                .collect(Collectors.toList());
    }

    public List<DeclarationOfInterests> getByAgentEmailAndStatus(String email, DeclarationStatus status) {
        return declarations.stream()
                .filter(d -> d.getPoliticalAgentEmail().equalsIgnoreCase(email) && d.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<DeclarationOfInterests> getPendingDeclarations() {
        return declarations.stream()
                .filter(d -> d.getStatus() == DeclarationStatus.PENDING)
                .collect(Collectors.toList());
    }

    public List<DeclarationOfInterests> getByAgentEmailBetweenDates(String email, LocalDate from, LocalDate to) {
        return declarations.stream()
                .filter(d -> d.getPoliticalAgentEmail().equalsIgnoreCase(email)
                        && !d.getSubmissionDate().isBefore(from)
                        && !d.getSubmissionDate().isAfter(to))
                .collect(Collectors.toList());
    }

    public Optional<DeclarationOfInterests> getByAgentEmailOnDate(String email, LocalDate date) {
        return declarations.stream()
                .filter(d -> d.getPoliticalAgentEmail().equalsIgnoreCase(email)
                        && !d.getSubmissionDate().isAfter(date)
                        && d.getStatus() == DeclarationStatus.VALIDATED)
                .max((a, b) -> a.getSubmissionDate().compareTo(b.getSubmissionDate()));
    }

    public List<DeclarationOfInterests> getAll() {
        return List.copyOf(declarations);
    }
}
