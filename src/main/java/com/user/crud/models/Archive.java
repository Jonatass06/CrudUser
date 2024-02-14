package com.user.crud.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "archive")
public class Archive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] data;

    public Archive(MultipartFile multipartFile) {
        this.name = multipartFile.getOriginalFilename();
        this.type = multipartFile.getContentType();
        try{
            this.data = multipartFile.getBytes();
        }catch (IOException e){
            this.data = null;
        }
    }
}
