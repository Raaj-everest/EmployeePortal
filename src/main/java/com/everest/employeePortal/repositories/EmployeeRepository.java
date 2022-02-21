package com.everest.employeePortal.repositories;

import com.everest.employeePortal.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT * FROM EMPLOYEES WHERE FIRST_NAME iLIKE %?1% or LAST_NAME iLike %?1%", nativeQuery = true)
    Page<Employee> findHavingNameLike(String data, Pageable pageable);

    @Query(value = "select permanent_address_id from employees where id = ?1", nativeQuery = true)
    Long getPermanentAddressId(Long employeeId);

    @Query(value = "select present_address_id from employees where id = ?1", nativeQuery = true)
    Long getPresentAddressId(Long employeeId);


}