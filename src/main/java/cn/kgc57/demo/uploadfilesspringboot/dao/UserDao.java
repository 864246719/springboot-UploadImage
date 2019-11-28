package cn.kgc57.demo.uploadfilesspringboot.dao;

import cn.kgc57.demo.uploadfilesspringboot.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    int insertUsers(@Param("users") List<UserInfo> users);

}
