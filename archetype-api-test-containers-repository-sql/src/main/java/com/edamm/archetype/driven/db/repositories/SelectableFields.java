package com.edamm.archetype.driven.db.repositories;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SelectableFields {
  <T> List<Map<String, Object>> findAllWithFields(
      Class<T> clz,
      Set<String> fields,
      Set<String> orderByFields
  );
}

