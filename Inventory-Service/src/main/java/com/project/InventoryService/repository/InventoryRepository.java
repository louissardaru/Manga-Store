package com.project.InventoryService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.InventoryService.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	Optional<Inventory> findBySkuCode(String skuCode);

}
