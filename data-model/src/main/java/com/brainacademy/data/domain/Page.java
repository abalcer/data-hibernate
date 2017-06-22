package com.brainacademy.data.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Page<T>
        implements Iterable<T> {

    private Long total;
    private Pageable pageable;

    private List<T> content = new ArrayList<T>();

    public Page(List<T> content, Pageable pageable, Long total) {
        this.content = content;
        this.pageable = pageable;
        this.total = (!content.isEmpty() && pageable != null && pageable.getOffset() + pageable.getPageSize() > total)
                ? pageable.getOffset() + content.size() : total;
    }

    @Override
    public Iterator<T> iterator() {
        return content.iterator();
    }

    public int getTotalPages() {
        return getSize() == 0 ? 1 : (int) Math.ceil((double) total / (double) getSize());
    }

    public int getSize() {
        return pageable == null ? 0 : pageable.getPageSize();
    }

    public long getTotalElements() {
        return total;
    }

    public List<T> getContent() {
        return content;
    }

    public int getNumber() {
        return pageable.getPageNumber();
    }
}
