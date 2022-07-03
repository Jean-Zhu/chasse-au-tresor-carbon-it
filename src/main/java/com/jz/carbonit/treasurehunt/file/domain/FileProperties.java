package com.jz.carbonit.treasurehunt.file.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileProperties {
    private String filepath;
    private String inputFilename;
    private String outputFilename;
    private boolean test;
}
