/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demohazelcast.hz;

import com.example.demohazelcast.model.Employee;
import com.example.demohazelcast.repo.IRepoEmployee;
import com.hazelcast.core.MapStore;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author fauzi
 */
public class HzEmployeeDAO implements MapStore<Integer, Employee>{
    
    @Autowired
    IRepoEmployee employeeDAO;

    @Override
    public void store(Integer k, Employee v) {
        employeeDAO.save(v);
    }

    @Override
    public void storeAll(Map<Integer, Employee> map) {
        for (Map.Entry<Integer, Employee> mapEntry : map.entrySet()) {
            store(mapEntry.getKey(), mapEntry.getValue());
        }
    }

    @Override
    public void delete(Integer k) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll(Collection<Integer> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Employee load(Integer k) {
        return employeeDAO.findOne(k);
    }

    @Override
    public Map<Integer, Employee> loadAll(Collection<Integer> clctn) {
        Map<Integer, Employee> employeeMap = new HashMap<Integer, Employee>();
        List<Employee> employees = employeeDAO.findAll();
        for (Employee employee : employees) {
            employeeMap.put(employee.getId(), employee);
        }
        return employeeMap;
    }

    @Override
    public Iterable<Integer> loadAllKeys() {
        Map<Integer, Employee> employeeMap = new HashMap<Integer, Employee>();
        List<Employee> employees = employeeDAO.findAll();
        for (Employee employee : employees) {
            employeeMap.put(employee.getId(), employee);
        }
        return employeeMap.keySet();
    }

        
}
