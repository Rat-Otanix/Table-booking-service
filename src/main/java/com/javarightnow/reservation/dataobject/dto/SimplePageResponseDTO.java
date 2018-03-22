package com.javarightnow.reservation.dataobject.dto;

import java.util.List;

/**
 * If you have a <b>find method</b> in your controller, you can return this Dto in your method.
 *
 * @author hadi
 */
public class SimplePageResponseDTO<T extends Object> extends BaseResponseDTO {

    private List<T> content;
    private long count;

    public List<T> getContent() {
        return content;
    }

    public SimplePageResponseDTO() {
    }

    public SimplePageResponseDTO(List<T> content, long count) {
        this.content = content;
        this.count = count;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

}
