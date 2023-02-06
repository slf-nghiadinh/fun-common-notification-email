package com.sunlife.vn.services;

import com.sunlife.vn.models.TaskRequest;

public interface EmailService {

    public String sendEmail (TaskRequest data) throws Exception;
}
