package com.example.demohazelcast;

import com.example.demohazelcast.model.Employee;
import com.example.demohazelcast.service.IServiceEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created on 4/22/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Controller
public class ControllerIgniteDB {

    @Autowired
    IServiceEmployee serviceEmployee;


    @RequestMapping("/employee/cache")
    public ModelAndView displayAllEmployee(){
        DTOReloadData dtoReloadData = serviceEmployee.reloadEmployee();
        return new ModelAndView("employee", "data", dtoReloadData);
    }
    
    @RequestMapping("/employee/nocache")
    public ModelAndView displayAllEmployeeNoCache(){
        DTOReloadData dtoReloadData = serviceEmployee.reloadEmployeeNoCache();
        return new ModelAndView("employee", "data", dtoReloadData);
    }
    
    @RequestMapping("/employee/insert")
    public String insertEmployee(@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO ){
        Employee employee = new Employee();
        employee.setCode(employeeDTO.getCode());
        employee.setName(employeeDTO.getName());
        employee.setRemarks(employeeDTO.getRemarks());
        serviceEmployee.addEmployee(employee);
        return "insert_employee";
    }
    
    @RequestMapping("/employee/view/insert")
    public String viewInsert(){
        return "insert_employee";
    }
    
    @RequestMapping("/employee/view/get")
    public String viewGet(){
        return "get_employee";
    }
    
    @RequestMapping("/employee/show/get")
    public ModelAndView showGet(@ModelAttribute("getEmployeeDTO") GetEmployeeDTO employeeDTO){
        return new ModelAndView("show_employee", "data", serviceEmployee.getEmployee(employeeDTO.getId()));
    }
    
    @RequestMapping("/employee/view/delete")
    public String viewDelete(){
        return "delete_employee";
    }
    
    @RequestMapping("/employee/delete")
    public ModelAndView showDelete(@ModelAttribute("getEmployeeDTO") GetEmployeeDTO employeeDTO){
        serviceEmployee.removeEmployee(employeeDTO.getId());
        DTOReloadData dtoReloadData = serviceEmployee.reloadEmployee();
        return new ModelAndView("employee", "data", dtoReloadData);        
    }
}
