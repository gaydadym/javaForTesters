package gaidadym.javaForTesters.addressbook.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
@Table(name = "address_in_groups")
public class ContactGroups {
    protected ContactGroups(){}

    @Transient
    private Set<ContactGroups> delegate;
    @Id
    @Column(name = "id")
    private int id;
    private int group_id;

    public ContactGroups(Collection<ContactGroups> groups) {
        this.delegate = new HashSet<ContactGroups>(groups);
    }


    public int getId() {
        return id;
    }



    public ContactGroups withId(int id) {
        this.id = id;
        return  this;
    }

    public int getGroup_id() {
        return group_id;
    }

    public ContactGroups withGroup_id(int group_id) {
        this.group_id = group_id;
        return this;
    }
}
