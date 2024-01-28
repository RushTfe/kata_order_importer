package com.pgalindo.kata.order.importer.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clear_database_job")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class ClearDatabaseJobEntity extends AbstractJobEntity {

}
