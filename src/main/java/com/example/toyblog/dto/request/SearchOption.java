package com.example.toyblog.dto.request;

import lombok.Data;

@Data
public class SearchOption {
    private static final int MAX_SIZE = 300;
    private Integer page=1;
    private Integer size=10;

    public SearchOption(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }
    public long getOffset(){
        return (long) (Math.max(1,page)-1) * Math.min(size,MAX_SIZE);
    }


}
