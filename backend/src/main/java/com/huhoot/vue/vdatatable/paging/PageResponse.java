package com.huhoot.vue.vdatatable.paging;

import com.huhoot.config.mvc.CustomBodyResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@CustomBodyResponse
@Data
public class PageResponse<T> {
    private List<T> list;
    private long totalElements;

    public <T> PageResponse(List<T> list, long totalElements) {

    }

    public PageResponse() {
        this.list = new ArrayList<>();
    }
}
