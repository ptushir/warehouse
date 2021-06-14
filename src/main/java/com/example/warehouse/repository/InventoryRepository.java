package com.example.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.warehouse.entity.Inventory;

@Repository("inventoryRepository")
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
