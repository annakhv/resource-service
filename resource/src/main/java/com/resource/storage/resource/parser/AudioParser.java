package com.resource.storage.resource.parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.web.multipart.MultipartFile;

public class AudioParser {

   public String parseAudio(MultipartFile file) throws IOException {
       BodyContentHandler handler = new BodyContentHandler();
       Metadata metadata = new Metadata();
       InputStream inputstream = file.getInputStream();
       ParseContext pcontext = new ParseContext();
       return null;
   }
}
