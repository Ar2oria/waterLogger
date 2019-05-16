package edu.hrbust.waterLogger.vo;

import lombok.Data;

/**
 * @describe:
 * @package: edu.hrbust.waterLogger.vo
 * @dateTime: 2019/5/16 20:06
 * @author: ar2oria
 */
@Data
public class WaterDataChgVO {
    private String appId;
    private String notifyType;
    private Content content;

    @lombok.Data
    public static class Content{
        private String notifyType;
        private String deviceId;
        private String gatewayId;
        private String requestId;
        private Service service;
    }

    @lombok.Data
    public static class Service{
        private String serviceId;
        private String serviceType;
        private Data data;
        private String eventTime;
    }

    @lombok.Data
    public static class Data{
        private String ntu;
        private Integer m;
    }


}
