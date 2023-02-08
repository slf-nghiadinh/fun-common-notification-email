package com.sunlife.vn.functions;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sunlife.vn.models.TaskRequest;
import com.sunlife.vn.models.TaskResponse;
import com.sunlife.vn.services.Impl.EmailServiceImpl;
import com.sunlife.vn.ultil.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.logging.Logger;

@Component
public class TaskFunction implements Function<TaskRequest, TaskResponse> {

    private static final Logger LOG = Logger.getLogger(String.valueOf(TaskFunction.class));
    @Autowired
    EmailServiceImpl emailService;

    @Override
    public TaskResponse apply(TaskRequest request) {

        try {
           ObjectNode data = (ObjectNode) JacksonUtil.objectToJsonNode(request.getMailSend());
            String isAttach = data.get("isAttach").asText();

            if(isAttach.equals("F")){
                data.remove("Attachment");
                ObjectMapper mapper = new ObjectMapper();
                request.setMailSend(mapper.writeValueAsString(data));
        }
            String Result = emailService.sendEmail(request);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        TaskResponse response = new TaskResponse();
       // response.setResult(dummyService.get());

        LOG.info("[INFO] " +"abcd");

        return response;
    }
}
