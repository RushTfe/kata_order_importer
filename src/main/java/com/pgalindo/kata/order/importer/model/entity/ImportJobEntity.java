package com.pgalindo.kata.order.importer.model.entity;

import com.pgalindo.kata.order.importer.visitor.JobVisitor;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "import_job")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class ImportJobEntity extends AbstractJobEntity {

    @Override
    public void accept(JobVisitor visitor) {
        visitor.visit(this);
    }

}
