package gaidadym.javaForTesters.addressbook.model;

import com.google.common.collect.ForwardingSet;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.*;

public class Groups extends ForwardingSet<GroupData> {
    @XStreamAlias("group")

    private Set<GroupData> delegate;

    public Groups(Groups groups) {
        this.delegate = new HashSet<GroupData>(groups.delegate);
    }

    public Groups() {
        this.delegate = new HashSet<GroupData>();
    }

    public Groups(Collection<GroupData> groups) {
        this.delegate = new HashSet<GroupData>(groups);
    }

    @Override
    protected Set<GroupData> delegate() {
        return delegate;
    }

    public Groups withAdded (GroupData group){
        Groups groups = new Groups(this);
        groups.add(group);
        return groups;
    }

    public Groups without (GroupData group){
        Groups groups = new Groups(this);
        groups.remove(group);
        return groups;
    }

}
