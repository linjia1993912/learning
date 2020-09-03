package learning.readinglist.uploadfile.controller;

import learning.readinglist.uploadfile.storage.StorageFileNotFoundException;
import learning.readinglist.uploadfile.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.stream.Collectors;

/**
 * @Description:文件上传controller
 *
 * springboot工程作为服务器，去接收通过http 上传的multi-file的文件
 *
 * 这个类通过@Controller注解，表明自己上一个Spring mvc的c。每个方法通过 @GetMapping 或者@PostMapping注解表明自己的 http方法。
 *
 * GET / 获取已经上传的文件列表
 * GET /files/{filename} 下载已经存在于服务器的文件
 * POST / 上传文件给服务器
 *
 * @Author LinJia
 * @Date 2020/9/3
 **/
@Controller
public class FileUploadController {

    private final StorageService storageService;

    //注入StorageService
    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    /**
     * @Description:获取文件列表
     * @Author LinJia
     * @Date 2020/9/3 16:31 
     * @Param [model]
     * @return java.lang.String
     **/
    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService
                .loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    /**
     * @Description:下载文件
     * @Author LinJia
     * @Date 2020/9/3 16:31 
     * @Param [filename]
     * @return org.springframework.http.ResponseEntity<org.springframework.core.io.Resource>
     **/
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }

    /**
     * @Description:上传文件
     * redirect重定向到获取文件列表
     * @Author LinJia
     * @Date 2020/9/3 16:30
     * @Param [file, redirectAttributes]
     * @return java.lang.String
     **/
    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    //统一异常处理
    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
