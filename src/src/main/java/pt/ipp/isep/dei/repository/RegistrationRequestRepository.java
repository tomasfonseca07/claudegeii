package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.RegistrationRequest;
import pt.ipp.isep.dei.domain.RegistrationStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RegistrationRequestRepository {
    private final List<RegistrationRequest> requests;

    public RegistrationRequestRepository() {
        requests = new ArrayList<>();
    }

    public Optional<RegistrationRequest> add(RegistrationRequest request) {
        if (request == null || requests.contains(request)) {
            return Optional.empty();
        }
        requests.add(request);
        return Optional.of(request);
    }

    public Optional<RegistrationRequest> getByEmail(String email) {
        for (RegistrationRequest r : requests) {
            if (r.getEmail().equalsIgnoreCase(email)) {
                return Optional.of(r);
            }
        }
        return Optional.empty();
    }

    public List<RegistrationRequest> getPendingRequests() {
        return requests.stream()
                .filter(r -> r.getStatus() == RegistrationStatus.PENDING)
                .collect(Collectors.toList());
    }

    public List<RegistrationRequest> getAll() {
        return List.copyOf(requests);
    }
}
