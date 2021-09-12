package com.halter.backendTest.models.requests;

import com.halter.backendTest.models.CollarStatus;
import lombok.Data;

/**
 * Simple DTO class containing info of a new cow
 */
@Data
public class NewCowRequest {
    private int collarId;
    private int cowNumber;
    private CollarStatus collarStatus;

    public NewCowRequest(int collarId, int cowNumber, CollarStatus collarStatus) {
        this.collarId = collarId;
        this.cowNumber = cowNumber;
        this.collarStatus = collarStatus;
    }
}
