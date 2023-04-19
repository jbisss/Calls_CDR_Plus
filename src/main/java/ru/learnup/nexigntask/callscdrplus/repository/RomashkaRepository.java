package ru.learnup.nexigntask.callscdrplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.learnup.nexigntask.callscdrplus.entity.Client;

import java.util.Set;

public interface RomashkaRepository extends JpaRepository<Client, Long> {

    @Query( "SELECT c.phoneNumber FROM Client c" )
    Set<String> findAllNumbers();
}
