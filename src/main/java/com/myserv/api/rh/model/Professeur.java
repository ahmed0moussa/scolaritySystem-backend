package com.myserv.api.rh.model;

import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document()
@Setter
public class Professeur {
    @Id
    private String id ;
    private String nom;
    private String prenom;
    private String specialite;

}
