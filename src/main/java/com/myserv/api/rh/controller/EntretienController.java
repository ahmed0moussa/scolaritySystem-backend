package com.myserv.api.rh.controller;


import com.myserv.api.rh.model.Entretien;
import com.myserv.api.rh.repository.EntretienRepository;
import com.myserv.api.rh.services.EntretientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.myserv.api.rh.services.FileUploadService;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class EntretienController {

    @Autowired
    private EntretienRepository entretienRepository;

    private EntretientService entretientService;

    @Autowired
    private FileUploadService fileUploadService;


    public EntretienController(EntretienRepository entretienRepository, EntretientService entretientService) {
        this.entretienRepository = entretienRepository;
        this.entretientService = entretientService;
    }

    @PostMapping(value = "/api/v1/entretien/create" )
    public Entretien place(@RequestBody Entretien entretien,
                        Principal principal,
                        @RequestParam String specialite) throws IOException {
        String username = principal.getName();

        entretientService.createEntretien(entretien, username, specialite);
        return entretien;
    }
    @GetMapping("/api/v1/entretien/findbyid")
    public Optional<Entretien> findbyid(@RequestParam String id ){
        
        return entretientService.getEntretienById(id);

    }


    @GetMapping("/api/v1/entretien/all")
    public List<Entretien> all() {
        return entretienRepository.findAll();

    }

    @GetMapping("/api/v1/entretien/specialite")
    public List<Entretien> etentretienBySpecialiteId(String specialiteId) {
        return entretienRepository.findBySpecialiteId(specialiteId);
    }
    @PostMapping ("/api/v1/entretien/uploadFile/{entretienId}")
    public String uploadFile(@PathVariable String entretienId,@RequestParam("file") MultipartFile file) {

        return fileUploadService.uploadFile(entretienId,file);
    }
    @GetMapping("/api/v1/entretien/downloadFile/{imageName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String imageName) {
        return fileUploadService.downloadFile(imageName);
    }

}
