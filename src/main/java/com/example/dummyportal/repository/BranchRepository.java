package com.example.dummyportal.repository;

import com.example.dummyportal.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    boolean existsByCode(String code);
    boolean existsByName(String name);
}
