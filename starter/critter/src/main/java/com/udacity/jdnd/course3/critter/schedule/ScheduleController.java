package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.UtilClass;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeService;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

  @Autowired
  PetService petService;

  @Autowired
  CustomerService customerService;

  @Autowired
  EmployeeService employeeService;

  @Autowired
  ScheduleService scheduleService;

  @PostMapping
  public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
    Schedule schedule = UtilClass.convertDTOToScheduleEntity(scheduleDTO);
    return convertScheduleToScheduleDTO(
        scheduleService.createSchedule(schedule, scheduleDTO.getEmployeeIds(),
            scheduleDTO.getPetIds()));
  }

  private ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule) {
    List<Long> employeeIds = schedule.getEmployees().stream().map(employee -> employee.getId())
        .collect(Collectors.toList());
    List<Long> petIds = schedule.getPets().stream().map(pet -> pet.getId())
        .collect(Collectors.toList());
    ScheduleDTO scheduleDTO = new ScheduleDTO();
    BeanUtils.copyProperties(schedule, scheduleDTO);
    scheduleDTO.setId(schedule.getId());
    scheduleDTO.setEmployeeIds(employeeIds);
    scheduleDTO.setPetIds(petIds);
    return scheduleDTO;
  }

  @GetMapping
  public List<ScheduleDTO> getAllSchedules() {
    List<Schedule> allSchedules = scheduleService.findAll();

    List<ScheduleDTO> scheduleDTOs = convertList(allSchedules);

    return scheduleDTOs;
  }

  @GetMapping("/pet/{petId}")
  public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
    Pet pet = petService.findPetById(petId);
    if (pet == null) {
      return new ArrayList<>();
    }

    List<Schedule> schedules = scheduleService.findScheduleByPet(pet);

    List<ScheduleDTO> scheduleDTOs = convertList(schedules);
    return scheduleDTOs;
  }

  @GetMapping("/employee/{employeeId}")
  public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
    Employee employee = employeeService.findById(employeeId);
    if (employee == null) {
      return new ArrayList<>();
    }

    List<Schedule> schedules = scheduleService.findScheduleByEmployee(employee);
    List<ScheduleDTO> scheduleDTOs = convertList(schedules);
    return scheduleDTOs;
  }

  @GetMapping("/customer/{customerId}")
  public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
    Customer customer = customerService.findCustomerById(customerId);
    if (customer == null) {
      return new ArrayList<>();
    }

    List<Schedule> schedules = scheduleService.findScheduleByCustomer(customer);
    List<ScheduleDTO> scheduleDTOs = convertList(schedules);
    return scheduleDTOs;
  }

  public List<ScheduleDTO> convertList(List<Schedule> schedules) {
    return schedules.stream().map(schedule ->
            UtilClass.convertEntityToScheduleDTO(schedule))
        .collect(Collectors.toList());
  }
}
