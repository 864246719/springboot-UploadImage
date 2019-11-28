package cn.kgc57.demo.uploadfilesspringboot.controller;

import cn.kgc57.demo.uploadfilesspringboot.pojo.UserInfo;
import cn.kgc57.demo.uploadfilesspringboot.service.UserService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserInfoController {

    @Resource
    private UserService userService;

    @RequestMapping("/addUser")
    public void insertUser(UserInfo userInfo, HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam(value = "file") MultipartFile attach[]) throws ServletException, IOException {
        String path ="C:\\Users\\Administrator\\Desktop\\uploadfiles-springboot\\src\\main\\resources\\static\\uploadfiles";
        Boolean flag=false;
        String logoPicPath = null;
        String logoLocPath = null;
        List<UserInfo> userInfos=new ArrayList<UserInfo>();
        for(int i=0;i<attach.length;i++){
            if (!attach[i].isEmpty()) {
                String oldFileName = attach[i].getOriginalFilename();//原文件名
                String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
                int filesize = 500000;
                if (attach[i].getSize() > filesize) {//上传大小不得超过 50k
                    request.setAttribute("error","图片过大");
                    request.getRequestDispatcher("error.html").forward(request,response);
                } else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
                        || prefix.equalsIgnoreCase("jepg") || prefix.equalsIgnoreCase("pneg")) {//上传图片格式
                    String fileName = userInfo.getUserName()+ "a.jpg";//上传LOGO图片命名:apk名称.apk
                    File targetFile = new File(path, oldFileName);
                    if (!targetFile.getParentFile().exists()) {
                        targetFile.getParentFile().mkdirs();
                    }
                    try {
                        attach[i].transferTo(targetFile);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        response.sendRedirect("error.html");
                    }
                    logoPicPath = request.getContextPath()+"/static/uploadfiles/" + fileName;
                    logoLocPath = "C:\\Users\\Administrator\\Desktop\\uploadfiles-springboot\\src\\main\\resources\\static\\uploadfiles" + File.separator + fileName;

                    userInfo.setImgLoc(logoLocPath);
                    userInfo.setImgPath(logoPicPath);
                    userInfo.setUserName("1");
                    userInfos.add(userInfo);
                } else {
                    response.sendRedirect("error.html");
                }
            }
        }
        if(userService.insertUser(userInfos)){
            response.sendRedirect("success.html");
        }else {
            response.sendRedirect("error.html");
        }

    }

}
