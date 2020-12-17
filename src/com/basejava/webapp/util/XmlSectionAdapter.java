package com.basejava.webapp.util;

import com.basejava.webapp.model.*;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.EnumMap;
import java.util.Map;

public class XmlSectionAdapter extends XmlAdapter<MyMapType, Map<SectionType, Section>> {

    @Override
    public MyMapType marshal(Map<SectionType, Section> arg0) throws Exception {
        MyMapType myMapType = new MyMapType();
        for (Map.Entry<SectionType, Section> entry : arg0.entrySet()) {
            MyMapEntryType myMapEntryType =
                    new MyMapEntryType();
            myMapEntryType.key = entry.getKey();
            myMapEntryType.value = entry.getValue();
            myMapType.entry.add(myMapEntryType);
        }
        return myMapType;
    }

    @Override
    public Map<SectionType, Section> unmarshal(MyMapType arg0) throws Exception {
        Map<SectionType, Section> map = new EnumMap<SectionType, Section>(SectionType.class);
        for (MyMapEntryType myEntryType : arg0.entry) {
            map.put(myEntryType.key, myEntryType.value);
        }
        return map;
    }
}