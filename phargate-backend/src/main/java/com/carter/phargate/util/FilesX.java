package com.carter.phargate.util;

import lombok.experimental.UtilityClass;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

@UtilityClass
public class FilesX {

    public static File fromClassPath(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        return resource.getFile();
    }

}