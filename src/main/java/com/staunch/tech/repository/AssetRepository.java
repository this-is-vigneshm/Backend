package com.staunch.tech.repository;

import com.staunch.tech.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Integer> {
    /**
     * 
     * @param location
     * @return
     */
	Optional<Asset> findByFileName(String fileName);
    List<Asset> findByLocation(String location);
}
