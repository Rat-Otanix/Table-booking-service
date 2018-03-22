package com.javarightnow.reservation.dataobject.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * If you have a <b>find method</b> in your controller, you can get this Dto as a parameter in your method.
 *
 * @author hadi
 */

public class SimplePageRequestDTO extends BaseRequestDTO {

    public static final int DEFAULT_PAGE = 0;
    public static final int DEFAULT_SIZE = 30;

    @Min(0)
    private int page = DEFAULT_PAGE;

    @Min(1)
    @Max(40)
    private int size = DEFAULT_SIZE;

    public SimplePageRequestDTO() {
    }

    public SimplePageRequestDTO(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
