package com.huhoot.admin.manage.student;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentAddErrorResponse extends StudentAddRequest {
    private String errorMessage;

    public StudentAddErrorResponse(StudentAddRequest studentAddRequest) {
        setUsername(studentAddRequest.getUsername());
        setFullName(studentAddRequest.getFullName());
        setIsNonLocked(studentAddRequest.getIsNonLocked());

    }
}
