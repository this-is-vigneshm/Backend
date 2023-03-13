package com.staunch.tech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.staunch.tech.entity.LookUps;

@Repository
public interface LookUpsRepository extends JpaRepository<LookUps, Integer> {

}
