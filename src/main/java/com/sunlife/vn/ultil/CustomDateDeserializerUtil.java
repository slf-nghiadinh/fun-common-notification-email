package com.sunlife.vn.ultil;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;
import org.apache.commons.lang3.time.DateUtils;

import java.io.IOException;

@SuppressWarnings("deprecation")
public class CustomDateDeserializerUtil extends UntypedObjectDeserializer {

    private static final long serialVersionUID = -2275951539867772400L;

    @Override
    public Object deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {

        if (jp.getCurrentTokenId() == JsonTokenId.ID_STRING) {
            try {
                return DateUtils.parseDate(jp.getText(), new String[] {"yyyy-MM-dd HH:mm:ss"});
            } catch (Exception e) {
                return super.deserialize(jp, ctxt);
            }
        } else {
            return super.deserialize(jp, ctxt);
        }
    }
}
