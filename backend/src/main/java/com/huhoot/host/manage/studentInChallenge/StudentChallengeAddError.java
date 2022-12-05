package com.huhoot.host.manage.studentInChallenge;

import lombok.Data;

@Data
public class StudentChallengeAddError{
    private int id;
    private String msg;
    public StudentChallengeAddError(int id, String message) {
        this.setId(id);
        this.setMsg(message);
    }
}


