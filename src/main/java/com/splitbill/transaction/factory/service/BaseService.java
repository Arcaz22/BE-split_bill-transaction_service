package com.splitbill.transaction.factory.service;

import java.util.Map;

public interface BaseService<C, U> {
    Map<String, Object> create(C createDto);

    Map<String, Object> update(U updateDto);

    Map<String, Object> detail(String id);

    Map<String, Object> list(String filter, long offset, int limit, String sortBy, String direction,
            Boolean activeOnly);

    Map<String, Object> delete(String id, Boolean isRestore);
}
