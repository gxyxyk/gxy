package lrm.com.mapper;

import lrm.com.entity.Tag;
import lrm.com.entity.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * tag的Mapper层
 */
@Mapper
public interface ITagMapper {
    /**
     * 添加类型方法
     * @param name
     * @return
     */
    @Insert("INSERT INTO `tag` VALUES (NULL,#{name})")
    Integer addtag(String name);

    /**
     * tag查询全部方法
     * @return
     */
    @Select("SELECT * FROM tag")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "blogs",column = "id",many = @Many(select = "lrm.com.mapper.IBlogsMapper.tagBlog"))
    })
    List<Tag> listTags();

    /**
     * 通过id查询单个值
     * @param id
     * @return
     */
    @Select("select * from tag where Id = #{id}")
    Tag Onetag(Integer id);

    /**
     * 通过id删除
     * @param id
     * @return
     */
    @Delete("delete from tag where id = #{id}")
    Integer deleteTag(Integer id);
    /**
     * 通过id修改单条数据
     * @return
     */
    @Update("update tag set name = #{name} where id = #{id}")
    Integer updateTag(@Param("id") Integer id,@Param("name") String name);
    @Insert("INSERT INTO blog_tag VALUES (#{bid},#{tid})")
    void addTag_Blog(@Param("bid") Integer bid,@Param("tid") Integer tid);
    @Select("select * from tag where id in (select tag_id from blog_tag where blog_id = #{id})")
    List<Tag> listTag(Integer id);
    @Delete("delete from blog_tag where blog_id=#{id}")
    void deleteOnetag(Integer id);
}
