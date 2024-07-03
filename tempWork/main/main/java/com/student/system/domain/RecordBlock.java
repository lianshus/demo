package com.student.system.domain;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * 上链存储对象
 * 
 * @author fabric
 */
public class RecordBlock implements Serializable {
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 溯源码 */
    private String traceno;

    private String hashstr;
    private String E_Addr;
    private String block_key;
    public String getBlock_key() {
        return block_key;
    }

    public String getE_Addr() {
        return E_Addr;
    }

    public void setE_Addr(String e_Addr) {
        E_Addr = e_Addr;
    }

    public void setBlock_key(String block_key) {
        this.block_key = block_key;
    }
    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static RecordBlock fromJSON(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, RecordBlock.class);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTraceno() {
        return traceno;
    }

    public void setTraceno(String traceno) {
        this.traceno = traceno;
    }

    public String getHashstr() {
        return hashstr;
    }

    public void setHashstr(String hashstr) {
        this.hashstr = hashstr;
    }

}
