package lrm.com.entity;

import lrm.com.util.DateUtils;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 博客实体
 */
public class Blog {
    @Column(name = "id")
    @Id
    @GeneratedValue(generator = "JDBC")
    //博客id
    private Integer id;
    //博客标题
    private String title;
    //博客内容
    private String content;
    //博客图片
    private String firstpicture;
    //标签
    private String flag;
    //浏览次数
    private Integer views;
    //赞赏是否开启
    private boolean appreciation;
    //转载声明是否开启
    private boolean shareStatment;
    //评论是否开启
    private boolean conmentabled;
    //是否发布
    private boolean published;
    //是否推荐
    private boolean recommend;
    //发布时间
    private Date createTime;
    private String createTimeStr;
    //更新时间
    private Date updateTime;
    private String updateTimeStr;
    //一个博客可有多个评论
    private List<Comment> comments = new ArrayList<Comment>();
    //一个博客对应一个用户
    private User user;
    //一个博客对应多个标签
    private List<Tag> tags = new ArrayList<Tag>();
    //一个博客对应一个类型
    private Type type;
    private String description;
    public Blog() {
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstpicture='" + firstpicture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", shareStatment=" + shareStatment +
                ", conmentabled=" + conmentabled +
                ", published=" + published +
                ", recommend=" + recommend +
                ", createTime=" + createTime +
                ", createTimeStr='" + createTimeStr + '\'' +
                ", updateTime=" + updateTime +
                ", updateTimeStr='" + updateTimeStr + '\'' +
                ", comments=" + comments +
                ", user=" + user +
                ", tags=" + tags +
                ", type=" + type +
                ", description='" + description + '\'' +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdateTimeStr() {
        if (updateTime!=null){
            updateTimeStr = DateUtils.date2String(updateTime,"yyyy-MM-dd HH:mm:ss");
        }
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    public String getCreateTimeStr() {
        if (createTime!=null){
            createTimeStr = DateUtils.date2String(createTime,"yyyy-MM-dd HH:mm:ss");
        }
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstpicture() {
        return firstpicture;
    }

    public void setFirstpicture(String firstpicture) {
        this.firstpicture = firstpicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShareStatment() {
        return shareStatment;
    }

    public void setShareStatment(boolean shareStatment) {
        this.shareStatment = shareStatment;
    }

    public boolean isConmentabled() {
        return conmentabled;
    }

    public void setConmentabled(boolean conmentabled) {
        this.conmentabled = conmentabled;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
