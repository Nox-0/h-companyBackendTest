package com.halter.backendTest.models;

import com.halter.backendTest.models.requests.NewCowRequest;
import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.ValidationException;

@Data
@Entity
public class Cow {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private int id;

    private int collarId;
    private int cowNumber;
    private CollarStatus collarStatus;

    /**
     * Constructor for Spring JPA use
     */
    protected Cow() { }

    /**
     * Basic cow constructor using NewCowRequest.
     * Converts a NewCowRequest to a Cow entity
     * @param newCowRequest see NewCowRequest.java - creates a new cow using minimum fields
     */
    public Cow(NewCowRequest newCowRequest) {
        this.collarStatus = newCowRequest.getCollarStatus();
        this.collarId = newCowRequest.getCollarId();
        this.cowNumber = newCowRequest.getCowNumber();
    }

    /**
     * Complete constructor for the cow entity
     * @param collarId ID number of the collar
     * @param cowNumber number of the cow
     * @param collarStatus status of the collar
     */
    public Cow(int collarId, int cowNumber, CollarStatus collarStatus) {
        this.collarId = collarId;
        this.cowNumber = cowNumber;
        this.collarStatus = collarStatus;
    }
}
