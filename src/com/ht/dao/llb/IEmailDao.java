package com.ht.dao.llb;

import com.ht.vo.EmailReceiverVO;
import com.ht.vo.EmailVO;
import com.ht.vo.SystemLogVO;

import java.util.List;
import java.util.Map;

public interface IEmailDao {

    int countReceiveEmail(Integer receiver);
    int countMySendEmail(Integer empId);

    List allReceiveEmail(Integer receiver,int page, int limit);
    List<EmailReceiverVO> allReceiveEmail(Integer receiver);
    List allMySendEmail(Integer empId,int page, int limit);

    void addEmail(EmailVO emailVO);
    void addEmailReceiver(EmailReceiverVO emailReceiverVO);

    EmailVO selEmailById(Integer emailId);

    void updIsRead(Integer emailReceiverId,Integer isRead);
    EmailReceiverVO selEmailReceiverById(Integer emailReceiverId);

    void delEmail(Integer emailReceiverId);
}
