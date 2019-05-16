package edu.hrbust.common.utils;

import java.util.UUID;

public class RequestUtils {
    /** requestId的最大长度 */
    public static final int MAX_LENGTH = 32;

    public static final String REQUEST_ID_KEY = "requestId";

    /**
     * 生成requestId
     */
    public static String generateRequestId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}