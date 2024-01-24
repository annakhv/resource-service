package com.resource.storage.resource.controller;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.io.IOException;

import com.resource.storage.resource.service.AudioService;
import lombok.RequiredArgsConstructor;
import org.apache.tika.exception.TikaException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

@RestController
@RequiredArgsConstructor
public class ResourceController {

    private final AudioService audioService;

    @PostMapping(value = "/resources", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public long saveMusic(@RequestParam("song") MultipartFile file) throws TikaException, IOException, SAXException {
        return audioService.saveResource(file);
    }

    @GetMapping(value = "/resources/{id}")
    public byte[] getMusic(@PathVariable("id") long id) {
        return audioService.getResource(id);
    }

    @DeleteMapping(value = "/resources")
    public long[] deleteMusic(@QueryParam("ids") String ids) {

        return audioService.deleteResources(ids);
    }
}
