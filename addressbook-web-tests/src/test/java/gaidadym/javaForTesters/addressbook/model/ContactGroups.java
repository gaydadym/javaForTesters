package gaidadym.javaForTesters.addressbook.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "address_in_groups")
public class ContactGroups {
    public ContactGroups(){}

    @Transient
    private Set<ContactGroups> delegate;
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "group_id")
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

    @Override
    public String toString() {
        return "ContactGroups{" +
                "id=" + id +
                ", group_id=" + group_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactGroups that = (ContactGroups) o;
        return id == that.id &&
                group_id == that.group_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, group_id);
    }
}
