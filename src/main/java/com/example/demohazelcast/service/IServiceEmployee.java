package com.example.demohazelcast.service;

import com.example.demohazelcast.DTOReloadData;
import com.example.demohazelcast.model.Employee;
import java.util.Map;

/**
 * Created on 4/22/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface IServiceEmployee {

    DTOReloadData reloadEmployee();
    
    DTOReloadData reloadEmployeeNoCache();
    
    Employee getEmployee(Integer employeeId);

    void removeEmployee(Integer employeeId);

    void addEmployee(Employee employee);

    void addEmployees(Map<Integer, Employee> employees);
}
