package com.resource.storage.resource.repository;

import com.resource.storage.resource.entity.ResourceData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<ResourceData,Long> {}
