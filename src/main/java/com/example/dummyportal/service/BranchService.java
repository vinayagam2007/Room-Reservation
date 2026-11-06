package com.example.dummyportal.service;

import com.example.dummyportal.dto.BranchRequest;
import com.example.dummyportal.entity.Branch;
import com.example.dummyportal.repository.BranchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public Branch create(BranchRequest req) {
        String code = req.getCode().trim().toUpperCase();
        String name = req.getName().trim();
        if (branchRepository.existsByCode(code)) {
            throw new IllegalArgumentException("Branch code already exists");
        }
        if (branchRepository.existsByName(name)) {
            throw new IllegalArgumentException("Branch name already exists");
        }
        Branch branch = new Branch();
        branch.setName(name);
        branch.setCode(code);
        return branchRepository.save(branch);
    }

    public List<Branch> getAll() {
        return branchRepository.findAll();
    }
}
