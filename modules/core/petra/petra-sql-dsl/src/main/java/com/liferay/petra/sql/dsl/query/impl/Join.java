/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.petra.sql.dsl.query.impl;

import com.liferay.petra.sql.dsl.BaseTable;
import com.liferay.petra.sql.dsl.ast.ASTNodeListener;
import com.liferay.petra.sql.dsl.ast.impl.BaseASTNode;
import com.liferay.petra.sql.dsl.expression.Predicate;
import com.liferay.petra.sql.dsl.query.JoinStep;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author Preston Crary
 */
public class Join extends BaseASTNode implements JoinStep {

	public Join(
		JoinStep joinStep, JoinType joinType, BaseTable<?> baseTable,
		Predicate onPredicate) {

		super(joinStep);

		_joinType = Objects.requireNonNull(joinType);
		_baseTable = Objects.requireNonNull(baseTable);
		_onPredicate = Objects.requireNonNull(onPredicate);
	}

	public JoinType getJoinType() {
		return _joinType;
	}

	public Predicate getOnPredicate() {
		return _onPredicate;
	}

	public BaseTable<?> getTable() {
		return _baseTable;
	}

	@Override
	protected void doToSQL(
		Consumer<String> consumer, ASTNodeListener astNodeListener) {

		consumer.accept(String.valueOf(_joinType));

		consumer.accept(" join ");

		_baseTable.toSQL(consumer, astNodeListener);

		consumer.accept(" on ");

		_onPredicate.toSQL(consumer, astNodeListener);
	}

	private final BaseTable<?> _baseTable;
	private final JoinType _joinType;
	private final Predicate _onPredicate;

}