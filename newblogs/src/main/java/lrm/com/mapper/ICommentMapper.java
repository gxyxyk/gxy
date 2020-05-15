package lrm.com.mapper;

import lrm.com.entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ICommentMapper{
    @Select("select * from comment where id in (select comment_id from blog_comment where blog_id = #{id}) and rankid = 0")
    @Results(id = "comment",value = {
            @Result(property = "id",column = "id",id = true),
            @Result(property = "nickname",column = "nickname"),
            @Result(property = "content",column = "content"),
            @Result(property = "avatar",column = "avatar"),
            @Result(property = "createTime",column = "createTime"),
            //一个父级对象对应多个子集对象
            @Result(property = "replyComments",column = "id" ,many=@Many(select = "lrm.com.mapper.ICommentMapper.sList")),
            //一个子级对应一个父级或者没有父级
            @Result(property = "parentComment",column = "rankid",one = @One(select = "lrm.com.mapper.ICommentMapper.fOne")),
            @Result(property = "rankid",column = "rankid")
    })
    List<Comment> commentsList(Integer id);
    @Select("select * from comment where rankid = #{id}")
    @ResultMap("comment")
    List<Comment> sList(Integer id);
    /**
     * 根据id查询单条评论
     * @param id
     * @return
     */
    @Select("select * from comment where id=#{id}")
    @ResultMap("comment")
    Comment fOne(Integer id);
    /**
     * 添加评论
     * @param comment
     */
    @Insert("INSERT INTO COMMENT\n" +
            "VALUES(\n" +
            "NULL,\n" +
            "#{nickname},\n" +
            "#{email},\n" +
            "#{content},\n" +
            "#{avatar},\n" +
            "(SELECT NOW()),\n" +
            "#{rankid}\n" +
            ")")
    @Options(useGeneratedKeys = true)
    void addComment(Comment comment);
    @Insert("Insert into blog_comment values(#{bid},#{cid})")
    void addCb(@Param("bid")Integer bid,@Param("cid")Integer cid);
}
