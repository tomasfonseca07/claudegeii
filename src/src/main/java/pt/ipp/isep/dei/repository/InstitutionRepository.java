package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.Institution;
import pt.ipp.isep.dei.domain.InstitutionType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InstitutionRepository {
    private final List<Institution> institutions;

    public InstitutionRepository() {
        institutions = new ArrayList<>();
    }

    public Optional<Institution> add(Institution institution) {
        if (institution == null || institutions.contains(institution)) {
            return Optional.empty();
        }
        Institution clone = institution.clone();
        institutions.add(clone);
        return Optional.of(clone);
    }

    public Optional<Institution> getByNameAndType(String name, InstitutionType type) {
        for (Institution i : institutions) {
            if (i.getName().equalsIgnoreCase(name) && i.getType() == type) {
                return Optional.of(i.clone());
            }
        }
        return Optional.empty();
    }

    public List<Institution> getAll() {
        return List.copyOf(institutions);
    }

    public List<Institution> getAllSortedByTypeAndName() {
        return institutions.stream()
                .sorted(Comparator.comparing((Institution i) -> i.getType().getDescription())
                        .thenComparing(Institution::getName, String.CASE_INSENSITIVE_ORDER))
                .map(Institution::clone)
                .collect(Collectors.toList());
    }

    public List<Institution> getByType(InstitutionType type) {
        return institutions.stream()
                .filter(i -> i.getType() == type)
                .sorted(Comparator.comparing(Institution::getName, String.CASE_INSENSITIVE_ORDER))
                .map(Institution::clone)
                .collect(Collectors.toList());
    }
}
