package cn.crabapples.application.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseForm {
    protected String id;
    protected int pageIndex;
    protected int pageSize;
}
