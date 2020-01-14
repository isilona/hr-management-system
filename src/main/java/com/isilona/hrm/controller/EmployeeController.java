package com.isilona.hrm.controller;

import com.isilona.hrm.dao.entity.Employee;
import com.isilona.hrm.dto.EmployeeDto;
import com.isilona.hrm.service.EmployeeService;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequestMapping(value = "/v1/employee")
public class EmployeeController {

    private EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    // CREATE

    @PostMapping
    public ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto input, UriComponentsBuilder ucb, HttpServletRequest request) {

        EmployeeDto saved = service.create(input);

        String path = String.format("%s/{id}", request.getAttribute(
                HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE));

        return ResponseEntity
                .created(ucb.path(path).buildAndExpand(saved.getUuid()).toUri())
                .body(saved);
    }

    // READ

    @GetMapping("/{uuid}")
    public ResponseEntity<EmployeeDto> read(@PathVariable UUID uuid) {
        try {
            return ResponseEntity
                    .ok()
                    .body(service.read(uuid));
        } catch (Exception e) {
            return ResponseEntity
                    .notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity readAll() {
        return ResponseEntity
                .ok()
                .body(service.readAll());
    }


    // UPDATE

    @PutMapping("/{uuid}")
    public ResponseEntity<EmployeeDto> update(@RequestBody EmployeeDto input, @PathVariable UUID uuid) {

        if (input.getUuid() != null && !input.getUuid().equals(uuid)) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT).build();
        }
        try {
            input.setUuid(uuid);
            return ResponseEntity
                    .ok().body(service.update(input));
        } catch (Exception e) {
            return ResponseEntity
                    .notFound().build();
        }

    }

    // DELETE

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        try {
            service.delete(uuid);
            return ResponseEntity
                    .noContent().build();
        } catch (Exception e) {
            return ResponseEntity
                    .notFound().build();
        }
    }

    @GetMapping("/filter")
    public ResponseEntity findFiltered(@QuerydslPredicate(root = Employee.class) Predicate predicate, Sort sort) {
        return ResponseEntity
                .ok()
                .body(service.findFiltered(predicate, sort));
    }
}
