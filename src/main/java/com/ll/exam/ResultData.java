//범용적으로 쓰임.

package com.ll.exam;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ResultData<T> {

    private String resultCode;
    private final String msg;
    private T data;

//    public ResultData(String resultCode, String msg, T data) {
//        this.resultCode = resultCode;
//        this.msg = msg;
//        this.data = data;
//    }
}
