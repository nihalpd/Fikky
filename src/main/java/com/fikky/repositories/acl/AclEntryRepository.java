package com.fikky.repositories.acl;

import com.fikky.models.acl.AclEntry;
import org.springframework.data.repository.CrudRepository;

public interface AclEntryRepository extends CrudRepository<AclEntry, Long> {
}
