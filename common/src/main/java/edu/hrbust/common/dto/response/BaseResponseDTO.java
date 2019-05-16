package edu.hrbust.common.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponseDTO<T> {

    private Integer status;
    private String message;
    private Integer total;
    private T results;

}