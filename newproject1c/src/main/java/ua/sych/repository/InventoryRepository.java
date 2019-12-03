package ua.sych.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.sych.entity.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
