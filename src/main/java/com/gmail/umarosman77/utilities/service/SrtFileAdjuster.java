package com.gmail.umarosman77.utilities.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface SrtFileAdjuster {

	boolean adjustFile(String operator, int moveValue, MultipartFile file) throws IOException;
}
