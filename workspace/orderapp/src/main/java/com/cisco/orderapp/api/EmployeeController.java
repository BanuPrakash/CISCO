package com.cisco.orderapp.api;

import com.cisco.orderapp.dto.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    Employee employee = new Employee();
    public EmployeeController() {
        employee.setId(123);
        employee.setTitle("Sr.Programmer");
       var personal = new HashMap<String, String>();
       personal.put("firstName", "Smitha");
       personal.put("lastName", "Patil");
       personal.put("phone", "1234567890");
       employee.setPersonal(personal);

       var programmingSkills = new ArrayList<String>();
       programmingSkills.add("Java");
       programmingSkills.add("Python");
       employee.setProgrammingSkills(programmingSkills);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public Employee updateEmployee(@PathVariable("id") int id, @RequestBody JsonPatch jsonPatch) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        var target = jsonPatch.apply(mapper.readTree(mapper.writeValueAsString(employee)));
        System.out.println(target);
        return mapper.treeToValue(target, Employee.class);
    }
}
