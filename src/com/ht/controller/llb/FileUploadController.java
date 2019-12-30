package com.ht.controller.llb;

import com.ht.service.llb.IDataDocService;
import com.ht.utils.file.DeleteFile;
import com.ht.utils.file.FileUpload;
import com.ht.vo.DataDocVO;
import com.ht.vo.EmpVO;
import org.apache.commons.io.FileUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/file")
public class FileUploadController {

    @Resource
    private IDataDocService dataDocService;

    @RequestMapping("/toFileList")
    public String toFileList(){
        return "llb/file-manage/home";
    }

    @RequestMapping("toUpload")
    public String toUpload(){
        return "";
    }

    @RequestMapping("/pageList")
    @ResponseBody
    public Map pageList(int page, int limit){
        Map map = new HashMap();
        Integer count = dataDocService.countFile();
        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",dataDocService.pageList(page,limit));
        return map;
    }

    @RequestMapping("/addFile")
    @ResponseBody
    public String addFile(DataDocVO dataDocVO, MultipartFile file, HttpServletRequest request){
        Map map = new HashMap();
        String realPath =  request.getRealPath("");
//        String dirPath = realPath+"WEB-INF"+File.separator+"static"+File.separator+"upload"+File.separator;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String strDate = sdf.format(new Date());

        String dirPath = realPath+"WEB-INF"+File.separator+"static"+File.separator+"upload"+File.separator + strDate + File.separator;
        //上传文件
        String fileName = FileUpload.uploadAndRename(dirPath,file);

        //保存数据库
        dataDocVO.setDataName(file.getOriginalFilename());
        dataDocVO.setUrl(dirPath+fileName);
        EmpVO empVO = (EmpVO) request.getSession().getAttribute("emp");
        dataDocVO.setEmpId(empVO.getEmpId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dataDocVO.setOptime(simpleDateFormat.format(new Date()));
        //保存到数据库
        dataDocService.addFile(dataDocVO);
        return "success";
    }

    @RequestMapping("/delFile")
    @ResponseBody
    public String delFile(DataDocVO dataDocVO){
        DataDocVO db = dataDocService.findFile(dataDocVO.getDocId());
        dataDocService.deleteFile(dataDocVO);
        //删除硬盘文件
        DeleteFile.deleteFile(db.getUrl());
        return "success";
    }

    /*@RequestMapping("/download.do")
    public ResponseEntity<byte[]> download(Integer docId) throws Exception{
        DataDocVO dataDocVO = dataDocService.findById(docId);
        // 指定要下载的文件所在路径
        String path = dataDocVO.getUrl();
        // 创建该文件对象
        File file = new File(path);

        HttpHeaders httpHeaders = new HttpHeaders();
        // 通知浏览器以下载的方式打开文件
        httpHeaders.setContentDispositionFormData("attachment",dataDocVO.getDataName());
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),httpHeaders, HttpStatus.OK);
    }*/
    @RequestMapping("/download.do")
    public void download(Integer docId,HttpServletRequest request,HttpServletResponse response) throws Exception{
        DataDocVO dataDocVO = dataDocService.findById(docId);

        // 指定要下载的文件所在路径
        String path = dataDocVO.getUrl();
        // 创建该文件对象
        File file = new File(path);

        String fileName = this.getFilename(request,dataDocVO.getDataName());

        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);

        //打开本地文件流
        InputStream inputStream = new FileInputStream(file);
        //激活下载操作
        OutputStream os = response.getOutputStream();

        byte[] b = new byte[2048];
        int length;
        while ((length = inputStream.read(b)) > 0) {
            os.write(b,0,length);
        }
        // 这里主要关闭。
        os.close();
        inputStream.close();
    }

    /**
     * 根据浏览器的不同进行编码设置，返回编码后的文件名
     */
    public String getFilename(HttpServletRequest request,
                              String filename) throws Exception {
        // IE不同版本User-Agent中出现的关键词
        String[] IEBrowserKeyWords = {"MSIE", "Trident", "Edge"};
        // 获取请求头代理信息
        String userAgent = request.getHeader("User-Agent");
        for (String keyWord : IEBrowserKeyWords) {
            if (userAgent.contains(keyWord)) {
                //IE内核浏览器，统一为UTF-8编码显示
                return URLEncoder.encode(filename, "UTF-8");
            }
        }
        //火狐等其它浏览器统一为ISO-8859-1编码显示
        return new String(filename.getBytes("UTF-8"), "ISO-8859-1");
    }

}
