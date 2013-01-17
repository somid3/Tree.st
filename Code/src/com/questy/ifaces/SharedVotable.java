package com.questy.ifaces;

public interface SharedVotable {

    public Integer getUserId();
    public Integer getNetworkId();
    public Integer getSmartGroupRef();
    public Integer getRef();
    public abstract Integer getUpVotes();
    public abstract Integer getDownVotes();

}