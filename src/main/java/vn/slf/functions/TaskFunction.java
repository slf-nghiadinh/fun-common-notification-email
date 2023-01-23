package vn.slf.functions;

import com.slf.core.services.repositories.DummyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.slf.models.TaskRequest;
import vn.slf.models.TaskResponse;

import java.util.function.Function;
import java.util.logging.Logger;

@Component
public class TaskFunction implements Function<TaskRequest, TaskResponse> {

    private static final Logger LOG = Logger.getLogger(String.valueOf(TaskFunction.class));
    @Autowired
    DummyRepository dummyService;

    @Override
    public TaskResponse apply(TaskRequest request) {
        TaskResponse response = new TaskResponse();
        response.setResult(dummyService.get());

        LOG.info("[INFO] " + response.getResult());

        return response;
    }
}
