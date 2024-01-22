package com.resource.storage.resource;

import org.springframework.web.multipart.MultipartFile;

public class AudioParser {

   public String parseAudio(MultipartFile file){

       //detecting the file type
       BodyContentHandler handler = new BodyContentHandler();
       Metadata metadata = new Metadata();
       FileInputStream inputstream = new FileInputStream(new File("example.mp3"));
       ParseContext pcontext = new ParseContext();

       //Mp3 parser
       Mp3Parser  Mp3Parser = new  Mp3Parser();
       Mp3Parser.parse(inputstream, handler, metadata, pcontext);
       LyricsHandler lyrics = new LyricsHandler(inputstream,handler);

       while(lyrics.hasLyrics()) {
           System.out.println(lyrics.toString());
       }

       System.out.println("Contents of the document:" + handler.toString());
       System.out.println("Metadata of the document:");
       String[] metadataNames = metadata.names();

       for(String name : metadataNames) {
           System.out.println(name + ": " + metadata.get(name));
       }
   }
}
   }
}
