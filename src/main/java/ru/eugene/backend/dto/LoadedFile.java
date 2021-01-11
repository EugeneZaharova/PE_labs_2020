package ru.eugene.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoadedFile {
    private String name;
    private String contentType;
    private byte[] content;
}
