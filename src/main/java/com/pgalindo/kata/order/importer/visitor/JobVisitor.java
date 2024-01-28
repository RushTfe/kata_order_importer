package com.pgalindo.kata.order.importer.visitor;

import com.pgalindo.kata.order.importer.model.entity.ClearDatabaseJobEntity;
import com.pgalindo.kata.order.importer.model.entity.ImportJobEntity;

public interface JobVisitor {
    void visit(ClearDatabaseJobEntity clearDatabaseJobEntity);
    void visit(ImportJobEntity importJobEntity);
}
