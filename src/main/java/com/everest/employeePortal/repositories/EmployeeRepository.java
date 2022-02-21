package com.everest.employeePortal.repositories;

import com.everest.employeePortal.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select u.permanent_address_id from employees u where u.id = %?1")
    Long getPermanentAddressId(Long employeeId);

    @Query("select u.present_address_id from employees u where u.id = %?1")
    Long getPresentAddressId(Long employeeId);


}