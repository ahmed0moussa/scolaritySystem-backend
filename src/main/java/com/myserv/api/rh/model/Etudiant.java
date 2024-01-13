package com.myserv.api.rh.model;

import lombok.Data;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document()
@Setter
public class Etudiant {
    @Id
    private String id ;
    private String matricule;
    private String cin;
    private int age;
    private String nom;
    private String prenom;
    private String adrress;
    private String gsm;
    private String parent;
    private String telParent;
    private String emailParent;
    private Date dateN;
    private String lienN;
    private String email;
    private String bac;
    private Date anneeBac;
    private Double moybac;
    private String image;

}
