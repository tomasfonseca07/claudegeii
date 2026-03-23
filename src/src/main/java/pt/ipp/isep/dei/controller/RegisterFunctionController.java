package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.Function;
import pt.ipp.isep.dei.repository.FunctionRepository;
import pt.ipp.isep.dei.repository.Repositories;

import java.util.List;
import java.util.Optional;

/**
 * US05 - As an Administrator, I want to register Functions.
 */
public class RegisterFunctionController {

    private final FunctionRepository functionRepository;

    public RegisterFunctionController() {
        this.functionRepository = Repositories.getInstance().getFunctionRepository();
    }

    public RegisterFunctionController(FunctionRepository functionRepository) {
        this.functionRepository = functionRepository;
    }

    public Optional<Function> registerFunction(String description) {
        Function function = new Function(description);
        return functionRepository.add(function);
    }

    public List<Function> getAllFunctions() {
        return functionRepository.getAll();
    }
}
