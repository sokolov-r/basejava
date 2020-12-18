package com.basejava.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class ListSection extends Section implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<String> textList;

    public ListSection() {
    }

    public ListSection(List<String> textList) {
        Objects.requireNonNull(textList, "textList must not be null");
        this.textList = textList;
    }

    public List<String> getTextList() {
        return textList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String text : textList) {
            sb.append(text);
            sb.append("\n\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return textList.equals(that.textList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(textList);
    }
}
