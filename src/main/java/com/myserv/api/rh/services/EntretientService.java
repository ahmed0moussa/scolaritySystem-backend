package com.myserv.api.rh.services;


import com.myserv.api.rh.model.Entretien;
import com.myserv.api.rh.model.FeedBack;
import com.myserv.api.rh.model.Specialite;
import com.myserv.api.rh.model.User;
import com.myserv.api.rh.repository.EntretienRepository;
import com.myserv.api.rh.repository.FeedBackRepository;
import com.myserv.api.rh.repository.SpecialiteRepository;
import com.myserv.api.rh.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EntretientService {

    private EntretienRepository entretienRepository;
    private UserRepository userRepository;
    private FeedBackRepository feedBackRepository;

    private SpecialiteRepository specialiteRepository ;



    public EntretientService(EntretienRepository entretienRepository,
                             UserRepository userRepository,
                             FeedBackRepository feedBackRepository,
                             SpecialiteRepository specialiteRepository
                             ) {
        this.entretienRepository = entretienRepository;
        this.userRepository = userRepository;
        this.feedBackRepository = feedBackRepository;
        this.specialiteRepository = specialiteRepository;

    }

    public List<FeedBack> getAllFeedbackOptions() {
        return feedBackRepository.findAll();
    }

    public Entretien createEntretien(Entretien entretien, String email, String specialiteId)  throws IOException {
        String DefaultFeedback = "64b1af43128f38495981525a";
        User user = userRepository.findByEmail(email).orElseThrow();
        Specialite specialite = specialiteRepository.findById(specialiteId).orElseThrow();
        FeedBack feedback = feedBackRepository.findById(DefaultFeedback).orElseThrow();
        entretien.setFeedback(feedback);
        entretien.setRecruteur(user);
        entretien.setSpecialite(specialite);

        return entretienRepository.save(entretien);

    }




    public Optional<Entretien> getEntretienById(String id) {
        return entretienRepository.findById(id);
    }
}
