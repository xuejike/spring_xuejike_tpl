package com.bidanet.springmvc.demo.jkbuilder.type;

import com.bidanet.springmvc.demo.jkbuilder.JkBuilder;
import com.bidanet.springmvc.demo.jkbuilder.annotation.JkFormGroup;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FormFieldGroup  {
    private String id;
    private JkFormGroup group;
    private List<FormFieldInfo> items=new ArrayList<>(1);


    public FormFieldGroup(String id) {
        this.id = id;
    }

    public FormFieldGroup(String id, FormFieldInfo info) {
        this.id = id;
        this.items.add(info);
    }
    public void addItem(FormFieldInfo formFieldInfo){
        items.add(formFieldInfo);
    }

    public String getAttrs(){
        if (group==null){
            return "";
        }
        return JkBuilder.getTplString(group.attrs());
    }
    public String getCssClass(){
        if (group==null){
            return "";
        }
        return JkBuilder.getTplString(group.cssClass());
    }

    public boolean checkBlock(){
        if (items.size()>1){
            return false;
        }else{
            return true;
        }
    }


}
