package cn.kgc57.demo.uploadfilesspringboot.service;

import cn.kgc57.demo.uploadfilesspringboot.pojo.UserInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public interface UserService {

    Boolean insertUser(List<UserInfo> userInfos);


}
