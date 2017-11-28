package com.bidanet.springmvc.demo.jkbuilder.type;

import com.bidanet.springmvc.demo.jkbuilder.data.JkNameValueData;

import java.util.List;

/**
 * 接口数据
 */
public interface JkTypeDataSource {
    /**
     * 加载数据
     * @param key 默认为null
     * @return
     */
    List<JkNameValueData> search(String key) ;
}
