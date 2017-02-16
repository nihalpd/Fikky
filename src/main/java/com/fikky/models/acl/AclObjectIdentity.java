package com.fikky.models.acl;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "acl_object_identity")
public class AclObjectIdentity {

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @Column(name = "object_id_identity", nullable = false)
        private Long objectIdIdentity;

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "object_id_class", referencedColumnName = "id", nullable = false)
        private AclClass objectIdClass;

        @Column(name = "entries_inheriting", nullable = false)
        private Boolean entriesInheriting;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "parent_object", referencedColumnName = "id")
        private AclObjectIdentity parentObject;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "owner_sid", referencedColumnName = "id")
        private AclSid ownerSid;

        @OneToMany(targetEntity = AclEntry.class, fetch = FetchType.LAZY, mappedBy = "aclObjectIdentity", cascade = CascadeType.REMOVE)
        private Set<AclEntry> aclEntries = new HashSet<AclEntry>();



}
