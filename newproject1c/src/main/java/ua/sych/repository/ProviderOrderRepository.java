package ua.sych.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.sych.entity.ProviderOrder;

@Repository
public interface ProviderOrderRepository extends JpaRepository<ProviderOrder, Long> {
}
