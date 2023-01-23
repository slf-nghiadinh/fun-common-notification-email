package vn.slf.functions;

import com.slf.core.services.repositories.DummyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.slf.models.TemplateRequest;
import vn.slf.models.TemplateResponse;

import java.util.function.Function;
import java.util.logging.Logger;

@Component
public class TemplateFunction implements Function<TemplateRequest, TemplateResponse> {

    private static final Logger LOG = Logger.getLogger(String.valueOf(TemplateFunction.class));
    @Autowired
    DummyRepository dummyService;

    @Override
    public TemplateResponse apply(TemplateRequest request) {
        TemplateResponse response = new TemplateResponse();
        response.setResult(dummyService.get());

        LOG.info("[INFO] " + response.getResult());

        return response;
    }
}
