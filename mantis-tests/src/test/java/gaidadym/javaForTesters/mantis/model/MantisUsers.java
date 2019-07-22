package gaidadym.javaForTesters.mantis.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "mantis_user_table")
public class MantisUsers {
    public MantisUsers(){}

    @Transient
    private Set<MantisUsers> delegate;
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "username")
   //@Type(type = "text")
    private String username;

    @Column(name = "email")
    //@Type(type = "text")
    private String email;

    public MantisUsers(Collection<MantisUsers> groups) {
        this.delegate = new HashSet<MantisUsers>(groups);
    }


    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }



    public MantisUsers withId(int id) {
        this.id = id;
        return  this;
    }


    public MantisUsers withUsername(String username) {
        this.username = username;
        return this;
    }

    public MantisUsers withEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MantisUsers that = (MantisUsers) o;
        return id == that.id &&
                Objects.equals(username, that.username) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email);
    }

    @Override
    public String toString() {
        return "MantisUsers{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
