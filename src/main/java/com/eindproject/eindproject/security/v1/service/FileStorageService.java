package com.eindproject.eindproject.security.v1.service;

import com.eindproject.eindproject.security.v1.exceptions.FileStorageException;
import com.eindproject.eindproject.security.v1.exceptions.MyFileNotFoundException;
import com.eindproject.eindproject.security.v1.exceptions.RecordNotFoundException;
import com.eindproject.eindproject.security.v1.model.Feedback;
import com.eindproject.eindproject.security.v1.model.FileDB;
import com.eindproject.eindproject.security.v1.model.User;
import com.eindproject.eindproject.security.v1.property.FileStorageProperties;
import com.eindproject.eindproject.security.v1.repository.FileDBRepository;
import com.eindproject.eindproject.security.v1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class FileStorageService {

//    FilesStorageService uses FileDBRepository to provide methods for saving new file, get file by id, get list of Files

    //service for storing files in the file system and retrieving them

    @Autowired
    private FileDBRepository fileDBRepository;

    @Autowired
    private UserRepository userRepository;

    public FileDB uploadFile(MultipartFile multipartFile, Long id) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        User user = userRepository.findById(id).get();

        FileDB fileDB = new FileDB(fileName, multipartFile.getContentType(), multipartFile.getBytes());
        fileDB.setUser(user);
        userRepository.save(user);
        return fileDBRepository.save(fileDB);
    }

    public FileDB getFileById(Long id) {
        if (fileDBRepository.findById(id).isPresent()) {
            return fileDBRepository.findById(id).get();
        } else {
            throw new RecordNotFoundException(id);
        }
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

    public void deleteFile(Long id) {
        if (fileDBRepository.existsById(id)) {
            fileDBRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException();
        }
    }

    public List<Feedback> getFilesFeedback(Long id) {
        Optional<FileDB> fileDB = fileDBRepository.findById(id);
        if (fileDB.isPresent()) {
            return fileDB.get().getFeedback();
        } else {
            throw new RecordNotFoundException();
        }
    }




//    private final Path fileStorageLocation;

//    @Autowired
//    public FileStorageService(FileStorageProperties fileStorageProperties) {
//        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
//                .toAbsolutePath().normalize();
//
//        try {
//            Files.createDirectories(this.fileStorageLocation);
//        } catch (Exception ex) {
//            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
//        }
//    }

//    public String storeFile(MultipartFile file) {
//        // Normalize file name
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//
//        try {
//            // Check if the file's name contains invalid characters
//            if(fileName.contains("..")) {
//                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
//            }
//
//            // Copy file to the target location (Replacing existing file with the same name)
//            Path targetLocation = this.fileStorageLocation.resolve(fileName);
//            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
//
//            return fileName;
//        } catch (IOException ex) {
//            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
//        }
//    }

//    public Resource loadFileAsResource(String fileName) {
//        try {
//            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
//            Resource resource = new UrlResource(filePath.toUri());
//            if(resource.exists()) {
//                return resource;
//            } else {
//                throw new MyFileNotFoundException("File not found " + fileName);
//            }
//        } catch (MalformedURLException ex) {
//            throw new MyFileNotFoundException("File not found " + fileName, ex);
//        }
//    }


}
