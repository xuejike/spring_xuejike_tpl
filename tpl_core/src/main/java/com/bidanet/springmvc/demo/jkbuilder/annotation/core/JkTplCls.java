package com.bidanet.springmvc.demo.jkbuilder.annotation.core;

import com.bidanet.springmvc.demo.jkbuilder.model.JkTplText;

public @interface JkTplCls {
    Class<? extends JkTplText> value();
}
