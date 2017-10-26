package com.cinek.edziennik.helpers;

import java.util.List;

import javax.persistence.TypedQuery;

public class JpaResultHelper {
	public static <T> T getSingleResultOrNull(TypedQuery<T> query) {
	    query.setMaxResults(1);
	    List<T> list = query.getResultList();
	    if (list == null || list.isEmpty()) {
	        return null;
	    }

	    return list.get(0);
	}

}
