package lrm.com.mapper;

import lrm.com.entity.Blog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IBlogsMapper {
    @Insert("INSERT INTO blog VALUES (NULL,#{title},#{content},\n" +
            "#{firstpicture},#{flag},0,#{appreciation},#{shareStatment},#{conmentabled}" +
            ",#{published},#{recommend},(SELECT NOW()),(SELECT NOW()),#{description})")
    @Options(useGeneratedKeys = true)
    Integer add(Blog blog);
    @Update("UPDATE blog SET \n" +
            "title=#{title},\n" +
            "content=#{content},\n" +
            "firstpicture=#{firstpicture},\n" +
            "flag=#{flag},\n" +
            "appreciation=#{appreciation},\n" +
            "sharestatment=#{shareStatment},\n" +
            "published=#{published},\n" +
            "description=#{description},\n" +
            "recommend=#{recommend},\n" +
            "updatetime=(SELECT NOW())\n" +
            "WHERE id = #{id}")
    Integer update(Blog blog);
    @Select("select * from blog where id = #{id}")
    @Results(id = "blogResults",value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "title",column = "title"),
            @Result(property = "content",column = "content"),
            @Result(property = "firstpicture",column = "firstpicture"),
            @Result(property = "flag",column = "flag"),
            @Result(property = "views",column = "views"),
            @Result(property = "appreciation",column = "appreciation"),
            @Result(property = "shareStatment",column = "shareStatment"),
            @Result(property = "conmentabled",column = "conmentabled"),
            @Result(property = "recommend",column = "recommend"),
            @Result(property = "createTime",column = "createTime"),
            @Result(property = "updateTime",column = "updateTime"),
            @Result(property = "description",column = "description"),
            @Result(property = "type",column = "id",one = @One(select = "lrm.com.mapper.ITypeMapper.oneType")),
            @Result(property = "tags",column = "id",many =@Many(select = "lrm.com.mapper.ITagMapper.listTag")),
            @Result(property = "user",column = "id",one =@One(select = "lrm.com.mapper.IUserMapper.user")),
            @Result(property = "comments",column = "id",many =@Many(select = "lrm.com.mapper.ICommentMapper.commentsList"))
    })
    Blog blog(Integer id);
    /**
     * 查询全部blog（初始化查询）
     * @return
     */
    @Select("select * from blog")
    @ResultMap("blogResults")
    List<Blog> listsBlog();

    /**
     * 有标题推荐类型三种参数的模糊查询
     * @param typeId
     * @param title
     * @param recommend
     * @return
     */
    @Select("select * from blog where 1=1 " +
            "and title like CONCAT('%',#{title},'%') " +
            "and recommend = #{recommend} " +
            "and id in (SELECT blog_id FROM blog_type WHERE type_id = 10)")
    @ResultMap("blogResults")
    List<Blog> liststypeBlog(@Param("typeId") Integer typeId,@Param("title")String title,@Param("recommend")Boolean recommend);
    /**
     * 只含标题和推荐的模糊查询
     * @param title
     * @param recommend
     * @return
     */
    @Select("select * from blog where 1=1 " +
            "and title like CONCAT('%',#{title},'%')" +
            "and recommend = #{recommend}")
    @ResultMap("blogResults")
    List<Blog> listsnotypeBlog(@Param("title")String title,@Param("recommend")Boolean recommend);
    /**
     * 根据type的id查询当前type下的所对应博客
     */
    @Select("select * from blog where id in(select blog_id from blog_type where type_id=#{id})")
    @ResultMap("blogResults")
    List<Blog> typeBlog(Integer id);
    /**
     * 根据标签id获取当前标签下的所有博客
     * @param id
     * @return
     */
    @Select("select * from blog where id in(select blog_id from blog_tag where tag_id=#{id})")
    @ResultMap("blogResults")
    List<Blog> tagBlog(Integer id);
    /**
     *根据归档实体dataTime获取当前年份的所有的历史博客
     * @param dataTime
     * @return
     */
    @Select("select * from blog where createTime LIKE CONCAT('%',#{dataTime},'%') ")
    @ResultMap("blogResults")
    List<Blog> pigeonHoleBlog(String dataTime);
    /**
     * 最基础查询推荐（完善后变更为按照时间和推荐查询前几个推荐......）
     * @return
     */
    @Select("select * from  blog where recommend = true")
    @ResultMap("blogResults")
    List<Blog> blogRecommend();
    @Select("select * from blog where title LIKE CONCAT('%',#{title},'%') ")
    @ResultMap("blogResults")
    List<Blog> listsIndexBlog(String title);
    @Update("update blog set views=views+1 where id = #{id}")
    void blogViews(Integer id);
}
