package com.myserv.api.rh.services;

import com.myserv.api.rh.configfile.FileStorageProperties;
import com.myserv.api.rh.configfile.ImageStorageProperties;
import com.myserv.api.rh.model.Entretien;
import com.myserv.api.rh.model.Etudiant;
import com.myserv.api.rh.repository.EntretienRepository;
import com.myserv.api.rh.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileImageService {
    @Autowired
    private EtudiantRepository etudiantRepository;
    private final Path imageUploadLocation;
    private final Path imageDownloadLocation = Paths.get("./uploads/Image");

    @Autowired
    public FileImageService(ImageStorageProperties imageStorageProperties) {
        this.imageUploadLocation = Paths.get(imageStorageProperties.getImageDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.imageUploadLocation);
        } catch (Exception e) {
            throw new RuntimeException("Cannot create the directory where you want to the uploaded the files will be kept.", e);
        }
    }
    public Resource loadResource(String filename) {
        try {
            Path path = imageUploadLocation.resolve(filename);
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists() || resource.isReadable()){
                return resource;
            }else {
                throw new RuntimeException("Fail");
            }
        }catch (MalformedURLException e){
            throw new RuntimeException("Fail");
        }
    }

    public String uploadImage(String etudiantId, MultipartFile image) {
        // Renormalize the file name
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());

        Etudiant etudiant= etudiantRepository.findById(etudiantId).orElseThrow();
        try {
            // Verify if the file's name  is containing invalid characters
            if (fileName.contains("..")) {
                throw new RuntimeException("Sorry! image name is containing invalid path sequence " + fileName);
            }
            // Copy file to the target path (replacing existing file with the same name)
            Path targetLocation = this.imageUploadLocation.resolve(fileName);

            Files.copy(image.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            String imageUploadUrl= ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/Image/").path(fileName).toUriString();

            etudiant.setImage(imageUploadUrl);
            etudiantRepository.save(etudiant);





            return imageUploadUrl;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public ResponseEntity<Resource> downloadImage(String fileName) {
        try {
            Path imagePath = imageDownloadLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(imagePath.toUri());

            if (resource.exists()) {
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; imagename=" + resource.getFilename());

                return ResponseEntity.ok()
                        .headers(headers)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error downloading image: " + ex.getMessage());
        }
    }
}
