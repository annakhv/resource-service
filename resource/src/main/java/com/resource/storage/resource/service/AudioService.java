package com.resource.storage.resource.service;

import java.io.IOException;
import java.util.Arrays;

import com.resource.storage.resource.client.SongClient;
import com.resource.storage.resource.dto.SongMetaData;
import com.resource.storage.resource.entity.ResourceData;
import com.resource.storage.resource.exceptions.IdNotFoundException;
import com.resource.storage.resource.exceptions.InvalidScvException;
import com.resource.storage.resource.exceptions.ParserException;
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

    public long saveResource(MultipartFile multipartFile) throws IOException {
        SongMetaData metaData = new SongMetaData();
        try {
            metaData = audioParser.parseAudio(multipartFile);
        }catch (TikaException | IOException | SAXException e){
            throw new ParserException("invalid mp3 file has been provided");
        }
        ResourceData data = new ResourceData().setData(multipartFile.getBytes());
        ResourceData savedData = resourceRepository.save(data);
        metaData.setResourceId(savedData.getResourceId());
        songClient.saveSongMetaData(metaData);
        return savedData.getResourceId();
    }


    public byte[] getResource(long id){
       return resourceRepository.findById(id).map(ResourceData::getData).orElseThrow(()->new IdNotFoundException("wrong id number"));
    }

    // i think this method should be transactional and it should extend to the song service
    public long[] deleteResources(String ids) {
        validateScv(ids);
        long[] resultArray = Arrays.stream(ids.split(","))
                .mapToLong(Long::parseLong)
                .filter(resourceRepository::existsById)
                .peek(resourceRepository::deleteById)
                .toArray();
        songClient.deleteSongMetaData(ids);
        return resultArray;
    }

    private void validateScv(String ids) {
        if (ids.length() >= 200) {
            throw new InvalidScvException("provided scv is invalid");
        }
    }
}
