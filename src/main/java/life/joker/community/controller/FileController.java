package life.joker.community.controller;

import jakarta.servlet.http.HttpServletRequest;
import life.joker.community.dto.FileDTO;
import life.joker.community.provider.QiniuProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;

/**
 * @author joker
 * @date 2023/03/10 11:41
 **/
@Controller
public class FileController {
    @Autowired
    private QiniuProvider qiniuProvider;

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO uplooad(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("editormd-image-file");
        String path = null;
        FileDTO fileDTO = new FileDTO();
        try {
            path = qiniuProvider.upload(file.getInputStream(), file.getOriginalFilename());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (StringUtils.isBlank(path)) {
            fileDTO.setSuccess(0);
            fileDTO.setMessage("上传失败");
        } else {
            fileDTO.setSuccess(1);
            fileDTO.setUrl(path);
        }

        return fileDTO;
    }
}