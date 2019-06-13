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

package com.liferay.dynamic.data.mapping.expression.internal;

import com.liferay.dynamic.data.mapping.constants.DDMConstants;
import com.liferay.dynamic.data.mapping.expression.DDMExpressionFunction;
import com.liferay.dynamic.data.mapping.expression.DDMExpressionFunctionTracker;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentFactory;
import org.osgi.service.component.ComponentInstance;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Leonardo Barros
 */
@Component(immediate = true, service = DDMExpressionFunctionTracker.class)
public class DDMExpressionFunctionTrackerImpl
	implements DDMExpressionFunctionTracker {

	@Activate
	public void activate(BundleContext bundleContext) {
		_componentFactoryMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, ComponentFactory.class,
			"(component.factory=" +
				DDMConstants.EXPRESSION_FUNCTION_FACTORY_NAME + ")",
			(serviceReference, emitter) -> {
				ComponentFactory componentFactory = bundleContext.getService(
					serviceReference);

				ComponentInstance componentInstance =
					componentFactory.newInstance(null);

				DDMExpressionFunction ddmExpressionFunction =
					(DDMExpressionFunction)componentInstance.getInstance();

				bundleContext.ungetService(serviceReference);

				emitter.emit(ddmExpressionFunction.getName());
			});
	}

	@Override
	public Map<String, DDMExpressionFunction> getDDMExpressionFunctions(
		Set<String> functionNames) {

		Map<String, DDMExpressionFunction> ddmExpressionFunctionsMap =
			new HashMap<>(functionNames.size());

		for (String functionName : functionNames) {
			ComponentFactory componentFactory = _componentFactoryMap.getService(
				functionName);

			if (componentFactory != null) {
				ddmExpressionFunctionsMap.put(
					functionName,
					_createDDMExpressionFunction(componentFactory));
			}
		}

		return ddmExpressionFunctionsMap;
	}

	/**
	 * @deprecated As of Mueller (7.2.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public void ungetDDMExpressionFunctions(
		Map<String, DDMExpressionFunction> ddmExpressionFunctionsMap) {
	}

	@Deactivate
	protected void deactivate() {
		_componentFactoryMap.close();
	}

	private DDMExpressionFunction _createDDMExpressionFunction(
		ComponentFactory componentFactory) {

		ComponentInstance componentInstance = componentFactory.newInstance(
			null);

		DDMExpressionFunction ddmExpressionFunction =
			(DDMExpressionFunction)componentInstance.getInstance();

		componentInstance.dispose();

		return ddmExpressionFunction;
	}

	private ServiceTrackerMap<String, ComponentFactory> _componentFactoryMap;

}