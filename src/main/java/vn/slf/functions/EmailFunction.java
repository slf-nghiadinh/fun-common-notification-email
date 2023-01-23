package vn.slf.functions;

import com.slf.core.services.repositories.DummyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.slf.models.EmailRequest;
import vn.slf.models.EmailResponse;

import java.util.function.Function;
import java.util.logging.Logger;

@Component
public class EmailFunction implements Function<EmailRequest, EmailResponse> {

    private static final Logger LOG = Logger.getLogger(String.valueOf(EmailFunction.class));
    @Autowired
    DummyRepository dummyService;

    @Override
    public EmailResponse apply(EmailRequest request) {
        EmailResponse response = new EmailResponse();
        response.setResult(dummyService.get());

        LOG.info("[INFO] " + response.getResult());

        return response;
    }
}
