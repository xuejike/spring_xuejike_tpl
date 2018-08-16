package com.bidanet.springmvc.demo.jkbuilder.data;

import lombok.Data;

import java.util.List;

/**
 * @author xuejike
 */
@Data
public class JkMenu {
    private String text;
    private String icon;
    private String href;
    private Long id;
    private Long pid;
    private boolean target;
    private List<JkMenu> subset;

    public JkMenu() {
    }

    public JkMenu(String text, String icon, String href) {
        this.text = text;
        this.icon = icon;
        this.href = href;
    }
}
