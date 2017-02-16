package com.fikky.models.acl;


import javax.persistence.*;

@Entity
@Table(name="acl_sid")
public class AclSid {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "principal", nullable = false)
    private boolean principal;

    @Column(name = "sid",length = 100, nullable = false)
    private String sid;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
