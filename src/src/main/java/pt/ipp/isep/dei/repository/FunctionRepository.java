package pt.ipp.isep.dei.repository;

import pt.ipp.isep.dei.domain.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FunctionRepository {
    private final List<Function> functions;

    public FunctionRepository() {
        functions = new ArrayList<>();
    }

    public Optional<Function> add(Function function) {
        if (function == null || functions.contains(function)) {
            return Optional.empty();
        }
        Function clone = function.clone();
        functions.add(clone);
        return Optional.of(clone);
    }

    public Optional<Function> getByDescription(String description) {
        for (Function f : functions) {
            if (f.getDescription().equalsIgnoreCase(description)) {
                return Optional.of(f.clone());
            }
        }
        return Optional.empty();
    }

    public List<Function> getAll() {
        return List.copyOf(functions);
    }
}
