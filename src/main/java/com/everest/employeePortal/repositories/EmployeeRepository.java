package com.everest.employeePortal.repositories;

import com.everest.employeePortal.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT * FROM EMPLOYEES WHERE FIRST_NAME iLIKE %?1%", nativeQuery = true)
    List<Employee> findByFirstNameLike(String data);

    @Query(value = "SELECT * FROM EMPLOYEES WHERE LAST_NAME iLIKE %?1%", nativeQuery = true)
    List<Employee> findByLastNameLike(String data);
}