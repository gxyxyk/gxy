package lrm.com.mapper;

import lrm.com.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface IUserMapper {
    @Select("select * from user where username=#{username} and password=#{password}")
    User loginUser(User user);
    @Select("select * from user where id = (select user_id from blog_user where blog_id=#{id})")
    User user(Integer id);
    @Insert("Insert into blog_user values(#{b_id},#{u_id})")
    void blog_useradd(@Param("b_id") Integer b_id,@Param("u_id") Integer u_id);
}
