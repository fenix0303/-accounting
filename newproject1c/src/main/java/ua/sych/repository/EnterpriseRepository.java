package ua.sych.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.sych.entity.Enterprise;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
}
