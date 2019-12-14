package com.ht.controller.llb;

import com.ht.utils.file.FileUpload;
import com.ht.vo.DataDocVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/file")
public class FileUploadController {

    @RequestMapping("/toFileList")
    public String toFileList(){
        return "llb/file-manage/home";
    }

    @RequestMapping("toUpload")
    public String toUpload(){
        return "";
    }

    @RequestMapping("/addFile")
    @ResponseBody
    public Map addFile(DataDocVO dataDocVO, MultipartFile file, HttpServletRequest request){
        Map map = new HashMap();
        String realPath =  request.getRealPath("");
        String dirPath = realPath+"\\WEB-INF\\static\\upload\\";
        //上传文件
        FileUpload.upload(dirPath,file);

        return map;
    }
}
