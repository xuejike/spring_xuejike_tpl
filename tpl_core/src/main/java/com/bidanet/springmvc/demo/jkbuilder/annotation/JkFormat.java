package com.bidanet.springmvc.demo.jkbuilder.annotation;

import com.bidanet.springmvc.demo.jkbuilder.type.JkFormatInterface;

public @interface JkFormat {
    Class<? extends JkFormatInterface> value();
}
