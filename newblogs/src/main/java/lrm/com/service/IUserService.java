package lrm.com.service;

import lrm.com.entity.User;

public interface IUserService {
    User loginUser(String username,String passwrod);
    public void userBlogadd(Integer b_id,Integer u_id);
}
