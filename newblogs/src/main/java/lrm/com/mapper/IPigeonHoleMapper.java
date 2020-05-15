package lrm.com.mapper;

import lrm.com.entity.PigeonHole;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IPigeonHoleMapper {
    @Select("SELECT YEAR(createTime) AS dateTime FROM blog GROUP BY YEAR(createTime)")
    @Results({
            @Result(property = "dateTime",column = "dateTime"),
            @Result(property = "blogs",column = "dateTime",many = @Many(select = "lrm.com.mapper.IBlogsMapper.pigeonHoleBlog"))
    })
    List<PigeonHole> list();
}
