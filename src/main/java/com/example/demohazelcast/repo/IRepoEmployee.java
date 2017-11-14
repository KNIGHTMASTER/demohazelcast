package com.example.demohazelcast.repo;

import com.example.demohazelcast.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on 4/22/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface IRepoEmployee extends JpaRepository<Employee, Integer> {
}
