package com.fikky.models.acl;

import javax.persistence.*;

@Entity
@Table(name = "acl_class")
public class AclClass {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "class", length = 100, nullable = false, unique = true)
    private String className;


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
