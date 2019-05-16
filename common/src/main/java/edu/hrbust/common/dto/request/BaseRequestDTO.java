package edu.hrbust.common.dto.request;


import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.Arrays;

public class BaseRequestDTO {
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        Class<? extends BaseRequestDTO> type = this.getClass();
        Field[] declaredFields = type.getDeclaredFields();
        Arrays.stream(declaredFields).forEach(x->{
            x.setAccessible(true);
            String val = null;
            try {
                Object obj = x.get(this);
                if (org.springframework.util.StringUtils.isEmpty(obj)){
                    return;
                }else {
                    val = obj.toString();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            try {
                stringBuilder.append(x.getName()+"="+URLEncoder.encode(val, "UTF-8")+"&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });

        if (declaredFields.length > 0){
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        }
        return stringBuilder.toString();
    }
}

