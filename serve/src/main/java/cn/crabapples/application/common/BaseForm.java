package cn.crabapples.application.common;

import lombok.Getter;
import lombok.Setter;
/**
 * TODO 表单基本属性
 *
 * @author Mr.He
 * 2021/4/9 1:51
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
@Getter
@Setter
public class BaseForm {
    protected String id;
    protected int pageIndex;
    protected int pageSize;
}
