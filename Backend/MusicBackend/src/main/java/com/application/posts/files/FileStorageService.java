package com.application.posts.files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * File storage service. responsible for storing the multipartfiles on the server
 */
@Service
public class FileStorageService {

    @Autowired
    private FileDBRepository fileDBRepository;

    /**
     * Stores the file in the fileRepository
     * @param file
     * @return the file that was stored
     * @throws IOException
     */
    public FileDB store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
        return fileDBRepository.save(FileDB);
    }

    /**
     *
     * @param id
     * @return the file fetched by its id
     */
    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    /**
     *
     * @return a list of all the files in the database
     */
    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

    /**
     * Removes a specific file by its id
     * @param id
     */
    public void removeFile(String id)
    {
    	fileDBRepository.delete(getFile(id));
    }
}
