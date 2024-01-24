package com.resource.storage.resource.parser;

import java.io.IOException;
import java.io.InputStream;

import com.resource.storage.resource.dto.SongMetaData;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
@Component
@Slf4j
public class AudioParser {

   public SongMetaData parseAudio(MultipartFile file) throws IOException, TikaException, SAXException {
       BodyContentHandler handler = new BodyContentHandler();
       Metadata metadata = new Metadata();
       InputStream inputstream = file.getInputStream();
       AutoDetectParser parser = new AutoDetectParser();
       parser.parse(inputstream, handler, metadata);
       String[] metadataNames = metadata.names();
       SongMetaData songMetaData = new SongMetaData();
       for (String name : metadataNames) {
           if (name.equals("xmpDM:artist")) {
               songMetaData.setArtist(metadata.get(name));
           }
           if (name.equals("xmpDM:duration")) {
               songMetaData.setLength(Double.parseDouble(metadata.get(name))/1000);
           }
           if (name.equals("title")) {
               songMetaData.setName(metadata.get(name));
           }
           if (name.equals("xmpDM:album")) {
               songMetaData.setAlbum(metadata.get(name));
           }
           if (name.equals("xmpDM:releaseDate")) {
               songMetaData.setYear(Integer.parseInt(metadata.get(name)));
           }
           log.info(name + ": " + metadata.get(name));

       }
       log.info(songMetaData.toString());
       return songMetaData;
   }



}
