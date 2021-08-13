package com.udacity.jdnd.course3.critter.user;
// @author asmaa **

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
  @Autowired
  EmployeeRepository employeeRepository;

  public Employee save(Employee employee){
    return employeeRepository.save(employee);
  }

  public Employee findById(long id){
    return employeeRepository.findById(id);
  }

  public void empAvailable(Set<DayOfWeek> daysAvailable, long empId){
    Employee emp = this.findById(empId);
    if(emp != null){
      Set<DayOfWeek> days = emp.getDaysAvailable();
      if(days == null){
        days = new HashSet<>();
      }
      days.addAll(daysAvailable);
      emp.setDaysAvailable(days);
      this.save(emp);
    }
  }

  public List<Employee> findEmpBySkillAndDate(Set<EmployeeSkill> skills, LocalDate date){
    DayOfWeek dayOfWeek = date.getDayOfWeek();
    List<Employee> resEmp = new ArrayList<>();
    for(EmployeeSkill skill : skills){
      List<Employee> employeeSkill = employeeRepository.findAllBySkills(skill);
      for(Employee e : employeeSkill){
        if(!resEmp.contains(e) && e.getDaysAvailable().contains(dayOfWeek) && e.getSkills().containsAll(skills)){
          resEmp.add(e);
        }
      }
    }
    return resEmp;
  }
}
