package com.cisco.orderapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
    int id;
    String title;
    Map<String, String> personal = new HashMap<>();
    List<String> programmingSkills = new ArrayList<>();
}
