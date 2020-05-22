package org.example.shiyanshi.config.base;

import lombok.Getter;
import lombok.Setter;
import org.example.shiyanshi.config.groups.*;
import org.example.shiyanshi.entity.SysUser;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
public class BaseForm {
    protected int pageIndex;
    protected int pageSize;
}
