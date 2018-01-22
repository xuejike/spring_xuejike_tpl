package com.bidanet.springmvc.demo.jkbuilder.type;

/**
 * 远程校验
 * @author xuejike
 */
public interface JkVerifyRemote {
    /**
     * 验证方法
     * @param value 验证值
     * @return 返回null 验证成功，返回字符串验证失败消息
     */
    String verify(String value);
}
