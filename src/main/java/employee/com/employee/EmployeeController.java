package employee.com.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping("/employees/{id}")
    public Employee findEmployeebyId(@PathVariable long id)
    {
        Optional <Employee> x=employeeRepository.findById(id);
        return x.get();
    }

    @DeleteMapping("/employees/{id}")
    public  void deleteEmployeebyId(@PathVariable long id)
    {
        employeeRepository.deleteById(id);
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails)
    {
        Optional<Employee> emp = employeeRepository.findById(id);
        Employee  employee    = emp.get();
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());
        Employee updatedEmployee = employeeRepository.save(employee);
        return updatedEmployee;
    }

}
