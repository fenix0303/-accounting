package ua.sych.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.sych.entity.Partner;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
}
