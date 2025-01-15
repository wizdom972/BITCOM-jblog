package jblog.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	private static final String SAVE_PATH = "/jblog-uploads";
    private static final String URL = "/assets/upload-images";

    // 파일 업로드 처리 및 URL 반환
    public String restore(MultipartFile file) throws RuntimeException {
        try {
            // 업로드 디렉토리 생성
            File uploadDirectory = new File(SAVE_PATH);
            if (!uploadDirectory.exists() && !uploadDirectory.mkdirs()) {
                return null;
            }

            // 파일 비어 있는지 확인
            if (file.isEmpty()) {
                return null;
            }

            // 파일명 생성
            String originFilename = Optional.ofNullable(file.getOriginalFilename()).orElse("");
            String extName = originFilename.substring(originFilename.lastIndexOf('.') + 1);
            String saveFilename = generateSaveFilename(extName);

            // 파일 저장
            byte[] data = file.getBytes();
            try (OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename)) {
                os.write(data);
            }

            // 저장된 파일의 URL 반환
            return URL + "/" + saveFilename;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 고유한 파일명 생성
    private String generateSaveFilename(String extName) {
        Calendar calendar = Calendar.getInstance();

        return ""
            + calendar.get(Calendar.YEAR)
            + (calendar.get(Calendar.MONTH) + 1) 
            + calendar.get(Calendar.DATE)
            + calendar.get(Calendar.HOUR_OF_DAY)
            + calendar.get(Calendar.MINUTE)
            + calendar.get(Calendar.SECOND)
            + calendar.get(Calendar.MILLISECOND)
            + ("." + extName);
    }
}

