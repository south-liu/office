package com.ht.service.llb;

import com.ht.vo.EmailReceiverVO;
import com.ht.vo.EmailVO;

import java.util.List;

public interface IEmailService {
    void addEmail(EmailVO emailVO);
    void addEmailReceiver(EmailReceiverVO emailReceiverVO);

    List allReceiveEmail(Integer receiver, int page, int limit);
    List allMySendEmail(Integer empId,int page, int limit);

    EmailVO selEmailById(Integer emailId);

    void updIsRead(Integer emailReceiverId,Integer isRead);
    EmailReceiverVO selEmailReceiverById(Integer emailReceiverId);

    void delEmail(Integer emailReceiverId);
}
