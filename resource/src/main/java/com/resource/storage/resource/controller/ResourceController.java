package com.resource.storage.resource.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ResourceController {
      @PostMapping(value = "/resources", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
      public long saveMusic(@RequestParam("song") MultipartFile file){
          System.out.println(file.getContentType());
             return 2L;
       }

}
