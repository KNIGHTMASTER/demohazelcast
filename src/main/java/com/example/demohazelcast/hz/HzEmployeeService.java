/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demohazelcast.hz;

import com.example.demohazelcast.DTOReloadData;
import com.example.demohazelcast.model.Employee;
import com.example.demohazelcast.repo.IRepoEmployee;
import com.example.demohazelcast.service.IServiceEmployee;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fauzi
 */
@Service
public class HzEmployeeService implements IServiceEmployee{

    @Autowired
    private HazelcastInstance instance;
    
    @Autowired
    private IRepoEmployee employeeDAO;
    
    @Override
    public DTOReloadData reloadEmployee() {        
        long startTime = System.currentTimeMillis();
        DTOReloadData dTOReloadData = new DTOReloadData();
        IMap<Integer , Employee> dataStore = instance.getMap("employeeMap");
        if (dataStore.values().size() < 1){
            System.out.println("DATA FROM DB");
            dTOReloadData.setEmployees(employeeDAO.findAll());
            Map<Integer, Employee> mapEmployee = new HashMap<>();
            System.out.println("Size 1 normal : "+dTOReloadData.getEmployees().size());
            for (Employee e : dTOReloadData.getEmployees()){
                mapEmployee.put(e.getId(), e);
            }
            addEmployees(mapEmployee);
        }else{
            System.out.println("DATA FROM CACHE");
            System.out.println("Size 2: "+dataStore.values().size());
            dTOReloadData.setEmployees(new ArrayList<>(dataStore.values()));    
        }        
        dTOReloadData.setElapsedTime(String.valueOf(System.currentTimeMillis() - startTime));
        return dTOReloadData;
    }

    @Override
    public DTOReloadData reloadEmployeeNoCache() {
        System.out.println("DATA NO CACHE");
        long startTime2 = System.currentTimeMillis();
        DTOReloadData dTOReloadData = new DTOReloadData();
        dTOReloadData.setEmployees(employeeDAO.findAll());
        System.out.println("Size 3 : "+dTOReloadData.getEmployees().size());
        dTOReloadData.setElapsedTime(String.valueOf(System.currentTimeMillis() - startTime2));
        return dTOReloadData;
    }

    @Override
    public Employee getEmployee(Integer employeeId) {
        IMap<Integer , Employee> dataStore = instance.getMap("employeeMap");
        return dataStore.get(employeeId);
    }

    @Override
    public void removeEmployee(Integer employeeId) {
        employeeDAO.delete(employeeId);
        IMap<Integer , Employee> dataStore = instance.getMap("employeeMap");
        dataStore.remove(employeeId);        
    }

    @Override
    public void addEmployee(Employee employee) {
        //JPAFIRST
        employeeDAO.save(employee);
        //HZ PART
        IMap<Integer , Employee> dataStore = instance.getMap("employeeMap");
        dataStore.put(employee.getId(), employee);
        System.out.println("INSERT TO CACHE");
    }

    @Override
    public void addEmployees(Map<Integer, Employee> employees) {
        employeeDAO.save(new ArrayList(employees.values()));
        IMap<Integer , Employee> dataStore = instance.getMap("employeeMap");
        dataStore.putAll(employees);
    }
    
    public void shutDown(){
        instance.getLifecycleService().shutdown();
    }
}
