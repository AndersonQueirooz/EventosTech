package com.queiroz.EventosTech.services;

import com.amazonaws.services.s3.AmazonS3;
import com.queiroz.EventosTech.domain.event.Event;
import com.queiroz.EventosTech.domain.event.EventRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class EventServices {

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3client;

   public Event createEvent(EventRequestDTO data) {
       String imgUrl = null;

       if (data.image() != null) {
          imgUrl = this.UploadImage(data.image());
       }

       Event newEvent = new Event();
       newEvent.setTitle(data.title());
       newEvent.setDescription(data.description());
       newEvent.setDate(new Date(data.date()));
       newEvent.setEventUrl(data.eventUrl());
       newEvent.setImgUrl(imgUrl);

       return newEvent;
   }

   private String UploadImage(MultipartFile MultipartFile) {
       String fileName = UUID.randomUUID() + "-" + MultipartFile.getOriginalFilename();

       try {
           File file = this.convertMultiPartToFile(MultipartFile);
           s3client.putObject(bucketName, fileName, file);
           file.delete();
           return s3client.getUrl(bucketName, fileName).toString();

       }
           catch(Exception e){
               System.out.println("Error uploading file to S3: " + e.getMessage());
               return null;
       }

   }
       private File convertMultiPartToFile(MultipartFile multiparfile) throws IOException {
           File convFile = new File(Objects.requireNonNull(multiparfile.getOriginalFilename()));
           FileOutputStream fos = new FileOutputStream(convFile);
           fos.write(multiparfile.getBytes());
           fos.close();
           return convFile;
   }

}
