package com.ht.vo;

import javax.persistence.*;

/**
 * Created by shkstart on 2019/12/3.
 */
@Entity
@Table(name = "noticeReceiver")
public class NoticeReceiverVO {  //通知公告接收人
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer receiverId;
    private Integer noticeId;  //关联管理通知公告
    private Integer receiver;  //接收人
    private Integer isRead;  //是否已读？ 1、已读； 2、未读

    @Override
    public String toString() {
        return "NoticeReceiverVO{" +
                "receiverId=" + receiverId +
                ", noticeId=" + noticeId +
                ", receiver=" + receiver +
                ", isRead=" + isRead +
                '}';
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }
}
