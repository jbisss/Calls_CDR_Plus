package ru.learnup.nexigntask.callscdrplus.service;

import org.springframework.stereotype.Service;
import ru.learnup.nexigntask.callscdrplus.repository.RomashkaRepository;

import java.util.Set;

@Service
public class RomashkaService {

    private final RomashkaRepository repository;

    public RomashkaService(RomashkaRepository repository) {
        this.repository = repository;
    }

    public Set<String> getNumbers() {
        return repository.findAllNumbers();
    }
}
