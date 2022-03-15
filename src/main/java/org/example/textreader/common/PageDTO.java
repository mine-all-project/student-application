package org.example.textreader.common;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PageDTO {
    protected int pageIndex;
    protected int pageSize = DIC.PAGE_SIZE;
    protected long dataCount;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
