package com.staunch.tech.repository;

import com.staunch.tech.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {

    List<Ticket> findByEmployeeId(int employeeId);
    Optional<Ticket> findByUuid(String uuid);
    Optional<Ticket> findByFileName(String fileName);
    List<Ticket> findByStatus(String status);
}
