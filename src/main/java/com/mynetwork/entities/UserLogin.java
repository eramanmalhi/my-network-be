package com.mynetwork.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Random;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class UserLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private String emailId;
    private String password;
    private boolean verified;
    private boolean locked;
    private String lockedComments;
    private boolean active;
    private String activeComments;
    private String verificationCode;
    private Long verificationEmailCount;
    @CreatedDate

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private LocalDateTime createdDate;
    @LastModifiedDate

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private LocalDateTime modifiedDate;
    private String comments;

    public UserLogin(Long id, String userName, String emailId, String password, boolean verified, boolean locked, String lockedComments, boolean active, String activeComments, String verificationCode, Long verificationEmailCount, LocalDateTime createdDate, LocalDateTime modifiedDate, String comments) {
        this.id = id;
        this.userName = userName;
        this.emailId = emailId;
        this.password = password;
        this.verified = verified;
        this.locked = locked;
        this.lockedComments=lockedComments;
        this.active = active;
        this.activeComments=activeComments;
        this.verificationCode = verificationCode;
        this.verificationEmailCount = verificationEmailCount;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.comments=comments;
    }

    public UserLogin() {
        this.verificationCode = this.getVerificationCode()==null?String.valueOf(10000 + new Random().nextInt(90000)):this.getVerificationCode();
        this.verificationEmailCount = this.getVerificationEmailCount()==null?0:this.getVerificationEmailCount();
    }
}
