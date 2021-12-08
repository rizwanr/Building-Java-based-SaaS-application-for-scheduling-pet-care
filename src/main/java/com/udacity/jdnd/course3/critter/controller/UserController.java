package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public CustomerService customerService;

    @Autowired
    public EmployeeService employeeService;

    @Autowired
    public PetService petService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Long createdCustomerId = customerService.saveCustomer(convertCustomerDtoToCustomer(customerDTO));

        customerDTO.setId(createdCustomerId);
        return customerDTO;


    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){

        List<Customer> listOfCustomers =customerService.getListOfCustomers();
        List<CustomerDTO> customerDto= new ArrayList<>();
        for (Customer customer: listOfCustomers
             ) {
            customerDto.add(convertCustomerToCustomerDTO(customer));
        }
        return customerDto;


//        List<Customer> customerList = customerService.getListOfCustomers();
//        List<CustomerDTO> customerDTO = customerList.stream()
//                .map(UserController::convertCustomerToCustomerDTO)
//                .collect(Collectors.toList());
        //        return customerDto;
    }


    @GetMapping("/employees")
    public List<EmployeeDTO> getAllEmployees(){

        List<Employee> listOfEmployees =employeeService.getListOfEmployees();
        List<EmployeeDTO> employeeDto= new ArrayList<>();
        for (Employee employee: listOfEmployees
        ) {
            employeeDto.add(convertEmpoyeeToEmployeeDTO(employee));
        }
        return employeeDto;


//        List<Customer> customerList = customerService.getListOfCustomers();
//        List<CustomerDTO> customerDTO = customerList.stream()
//                .map(UserController::convertCustomerToCustomerDTO)
//                .collect(Collectors.toList());
        //        return customerDto;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        return convertCustomerToCustomerDTO(customerService.getCustomerByPetId(petId));

    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Long createdEmployeeId = employeeService.saveEmployee(convertEmpoyeeDtoToEmployee(employeeDTO));
        employeeDTO.setId(createdEmployeeId);
        return employeeDTO;
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return convertEmpoyeeToEmployeeDTO(employeeService.getEmployeeById(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.saveAvailablityOfEmployee(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
       //return  employeeService.findEmployeesWithSkillSet(employeeDTO);
//        List<Employee> employees = employeeService.findEmployeeAvailability(employeeDTO);

        throw new UnsupportedOperationException();


    }


    //create two conversion methods that know how to exchange between Customer and CustomerDTO
    //extract the pet ids from the pets present in the customer and include them in the customerDTO,
    private static CustomerDTO convertCustomerToCustomerDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        List<Long> petIds = new ArrayList<>();
        try {
            customer.getPet().forEach(pet -> {
                petIds.add(pet.getPetId());
                customerDTO.setPetIds(petIds);
                customerDTO.setNotes(pet.getNotes());
            });

        }catch (Exception e){
            System.out.println("customer "+customer.getCustomerId() + " does not have any pet");
        }
        return customerDTO;

    }

    private static Customer convertCustomerDtoToCustomer(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;

    }


    public  static Employee convertEmpoyeeDtoToEmployee(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
         BeanUtils.copyProperties(employeeDTO, employee);
         return employee;
    }

    public  static EmployeeDTO convertEmpoyeeToEmployeeDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }




}
