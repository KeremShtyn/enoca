package com.example.manager.util.response;

import com.example.manager.util.EnocaUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


public class EnocaGenerator {

    private EnocaGenerator() {
    }

    public static <T> EnocaResponse<T> generateSignResponse(T payload, Object... parametersWithOrderVersionReferenceIdExtras) {

        EnocaResponse<T> tEnocaResponse;

        if (payload instanceof Collection) {
            tEnocaResponse = new EnocaResponse.SignResponseBuilder<T>()
                    .withPayload(payload)
                    .build();
        } else if (payload instanceof Map) {
            Map<String, Object> resultMap = (Map) payload;
            tEnocaResponse = new EnocaResponse.SignResponseBuilder<T>()
                    .withPayload((T) resultMap)
                    .build();

        } else {
            tEnocaResponse = new EnocaResponse.SignResponseBuilder<T>()
                    .withPayload(payload)
                    .build();
        }


        if (parametersWithOrderVersionReferenceIdExtras.length > 0) {
            for (Object object : parametersWithOrderVersionReferenceIdExtras) {
             if (object instanceof String && object == parametersWithOrderVersionReferenceIdExtras[0]) {
                    tEnocaResponse.setVersion((String) object);
                } else if (object instanceof String && object == parametersWithOrderVersionReferenceIdExtras[1]) {
                    tEnocaResponse.setReferenceId((String) object);
                }
            }
        } else {
            tEnocaResponse.setVersion(ResponseContants.SIGN_RESPONSE_VERSION);
            tEnocaResponse.setReferenceId(ResponseContants.SIGN_RESPONSE_REFERENCE + EnocaUtil.formatLocalDateTimeAsString(LocalDateTime.now(), ResponseContants.SIGN_KEY_DATE_TIME_FORMAT));
        }

        return tEnocaResponse;
    }


    private static Sort.Direction generateDirection(String sortDirection) {
        if (sortDirection.equalsIgnoreCase("DESC")) {
            return Sort.Direction.DESC;
        } else {
            return Sort.Direction.ASC;
        }
    }

}
