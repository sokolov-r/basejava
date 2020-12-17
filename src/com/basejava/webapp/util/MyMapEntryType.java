package com.basejava.webapp.util;

import com.basejava.webapp.model.Section;
import com.basejava.webapp.model.SectionType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class MyMapEntryType {

    @XmlAttribute
    public SectionType key;

    @XmlValue
    public Section value;
}
