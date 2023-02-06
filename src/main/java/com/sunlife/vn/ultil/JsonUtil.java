package com.sunlife.vn.ultil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Strings;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.Base64;
import java.util.Iterator;
import java.util.Map;

public class JsonUtil {
	
	private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
	
	public static Double extractJsonDouble(JsonNode jsonNode) {
		if (jsonNode == null || jsonNode.isNull()) {
			return null;
		}
		return jsonNode.asDouble();
	}

	public static Integer extractJsonInteger(JsonNode jsonNode) {
		if (jsonNode == null || jsonNode.isNull()) {
			return null;
		}
		return jsonNode.asInt();
	}	

	public static Long extractJsonLong(JsonNode jsonNode) {
		if (jsonNode == null || jsonNode.isNull()) {
			return null;
		}
		return jsonNode.asLong();
	}

	public static String extractJsonString(JsonNode jsonNode) {
		if (jsonNode == null || jsonNode.isNull()) {
			return null;
		}
		return jsonNode.asText();
	}

	public static Date extractJsonDate(JsonNode jsonNode) {
		if (jsonNode == null || jsonNode.isNull()) {
			return null;
		}

		return new Date(jsonNode.asLong());
	}
	
	public static Boolean extractJsonBoolean(JsonNode jsonNode) {
		if (jsonNode == null || jsonNode.isNull()) {
			return false;
		}
		return jsonNode.asBoolean();
	}

	public static String mergeUserData(String userData1, String userData2) throws JsonProcessingException {
		try {
			if (Strings.isNullOrEmpty(userData1)) {
				return userData2;
			} 
			if (Strings.isNullOrEmpty(userData2)) {
				return userData1;
			}
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> mapUserData1 = mapper.readValue(userData1, new TypeReference<Map<String, Object>>() {
			});
			Map<String, Object> mapUserData2 = mapper.readValue(userData2, new TypeReference<Map<String, Object>>() {
			});
			mapUserData1.putAll(mapUserData2);
			return mapper.writeValueAsString(mapUserData1);
		} catch (Exception e) {

			return null;
		}
	}
	
	public static JsonNode decodeBase64Data(JsonNode mainNode) {
        Iterator<String> fieldNames = mainNode.fieldNames();
        while(fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            JsonNode node = mainNode.get(fieldNames.next());
            if (node.isArray()) {
                JsonNode arrayNode = decodeBase64Data(node);
                ((ObjectNode) mainNode).replace(fieldName, (ArrayNode) arrayNode);
            }
            else {
                try {
                    byte[] decodedBytes = Base64.getDecoder().decode(node.asText());
                    String decodedString = new String(decodedBytes);
                    ((ObjectNode) mainNode).put(fieldName, decodedString);
                } catch (Exception e) {
                    logger.debug("Node is not base64 string");
                }
            }
            
        }
        
       return mainNode;
    }
}
