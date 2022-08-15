package com.czy.study.netty.serialize.jdk;

import java.io.Serializable;

public class SubScribeResp implements Serializable {
    private static final long serialVersionUID = -4180931518847538060L;

    private int subRespID;

    private int respCode;

    private String desc;

    public int getSubRespID() {
        return subRespID;
    }

    public void setSubRespID(int subRespID) {
        this.subRespID = subRespID;
    }

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "SubScribeResp{" +
                "subRespID=" + subRespID +
                ", respCode=" + respCode +
                ", desc='" + desc + '\'' +
                '}';
    }
}
