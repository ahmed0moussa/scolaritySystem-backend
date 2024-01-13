package com.myserv.api.rh.model;

import lombok.Data;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document()
@Setter
public class Classe {
    @Id
    private String id ;
    private  String nomClasse;
    private List<Matiere> matieres;
    private List<Etudiant> etudiants;
}
