package com.resource.storage.resource.service;

import java.io.IOException;

import com.resource.storage.resource.client.SongClient;
import com.resource.storage.resource.dto.SongMetaData;
import com.resource.storage.resource.entity.ResourceData;
import com.resource.storage.resource.parser.AudioParser;
import com.resource.storage.resource.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

@RequiredArgsConstructor
@Service
public class AudioService {
    private final SongClient songClient;
    private final ResourceRepository resourceRepository;
    private final AudioParser audioParser;

    public long saveResource(MultipartFile multipartFile) throws TikaException, IOException, SAXException {
        SongMetaData metaData = audioParser.parseAudio(multipartFile);
        ResourceData data = new ResourceData().setData(multipartFile.getBytes());
        System.out.println("getbytes"+multipartFile.getBytes());
        ResourceData savedData = resourceRepository.save(data);
        metaData.setResourceId(savedData.getResourceId());
        songClient.saveSongMetaData(metaData);
        return savedData.getResourceId();
    }
}
