package com.songbo.filecoincloud.entity;

    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.io.Serializable;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 
    * </p>
*
* @author songbo
* @since 2020-05-23
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class FccUser implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "fcc_user_id", type = IdType.AUTO)
    private Integer fccUserId;

    private String fccUserName;

    private String fccUserTel;

    private String fccUserPwd;

    private Integer fccUserStatus;

    private Integer fccUserLevel;

    private String fccUserMail;

    public FccUser() {
    }

    public FccUser(String fccUserName, String fccUserPwd) {
        this.fccUserName = fccUserName;
        this.fccUserPwd = fccUserPwd;
    }
}
