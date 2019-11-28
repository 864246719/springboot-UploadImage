package cn.kgc57.demo.uploadfilesspringboot.service;

import cn.kgc57.demo.uploadfilesspringboot.dao.UserDao;
import cn.kgc57.demo.uploadfilesspringboot.pojo.UserInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserInfoImpl implements UserService {
    @Resource
    private UserDao userDao;


    @Override
    public Boolean insertUser(List<UserInfo> userInfos) {
        return userDao.insertUsers(userInfos)>0;
    }

}
