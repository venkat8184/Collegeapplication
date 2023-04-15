package com.collegeapplication.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponse {
    private List<DepartmentDto> contents;
    private int pageSize;
    private int pageNumber;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
