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

package com.liferay.fragment.renderer;

import com.liferay.fragment.constants.FragmentConstants;
import com.liferay.petra.string.StringPool;

import java.io.IOException;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jorge Ferrer
 */
public interface FragmentRenderer {

	public default String getImagePreviewURL(
		HttpServletRequest httpServletRequest) {

		return StringPool.BLANK;
	}

	public default String getKey() {
		Class<?> clazz = getClass();

		return clazz.getName();
	}

	public String getLabel(Locale locale);

	public default int getType() {
		return FragmentConstants.TYPE_COMPONENT;
	}

	public default boolean isSelectable(HttpServletRequest httpServletRequest) {
		return true;
	}

	public void render(
			FragmentRendererContext fragmentRendererContext,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException;

}