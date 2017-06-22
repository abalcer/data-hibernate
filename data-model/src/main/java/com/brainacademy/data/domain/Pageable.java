package com.brainacademy.data.domain;

public class Pageable {
    private int pageNumber;
    private int pageSize;
    private int offset;

    public Pageable(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public Pageable first() {
        return new Pageable(0, pageSize);
    }

    public Pageable next() {
        return new Pageable(getPageNumber() + 1, getPageSize());
    }

    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    private Pageable previous() {
        return getPageNumber() == 0 ? this : new Pageable(getPageNumber() - 1, getPageSize());
    }

    private boolean hasPrevious() {
        return getPageNumber() > 0;
    }
}
