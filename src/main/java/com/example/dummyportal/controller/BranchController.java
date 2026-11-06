package com.example.dummyportal.controller;

import com.example.dummyportal.dto.BranchRequest;
import com.example.dummyportal.entity.Branch;
import com.example.dummyportal.service.BranchService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping
    public ResponseEntity<Branch> create(@Valid @RequestBody BranchRequest req) {
        return ResponseEntity.ok(branchService.create(req));
    }

    @GetMapping
    public List<Branch> list() {
        return branchService.getAll();
    }
}
