package lrm.com.entity;

import lrm.com.util.DateUtils;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 评论实体
 */
public class Comment {
    @Column(name = "id")
    @Id
    @GeneratedValue(generator = "JDBC")
    //评论id
    private Integer id;
    //评论昵称
    private String nickname;
    //评论邮箱
    private String email;
    //评论内容
    private String content;
    //评论头像
    private String avatar;
    //评论时间
    private Date createTime;
    private String createTimeStr;
    //一个父对象包含多个子对象
    private List<Comment> replyComments =new ArrayList<Comment>();
    //一个子对象只有一个父级对象
    private Comment parentComment;
    //一个评论对应一个用户
    private Blog blog;
    //评论的级别(父级：如果为最高级则rankid为0)
    private Integer rankid;

    public String getCreateTimeStr() {
        if (createTime!=null){
            createTimeStr = DateUtils.date2String(createTime,"yyyy-MM-dd HH:mm:ss");
        }
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public List<Comment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRankid() {
        return rankid;
    }

    public void setRankid(Integer rankid) {
        this.rankid = rankid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Comment() {

    }
}
