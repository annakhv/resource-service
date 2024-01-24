package com.resource.storage.resource.client;

import com.resource.storage.resource.dto.SongMetaData;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class SongClient {

    private final WebClient songWebClient;

    public Long saveSongMetaData(SongMetaData metaData) {
        return songWebClient.post()
                .uri("/meta-data/songs")
                .body(BodyInserters.fromValue(metaData))
                .retrieve()
                .bodyToMono(Long.class)
                .block();
    }
}
