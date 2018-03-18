package com.fjalvarez.core;

import javax.persistence.*;

@MappedSuperclass
abstract public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;

    @Version
    private Long version;

    protected BaseEntity() {
        id = null;
    }
}
