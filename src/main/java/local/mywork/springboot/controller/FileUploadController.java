package local.mywork.springboot.controller;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.boxes.MovieHeaderBox;
import com.googlecode.mp4parser.MemoryDataSourceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class FileUploadController {
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") @Validated MultipartFile file) {
        if (file.isEmpty()) {
            return "empty.";
        }

        try {
            log.info(file.getName());
            log.info(file.getOriginalFilename());
            log.info(file.getContentType());

            IsoFile isoFile = new IsoFile(new MemoryDataSourceImpl(file.getBytes()));
            MovieHeaderBox movieHeader = isoFile.getMovieBox().getMovieHeaderBox();

            log.info("time scale: {}", movieHeader.getTimescale());
            log.info("duration: {}", movieHeader.getDuration());
            log.info("actual duration: {}", movieHeader.getDuration() / movieHeader.getTimescale());

            return "successfully uploaded!";
        } catch (Exception e) {
            return "failed to upload => " + e.getMessage();
        }
    }

    @ExceptionHandler(Exception.class)
    public void handleException(HttpServletRequest req, HttpServletResponse resp) {
    }
}
