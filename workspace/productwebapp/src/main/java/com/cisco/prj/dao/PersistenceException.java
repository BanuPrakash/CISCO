package com.cisco.prj.dao;

public class PersistenceException extends  Exception{
    public PersistenceException(String msg){
        super(msg);
    }

    public PersistenceException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
