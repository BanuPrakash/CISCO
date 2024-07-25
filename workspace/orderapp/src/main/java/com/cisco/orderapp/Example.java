package com.cisco.orderapp;

import com.cisco.orderapp.dto.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;

public class Example {
    public static void main(String[] args) throws  Exception{
        Employee employee = new Employee();
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
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(employee));
    }
}
