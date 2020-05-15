package lrm.com.service.impl;

import lrm.com.entity.User;
import lrm.com.mapper.IUserMapper;
import lrm.com.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserMapper userMapper;
    @Override
    public User loginUser(String username,String passwrod) {
        User user= new User();
        user.setPassword(passwrod);
        user.setUsername(username);
        try {
            user = userMapper.loginUser(user);
        } catch (Exception e) {
            user=null;
        }
        return user;
    }
    public void userBlogadd(Integer b_id,Integer u_id){
        userMapper.blog_useradd(b_id,u_id);
    }
}
