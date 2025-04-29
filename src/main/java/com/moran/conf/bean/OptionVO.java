package com.moran.conf.bean;

import lombok.Data;

@Data
public class OptionVO {

    private String label;

    private Object value;

    public static OptionVO convert(Object value, String label) {
        OptionVO vo = new OptionVO();
        vo.setLabel(label);
        vo.setValue(value);
        return vo;
    }
}
