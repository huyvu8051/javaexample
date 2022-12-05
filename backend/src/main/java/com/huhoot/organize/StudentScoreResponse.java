package com.huhoot.organize;

import lombok.Data;

@Data
public class StudentScoreResponse {
    private int id;
    private String username;
    private String fullName;
    private double totalPoint;

    public StudentScoreResponse(int studentId, double score, String studentFullName, String username){
        this.id = studentId;
        this.totalPoint = score;
        this.fullName = studentFullName;
        this.username = username;
    }
}
