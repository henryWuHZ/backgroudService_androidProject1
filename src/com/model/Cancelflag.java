package com.model;
import com.original.util.jsf.facelets.FaceletsUtil;
public enum Cancelflag {
	NORMAL(FaceletsUtil.getResourceString(null, "normal"), null),
    CANCEL(FaceletsUtil.getResourceString(null, "cancel"), 1),
    STORAGE_NOT_RETURN(FaceletsUtil.getResourceString(null, "storageNotReturn"), 6),
    ALREADY_RETURN_STOCK(FaceletsUtil.getResourceString(null, "storageAlreadyReturn"), 2),
    CANCEL_EXAMINE(FaceletsUtil.getResourceString(null, "cancelExamine"), 4),
    REFUSE_CANCEL(FaceletsUtil.getResourceString(null, "refuseCancel"), 5);

    private final String s;
    private final Integer i;

    Cancelflag(String s, Integer i) {
        this.s = s;
        this.i = i;
    }

    @Override
    public String toString() {
        return s;
    }

    public String getS() {
        return s;
    }

    public Integer getI() {
        return i;
    }

}
