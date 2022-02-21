package com.everest.employeePortal.repositories;

import com.everest.employeePortal.entities.Address;
import com.everest.employeePortal.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "select permanent_address_id from employees where id = ?1",nativeQuery = true)
    Long getPermanentAddressId(Long employeeId);

    @Query(value = "select present_address_id from employees where id = ?1",nativeQuery = true)
    Long getPresentAddressId(Long employeeId);


}