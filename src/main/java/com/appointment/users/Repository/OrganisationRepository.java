package com.appointment.users.Repository;
import com.appointment.users.entity.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation, Long> {

    // Optional: find by name
    Organisation findByName(String name);
}
