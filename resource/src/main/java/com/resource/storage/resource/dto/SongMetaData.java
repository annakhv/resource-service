package com.resource.storage.resource.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SongMetaData {
    private String name;
    private String artist;
    private String album;
    private double length;
    private long resourceId;
    private int year;
}
