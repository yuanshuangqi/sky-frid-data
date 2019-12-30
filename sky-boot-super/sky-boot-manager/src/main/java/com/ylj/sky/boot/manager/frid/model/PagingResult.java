package com.ylj.sky.boot.manager.frid.model;

import java.io.Serializable;

public class PagingResult implements Serializable{

    /**
     * totalCount
     */
    private long totalCount;

    /**
     * offset
     */
    private long offset;

    /**
     * limit
     */
    private int limit;
}
