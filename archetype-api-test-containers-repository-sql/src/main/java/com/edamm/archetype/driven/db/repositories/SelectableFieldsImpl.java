package com.edamm.archetype.driven.db.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class SelectableFieldsImpl implements SelectableFields {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public <T> List<Map<String, Object>> findAllWithFields(
      Class<T> clz,
      Set<String> fields,
      Set<String> orderByFields
  ) {

    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Tuple> cq = cb.createTupleQuery();
    Root<T> root = cq.from(clz);

    cq.multiselect(getSelectionFields(fields, root));
    cq.orderBy(getOrders(orderByFields, cb, root));

    List<Tuple> tuples = entityManager.createQuery(cq).getResultList();

    List<Map<String, Object>> results = new ArrayList<>();

    for (Tuple t : tuples) {

      Map<String, Object> map = new LinkedHashMap<>();

      for (String field : fields) {
        map.put(field, t.get(field));
      }

      results.add(map);

    }

    return results;

  }

  private static <T> List<Selection<?>> getSelectionFields(Set<String> fields, Root<T> root) {
    List<Selection<?>> selections = new ArrayList<>();

    for (String field : fields) {
      selections.add(root.get(field).alias(field));
    }

    return selections;
  }

  private static <T> List<Order> getOrders(Set<String> orderByFields, CriteriaBuilder cb,
      Root<T> root) {
    List<Order> orders = new ArrayList<>();
    for (String field : orderByFields) {
      if (field.startsWith("-")) {
        orders.add(cb.desc(root.get(field.substring(1))));
      } else if (field.startsWith("+")) {
        orders.add(cb.asc(root.get(field.substring(1))));
      } else {
        orders.add(cb.asc(root.get(field)));
      }
    }
    return orders;
  }

}

