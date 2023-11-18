package com.azure.azure_merge.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 资讯表
 * @TableName news
 */
@TableName(value ="news")
@Data
public class News implements Serializable {
    /**
     * 资讯id
     */
    @TableId(type = IdType.AUTO)
    private Integer newsId;

    /**
     * 网站标题
     */
    private String websiteTitle;

    /**
     * 资讯标题
     */
    private String newsTitle;

    /**
     * 资讯链接
     */
    private String newsLink;

    /**
     * 资讯缩略图
     */
    private String newsThumbnail;

    /**
     * 资讯更新时间
     */
    private String newsUpdatedTime;

    /**
     * 资讯内容
     */
    private String newsContent;

    /**
     * 0 表示未删除  1表示删除
     */
    private Integer isDeleted;

    /**
     * 资讯管理员ID
     */
    private Integer adminId;

    /**
     * 所属区域
     */
    private Integer newsRegionId;

    /**
     * 所属行业
     */
    private Integer newsIndustryId;

    /**
     * 所属类型
     */
    private Integer newsTypeId;

    /**
     * 咨询是否发布 (0表示未发布，1表示已发布)
     */
    private Integer isPublished;

    /**
     * 资讯爬取时间 (yyyy-MM-dd HH:mm)
     */
    private String newsGetTime;

    /**
     * 资讯发布时间
     */
    private LocalDateTime newsPublishedTime;

    /**
     * 
     */
    private String newsAbstract;

    /**
     * 网站网址
     */
    private String websiteUrl;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        News other = (News) that;
        return (this.getNewsId() == null ? other.getNewsId() == null : this.getNewsId().equals(other.getNewsId()))
            && (this.getWebsiteTitle() == null ? other.getWebsiteTitle() == null : this.getWebsiteTitle().equals(other.getWebsiteTitle()))
            && (this.getNewsTitle() == null ? other.getNewsTitle() == null : this.getNewsTitle().equals(other.getNewsTitle()))
            && (this.getNewsLink() == null ? other.getNewsLink() == null : this.getNewsLink().equals(other.getNewsLink()))
            && (this.getNewsThumbnail() == null ? other.getNewsThumbnail() == null : this.getNewsThumbnail().equals(other.getNewsThumbnail()))
            && (this.getNewsUpdatedTime() == null ? other.getNewsUpdatedTime() == null : this.getNewsUpdatedTime().equals(other.getNewsUpdatedTime()))
            && (this.getNewsContent() == null ? other.getNewsContent() == null : this.getNewsContent().equals(other.getNewsContent()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()))
            && (this.getAdminId() == null ? other.getAdminId() == null : this.getAdminId().equals(other.getAdminId()))
            && (this.getNewsRegionId() == null ? other.getNewsRegionId() == null : this.getNewsRegionId().equals(other.getNewsRegionId()))
            && (this.getNewsIndustryId() == null ? other.getNewsIndustryId() == null : this.getNewsIndustryId().equals(other.getNewsIndustryId()))
            && (this.getNewsTypeId() == null ? other.getNewsTypeId() == null : this.getNewsTypeId().equals(other.getNewsTypeId()))
            && (this.getIsPublished() == null ? other.getIsPublished() == null : this.getIsPublished().equals(other.getIsPublished()))
            && (this.getNewsGetTime() == null ? other.getNewsGetTime() == null : this.getNewsGetTime().equals(other.getNewsGetTime()))
            && (this.getNewsPublishedTime() == null ? other.getNewsPublishedTime() == null : this.getNewsPublishedTime().equals(other.getNewsPublishedTime()))
            && (this.getNewsAbstract() == null ? other.getNewsAbstract() == null : this.getNewsAbstract().equals(other.getNewsAbstract()))
            && (this.getWebsiteUrl() == null ? other.getWebsiteUrl() == null : this.getWebsiteUrl().equals(other.getWebsiteUrl()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getNewsId() == null) ? 0 : getNewsId().hashCode());
        result = prime * result + ((getWebsiteTitle() == null) ? 0 : getWebsiteTitle().hashCode());
        result = prime * result + ((getNewsTitle() == null) ? 0 : getNewsTitle().hashCode());
        result = prime * result + ((getNewsLink() == null) ? 0 : getNewsLink().hashCode());
        result = prime * result + ((getNewsThumbnail() == null) ? 0 : getNewsThumbnail().hashCode());
        result = prime * result + ((getNewsUpdatedTime() == null) ? 0 : getNewsUpdatedTime().hashCode());
        result = prime * result + ((getNewsContent() == null) ? 0 : getNewsContent().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        result = prime * result + ((getAdminId() == null) ? 0 : getAdminId().hashCode());
        result = prime * result + ((getNewsRegionId() == null) ? 0 : getNewsRegionId().hashCode());
        result = prime * result + ((getNewsIndustryId() == null) ? 0 : getNewsIndustryId().hashCode());
        result = prime * result + ((getNewsTypeId() == null) ? 0 : getNewsTypeId().hashCode());
        result = prime * result + ((getIsPublished() == null) ? 0 : getIsPublished().hashCode());
        result = prime * result + ((getNewsGetTime() == null) ? 0 : getNewsGetTime().hashCode());
        result = prime * result + ((getNewsPublishedTime() == null) ? 0 : getNewsPublishedTime().hashCode());
        result = prime * result + ((getNewsAbstract() == null) ? 0 : getNewsAbstract().hashCode());
        result = prime * result + ((getWebsiteUrl() == null) ? 0 : getWebsiteUrl().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", newsId=").append(newsId);
        sb.append(", websiteTitle=").append(websiteTitle);
        sb.append(", newsTitle=").append(newsTitle);
        sb.append(", newsLink=").append(newsLink);
        sb.append(", newsThumbnail=").append(newsThumbnail);
        sb.append(", newsUpdatedTime=").append(newsUpdatedTime);
        sb.append(", newsContent=").append(newsContent);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", adminId=").append(adminId);
        sb.append(", newsRegionId=").append(newsRegionId);
        sb.append(", newsIndustryId=").append(newsIndustryId);
        sb.append(", newsTypeId=").append(newsTypeId);
        sb.append(", isPublished=").append(isPublished);
        sb.append(", newsGetTime=").append(newsGetTime);
        sb.append(", newsPublishedTime=").append(newsPublishedTime);
        sb.append(", newsAbstract=").append(newsAbstract);
        sb.append(", websiteUrl=").append(websiteUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}