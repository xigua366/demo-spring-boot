package com.yx.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yx.demo.mapper.UserMapper;
import com.yx.demo.model.entity.User;
import com.yx.demo.model.request.RegisterRequest;
import com.yx.demo.service.UserService;
import com.yx.demo.utils.CommonUtils;
import com.yx.demo.utils.JWTUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public int save(RegisterRequest registerRequest) {

        User user = parseToUser(registerRequest);
        if( user != null){
//           return userMapper.save(user);
            return userMapper.insert(user);
        }else {
            return -1;
        }

    }


    @Override
    public String findByPhoneAndPwd(String phone, String pwd) {

//        User user = userMapper.findByPhoneAndPwd(phone, CommonUtils.MD5(pwd));
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("phone", phone).eq("pwd", CommonUtils.MD5(pwd)));

        if(user == null){
            return null;

        }else {
            String token = JWTUtils.geneJsonWebToken(user);
            return token;
        }

    }

    /**
     * 解析 user 对象
     * @param registerRequest
     * @return
     */
    private User parseToUser(RegisterRequest registerRequest) {

        if(StringUtils.isNotBlank(registerRequest.getName()) && StringUtils.isNotBlank(registerRequest.getPhone()) && StringUtils.isNotBlank(registerRequest.getPwd())){
            User user = new User();
            user.setName(registerRequest.getName());
            user.setHeadImg(getRandomImg());
            user.setCreateTime(new Date());
            user.setPhone(registerRequest.getPhone());
            String pwd = registerRequest.getPwd();
            //MD5加密
            user.setPwd(CommonUtils.MD5(pwd));

            return user;
        }else {
            return null;
        }

    }

    /**
     * 放在CDN上的随机头像
     */
    private static final String [] headImg = {
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/12.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/11.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/13.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/14.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/15.jpeg"
    };

    private String getRandomImg(){
        int size =  headImg.length;
        Random random = new Random();
        int index = random.nextInt(size);
        return headImg[index];
    }

}
