package com.bidanet.springmvc.demo.jkbuilder.type;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class TableColumnInfo {
    private String field;
    private String title;
    private String width;
    private boolean sort;
    private boolean edit;
    private String event;
    private String style;
    private String align;
    private int colspan;
    private int rowspan;
    private String templet;
    private String toolbar;
    @JSONField(serialize = false)
    private int sortIndex;
    @JSONField(serialize = false)
    private int rowIndex=1;
    private String fixed;
    private String type;
}
