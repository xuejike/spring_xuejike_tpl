package com.bidanet.springmvc.demo.jkbuilder.annotation.type;

/**
 * 数据校验
 * @author xuejike
 */

public enum JkVerifyType {
    none,
    required,
    phone,
    email,
    url,
    number,
    identity;

    @Override
    public String toString() {
        if (this==none){
            return "";
        }
        return super.toString();
    }
}
