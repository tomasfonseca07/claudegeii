package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.Complaint;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ComplaintRepository {
    private final List<Complaint> complaints;

    public ComplaintRepository() {
        complaints = new ArrayList<>();
    }

    public Optional<Complaint> add(Complaint complaint) {
        if (complaint == null) {
            return Optional.empty();
        }
        complaints.add(complaint);
        return Optional.of(complaint);
    }

    public List<Complaint> getByAgentEmail(String email) {
        return complaints.stream()
                .filter(c -> c.getPoliticalAgentEmail().equalsIgnoreCase(email))
                .collect(Collectors.toList());
    }

    public List<Complaint> getByCitizenEmail(String email) {
        return complaints.stream()
                .filter(c -> c.getCitizenEmail().equalsIgnoreCase(email))
                .collect(Collectors.toList());
    }

    public List<Complaint> getAll() {
        return List.copyOf(complaints);
    }
}
