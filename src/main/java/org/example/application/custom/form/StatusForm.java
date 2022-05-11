package org.example.application.custom.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.application.system.entity.FileInfo;

import java.util.List;

@Getter
@Setter
@ToString
public class StatusForm {
    private String id;
    private String subjectId;
    private String note;
    private String content;
    private List<FileInfo> files;
}
