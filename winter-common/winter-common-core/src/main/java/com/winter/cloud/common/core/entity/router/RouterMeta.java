package com.winter.cloud.common.core.entity.router;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RouterMeta implements Serializable {
    private static final long serialVersionUID = 5954043220043933509L;

    private String title;
    private String icon;
    private Boolean breadcrumb = true;
}
