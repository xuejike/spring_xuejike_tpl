package com.bidanet.springmvc.demo.jkbuilder.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapList<K,L> {
    protected Map<K,List<L>> mapList=new HashMap<>();
    public Map<K, List<L>> getMapList() {
        return mapList;
    }

    public void addItem(K k,L item){
        List<L> list = mapList.get(k);
        if (list==null){
            list=new ArrayList<>();
            mapList.put(k,list);
        }
        list.add(item);
    }
    public L getItem(K k){
        List<L> ls = mapList.get(k);
        if (ls!=null&&ls.size()>0){
            return ls.get(0);
        }else{
            return null;
        }
    }
    public List<L> getItemList(K k){
        return mapList.get(k);
    }


}
