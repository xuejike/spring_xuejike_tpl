package com.bidanet.springmvc.demo.jkbuilder.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JkUploadFile {
    private String url;
    private String filename;

    public JkUploadFile(String url, String filename) {
        this.url = url;
        this.filename = filename;
    }
}
