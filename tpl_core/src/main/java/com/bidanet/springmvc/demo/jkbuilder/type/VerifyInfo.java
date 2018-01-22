package com.bidanet.springmvc.demo.jkbuilder.type;

import com.bidanet.springmvc.demo.jkbuilder.annotation.JkVerify;
import com.bidanet.springmvc.demo.jkbuilder.annotation.JkVerifyRegExp;
import com.bidanet.springmvc.demo.jkbuilder.annotation.type.JkVerifyType;
import lombok.Data;

@Data
public class VerifyInfo {
    String verify="";
    JkVerifyRegExp[] regExps={};
    String ajaxCls="";

    public VerifyInfo(JkVerify jkVerify) {

        if (jkVerify!=null){
            verify=getVerifyString(jkVerify);
            regExps=jkVerify.regExps();
            ajaxCls=jkVerify.ajaxCls().getName();
        }

    }


    protected String getVerifyString(JkVerify jkVerify){
        if (jkVerify==null){
            return "";
        }else{
            StringBuilder sb=new StringBuilder();
            JkVerifyType[] rules = jkVerify.rules();
            for (JkVerifyType rule : rules) {
                sb.append(rule.toString()).append("|");
            }
            for (String s : jkVerify.rulesTag()) {
                sb.append(s).append("|");
            }
            sb.deleteCharAt(sb.length()-1);
            return sb.toString();


        }

    }
}
