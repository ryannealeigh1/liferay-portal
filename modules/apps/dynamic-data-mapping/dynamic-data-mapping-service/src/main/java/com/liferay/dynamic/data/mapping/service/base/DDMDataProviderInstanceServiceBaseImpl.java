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

package com.liferay.dynamic.data.mapping.service.base;

import com.liferay.dynamic.data.mapping.model.DDMDataProviderInstance;
import com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceService;
import com.liferay.dynamic.data.mapping.service.persistence.DDMDataProviderInstanceFinder;
import com.liferay.dynamic.data.mapping.service.persistence.DDMDataProviderInstanceLinkPersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMDataProviderInstancePersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the ddm data provider instance remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.dynamic.data.mapping.service.impl.DDMDataProviderInstanceServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.dynamic.data.mapping.service.impl.DDMDataProviderInstanceServiceImpl
 * @see com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceServiceUtil
 * @generated
 */
public abstract class DDMDataProviderInstanceServiceBaseImpl
	extends BaseServiceImpl implements DDMDataProviderInstanceService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceServiceUtil} to access the ddm data provider instance remote service.
	 */

	/**
	 * Returns the ddm data provider instance local service.
	 *
	 * @return the ddm data provider instance local service
	 */
	public com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceLocalService getDDMDataProviderInstanceLocalService() {
		return ddmDataProviderInstanceLocalService;
	}

	/**
	 * Sets the ddm data provider instance local service.
	 *
	 * @param ddmDataProviderInstanceLocalService the ddm data provider instance local service
	 */
	public void setDDMDataProviderInstanceLocalService(
		com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceLocalService ddmDataProviderInstanceLocalService) {
		this.ddmDataProviderInstanceLocalService = ddmDataProviderInstanceLocalService;
	}

	/**
	 * Returns the ddm data provider instance remote service.
	 *
	 * @return the ddm data provider instance remote service
	 */
	public DDMDataProviderInstanceService getDDMDataProviderInstanceService() {
		return ddmDataProviderInstanceService;
	}

	/**
	 * Sets the ddm data provider instance remote service.
	 *
	 * @param ddmDataProviderInstanceService the ddm data provider instance remote service
	 */
	public void setDDMDataProviderInstanceService(
		DDMDataProviderInstanceService ddmDataProviderInstanceService) {
		this.ddmDataProviderInstanceService = ddmDataProviderInstanceService;
	}

	/**
	 * Returns the ddm data provider instance persistence.
	 *
	 * @return the ddm data provider instance persistence
	 */
	public DDMDataProviderInstancePersistence getDDMDataProviderInstancePersistence() {
		return ddmDataProviderInstancePersistence;
	}

	/**
	 * Sets the ddm data provider instance persistence.
	 *
	 * @param ddmDataProviderInstancePersistence the ddm data provider instance persistence
	 */
	public void setDDMDataProviderInstancePersistence(
		DDMDataProviderInstancePersistence ddmDataProviderInstancePersistence) {
		this.ddmDataProviderInstancePersistence = ddmDataProviderInstancePersistence;
	}

	/**
	 * Returns the ddm data provider instance finder.
	 *
	 * @return the ddm data provider instance finder
	 */
	public DDMDataProviderInstanceFinder getDDMDataProviderInstanceFinder() {
		return ddmDataProviderInstanceFinder;
	}

	/**
	 * Sets the ddm data provider instance finder.
	 *
	 * @param ddmDataProviderInstanceFinder the ddm data provider instance finder
	 */
	public void setDDMDataProviderInstanceFinder(
		DDMDataProviderInstanceFinder ddmDataProviderInstanceFinder) {
		this.ddmDataProviderInstanceFinder = ddmDataProviderInstanceFinder;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.kernel.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.kernel.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Returns the ddm data provider instance link local service.
	 *
	 * @return the ddm data provider instance link local service
	 */
	public com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceLinkLocalService getDDMDataProviderInstanceLinkLocalService() {
		return ddmDataProviderInstanceLinkLocalService;
	}

	/**
	 * Sets the ddm data provider instance link local service.
	 *
	 * @param ddmDataProviderInstanceLinkLocalService the ddm data provider instance link local service
	 */
	public void setDDMDataProviderInstanceLinkLocalService(
		com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceLinkLocalService ddmDataProviderInstanceLinkLocalService) {
		this.ddmDataProviderInstanceLinkLocalService = ddmDataProviderInstanceLinkLocalService;
	}

	/**
	 * Returns the ddm data provider instance link persistence.
	 *
	 * @return the ddm data provider instance link persistence
	 */
	public DDMDataProviderInstanceLinkPersistence getDDMDataProviderInstanceLinkPersistence() {
		return ddmDataProviderInstanceLinkPersistence;
	}

	/**
	 * Sets the ddm data provider instance link persistence.
	 *
	 * @param ddmDataProviderInstanceLinkPersistence the ddm data provider instance link persistence
	 */
	public void setDDMDataProviderInstanceLinkPersistence(
		DDMDataProviderInstanceLinkPersistence ddmDataProviderInstanceLinkPersistence) {
		this.ddmDataProviderInstanceLinkPersistence = ddmDataProviderInstanceLinkPersistence;
	}

	public void afterPropertiesSet() {
	}

	public void destroy() {
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return DDMDataProviderInstanceService.class.getName();
	}

	protected Class<?> getModelClass() {
		return DDMDataProviderInstance.class;
	}

	protected String getModelClassName() {
		return DDMDataProviderInstance.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = ddmDataProviderInstancePersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceLocalService.class)
	protected com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceLocalService ddmDataProviderInstanceLocalService;
	@BeanReference(type = DDMDataProviderInstanceService.class)
	protected DDMDataProviderInstanceService ddmDataProviderInstanceService;
	@BeanReference(type = DDMDataProviderInstancePersistence.class)
	protected DDMDataProviderInstancePersistence ddmDataProviderInstancePersistence;
	@BeanReference(type = DDMDataProviderInstanceFinder.class)
	protected DDMDataProviderInstanceFinder ddmDataProviderInstanceFinder;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserService.class)
	protected com.liferay.portal.kernel.service.UserService userService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceLinkLocalService.class)
	protected com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceLinkLocalService ddmDataProviderInstanceLinkLocalService;
	@BeanReference(type = DDMDataProviderInstanceLinkPersistence.class)
	protected DDMDataProviderInstanceLinkPersistence ddmDataProviderInstanceLinkPersistence;
}