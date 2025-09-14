package com.gmail.umarosman77.utilities.service;

import com.gmail.umarosman77.utilities.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class SrtFileAdjusterImpl implements SrtFileAdjuster {

	@Override
	public boolean adjustFile(String operator, int moveValue, MultipartFile file) throws IOException {
		initialiseResponseFile(file.getOriginalFilename());
//		String fileName = file.getOriginalFilename();
//		String contentType = file.getContentType();
//		long size = file.getSize();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			String line, subtitleNumber = "", subtitleTime = "", subtitleValue = "";
			int lineCount = 0;
			while ((line = reader.readLine()) != null) {
				lineCount++;
				switch (lineCount) {
					case 1:
						subtitleNumber = line;
						break;
					case 2:
						subtitleTime = line;
						break;
					case 3:
						subtitleValue = line;
						break;
				}
				if (lineCount == 4) {
					reader.readLine();
					subtitleTime = updateSubtitleTime(subtitleTime, operator, moveValue);
					writeToResponseFile(subtitleNumber, subtitleTime, subtitleValue);
					lineCount = 0;
				}
			}
		}

		return false;
	}

	//TODO Set up with AOP???
	private void initialiseResponseFile(String originalFilename) {

	}

	private String updateSubtitleTime(String subtitleTime, String operator, int moveValue) {
		return StringUtils.equals(Constants.PLUS_OPERATOR, operator) ? moveValuePlus(subtitleTime, moveValue) : moveValueMinus(subtitleTime, moveValue);
	}

	private String moveValuePlus(String subtitleTime, int moveValue) {
		return null;
	}

	private String moveValueMinus(String subtitleTime, int moveValue) {
		return null;
	}

	private void writeToResponseFile(String subtitleNumber, String subtitleTime, String subtitleValue) {

	}
}
