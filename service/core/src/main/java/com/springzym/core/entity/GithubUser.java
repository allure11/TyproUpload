package com.springzym.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.HashMap;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * GitHub 用户信息
 * </p>
 *
 * @author springzym
 * @since 2022-07-08
 */
@TableName("github_user")
@ApiModel(value = "GithubUser对象", description = "")
public class GithubUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("登录名")
    private String login;

    @ApiModelProperty("节点id")
    private String nodeId;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("用户头像")
    private String avatarUrl;

    @ApiModelProperty("github首页")
    private String htmlUrl;

    @ApiModelProperty("用户类型")
    private String type;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty("修改时间")
    private LocalDateTime gmtModified;

    public GithubUser(HashMap<String, Object> userinfo) {
        this.id = String.valueOf(userinfo.get("id"));
        this.login = String.valueOf(userinfo.get("login"));
        this.nodeId = String.valueOf(userinfo.get("node_id"));
        this.name = String.valueOf(userinfo.get("name"));
        this.avatarUrl = String.valueOf(userinfo.get("avatar_url"));
        this.htmlUrl = String.valueOf(userinfo.get("html_url"));
        this.type = String.valueOf(userinfo.get("type"));
        this.email = String.valueOf(userinfo.get("email"));
        this.gmtCreate = LocalDateTime.ofInstant(Instant.parse(userinfo.get("created_at").toString()), java.time.ZoneId.systemDefault());
        this.gmtModified = LocalDateTime.ofInstant(Instant.parse(userinfo.get("updated_at").toString()), java.time.ZoneId.systemDefault());
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        return "GithubUser{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", nodeId='" + nodeId + '\'' +
                ", name='" + name + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", type='" + type + '\'' +
                ", email='" + email + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
