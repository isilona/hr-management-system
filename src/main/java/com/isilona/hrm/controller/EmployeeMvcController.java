package com.isilona.hrm.controller;

import com.isilona.hrm.dao.entity.ContactType;
import com.isilona.hrm.dto.EmployeeDto;
import com.isilona.hrm.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
@RequestMapping(value = "employee")
public class EmployeeMvcController {

    private EmployeeService service;

    public EmployeeMvcController(EmployeeService service) {
        this.service = service;
    }

    // CREATE

    @GetMapping("/create")
    public String employeeCreateForm(Model model) {
        model.addAttribute("employee_create", new EmployeeDto());
        return "employeeCreate";
    }

    @PostMapping
    public String create(EmployeeDto input, UriComponentsBuilder ucb, HttpServletRequest request, Model model) {

        input.getContacts().get(0).setType(ContactType.TELEPHONE);
        EmployeeDto saved = service.create(input);

        model.addAttribute("employee_details_result", saved);

        String path = String.format("%s/{id}", request.getAttribute(
                HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE));
        return "redirect:" + ucb.path(path).buildAndExpand(saved.getUuid()).toUri();

    }

    // READ

    @GetMapping("/{uuid}")
    public String read(@PathVariable UUID uuid, Model model) {
        model.addAttribute("employee_details_result", service.read(uuid));
        return "employeeDetails";
    }

    @GetMapping("/list")
    public String readAll(Model model) {
        model.addAttribute("employee_list_result", service.readAll());
        return "employeeList";
    }
}
