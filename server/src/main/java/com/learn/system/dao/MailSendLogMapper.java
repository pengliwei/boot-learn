package com.learn.system.dao;

import com.learn.system.model.entity.MailSendLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MailSendLogMapper {
    Integer updateMailSendLogStatus(@Param("msgId") String msgId, @Param("status") Integer status);

    Integer insert(MailSendLog mailSendLog);

    List<MailSendLog> getMailSendLogsByStatus();

    Integer updateCount(@Param("msgId") String msgId, @Param("date") Date date);
}