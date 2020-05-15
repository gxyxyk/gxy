package lrm.com.mapper;

import lrm.com.entity.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * typed的Mapper层
 */
@Mapper
public interface ITypeMapper {
    /**
     * 添加类型方法
     * @param name
     * @return
     */
    @Insert("INSERT INTO `type` VALUES (NULL,#{name})")
    Integer addtype(String name);

    /**
     * type查询全部方法
     * @return
     */
    @Select("select * from type")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "blogs",column = "id",many = @Many(select = "lrm.com.mapper.IBlogsMapper.typeBlog")),
    })
    List<Type> listTypes();

    /**
     * 通过id查询单个值
     * @param id
     * @return
     */
    @Select("select * from type where Id = #{id}")
    Type Onetype(Integer id);

    /**
     * 通过id删除
     * @param id
     * @return
     */
    @Delete("delete from type where id = #{id}")
    Integer deleteType(Integer id);

    /**
     * 通过id修改单条数据
     * @param type
     * @return
     */
    @Update("update type set name = #{name} where id = #{id}")
    Integer updateType(Type type);
    @Insert("INSERT INTO blog_type VALUES (#{bid},#{tid})")
    void addType_Blog(@Param("bid") Integer bid,@Param("tid") Integer tid);
    @Select("select * from type where id = (select type_id from blog_type where blog_id = #{id})")
    Type oneType(Integer id);
    @Delete("delete from blog_type where blog_id=#{id}")
    void deleteOnetype(Integer id);

}
