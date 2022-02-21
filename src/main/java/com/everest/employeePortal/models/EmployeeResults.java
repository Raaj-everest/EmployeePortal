package com.everest.employeePortal.models;

import com.everest.employeePortal.entities.Employee;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class EmployeeResults {
    public List<Employee> data;
    public long totalElements;
    public long totalPages;
    public long pageSize;
    public long currentPage;
    public boolean hasNext;
    public boolean hasPrevious;

    public EmployeeResults(Page<Employee> employeesData) {
        this.setData(employeesData.getContent());
        this.setTotalElements(employeesData.getTotalElements());
        this.setTotalPages(employeesData.getTotalPages());
        this.setPageSize(employeesData.getSize());
        this.setCurrentPage(employeesData.getNumber()+1);
        this.setHasNext(employeesData.hasNext());
        this.setHasPrevious(employeesData.hasPrevious());
    }


}
