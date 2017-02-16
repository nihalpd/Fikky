package com.fikky.models.acl;

import javax.persistence.*;

@Entity
@Table(name = "acl_entry")
public class AclEntry {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ace_order", nullable = false)
    private Integer aceOrder;

    @Column(name = "mask", nullable = false)
    private Integer mask;

    @Column(name = "granting", nullable = false)
    private Boolean granting;

    @Column(name = "audit_success", nullable = false)
    private Boolean auditSuccess;

    @Column(name = "audit_failure", nullable = false)
    private Boolean auditFailure;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "acl_object_identity", referencedColumnName = "id", nullable = false)
    private AclObjectIdentity aclObjectIdentity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sid", referencedColumnName = "id", nullable = false)
    private AclSid sid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAceOrder() {
        return aceOrder;
    }

    public void setAceOrder(Integer aceOrder) {
        this.aceOrder = aceOrder;
    }

    public Integer getMask() {
        return mask;
    }

    public void setMask(Integer mask) {
        this.mask = mask;
    }

    public Boolean getGranting() {
        return granting;
    }

    public void setGranting(Boolean granting) {
        this.granting = granting;
    }

    public Boolean getAuditSuccess() {
        return auditSuccess;
    }

    public void setAuditSuccess(Boolean auditSuccess) {
        this.auditSuccess = auditSuccess;
    }

    public Boolean getAuditFailure() {
        return auditFailure;
    }

    public void setAuditFailure(Boolean auditFailure) {
        this.auditFailure = auditFailure;
    }

    public AclObjectIdentity getAclObjectIdentity() {
        return aclObjectIdentity;
    }

    public void setAclObjectIdentity(AclObjectIdentity aclObjectIdentity) {
        this.aclObjectIdentity = aclObjectIdentity;
    }

    public AclSid getSid() {
        return sid;
    }

    public void setSid(AclSid sid) {
        this.sid = sid;
    }
}
