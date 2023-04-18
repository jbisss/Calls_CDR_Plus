package ru.learnup.nexigntask.callscdrplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.learnup.nexigntask.callscdrplus.entity.Client;

public interface RomashkaRepository extends JpaRepository<Client, Long> {
}
