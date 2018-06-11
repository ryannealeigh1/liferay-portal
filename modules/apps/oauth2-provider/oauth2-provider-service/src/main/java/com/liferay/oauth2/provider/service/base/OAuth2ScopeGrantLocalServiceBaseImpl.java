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

package com.liferay.oauth2.provider.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.oauth2.provider.model.OAuth2ScopeGrant;
import com.liferay.oauth2.provider.service.OAuth2ScopeGrantLocalService;
import com.liferay.oauth2.provider.service.persistence.OAuth2AuthorizationPersistence;
import com.liferay.oauth2.provider.service.persistence.OAuth2ScopeGrantFinder;
import com.liferay.oauth2.provider.service.persistence.OAuth2ScopeGrantPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the o auth2 scope grant local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.oauth2.provider.service.impl.OAuth2ScopeGrantLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.oauth2.provider.service.impl.OAuth2ScopeGrantLocalServiceImpl
 * @see com.liferay.oauth2.provider.service.OAuth2ScopeGrantLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class OAuth2ScopeGrantLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements OAuth2ScopeGrantLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.oauth2.provider.service.OAuth2ScopeGrantLocalServiceUtil} to access the o auth2 scope grant local service.
	 */

	/**
	 * Adds the o auth2 scope grant to the database. Also notifies the appropriate model listeners.
	 *
	 * @param oAuth2ScopeGrant the o auth2 scope grant
	 * @return the o auth2 scope grant that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public OAuth2ScopeGrant addOAuth2ScopeGrant(
		OAuth2ScopeGrant oAuth2ScopeGrant) {
		oAuth2ScopeGrant.setNew(true);

		return oAuth2ScopeGrantPersistence.update(oAuth2ScopeGrant);
	}

	/**
	 * Creates a new o auth2 scope grant with the primary key. Does not add the o auth2 scope grant to the database.
	 *
	 * @param oAuth2ScopeGrantId the primary key for the new o auth2 scope grant
	 * @return the new o auth2 scope grant
	 */
	@Override
	@Transactional(enabled = false)
	public OAuth2ScopeGrant createOAuth2ScopeGrant(long oAuth2ScopeGrantId) {
		return oAuth2ScopeGrantPersistence.create(oAuth2ScopeGrantId);
	}

	/**
	 * Deletes the o auth2 scope grant with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param oAuth2ScopeGrantId the primary key of the o auth2 scope grant
	 * @return the o auth2 scope grant that was removed
	 * @throws PortalException if a o auth2 scope grant with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public OAuth2ScopeGrant deleteOAuth2ScopeGrant(long oAuth2ScopeGrantId)
		throws PortalException {
		return oAuth2ScopeGrantPersistence.remove(oAuth2ScopeGrantId);
	}

	/**
	 * Deletes the o auth2 scope grant from the database. Also notifies the appropriate model listeners.
	 *
	 * @param oAuth2ScopeGrant the o auth2 scope grant
	 * @return the o auth2 scope grant that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public OAuth2ScopeGrant deleteOAuth2ScopeGrant(
		OAuth2ScopeGrant oAuth2ScopeGrant) {
		return oAuth2ScopeGrantPersistence.remove(oAuth2ScopeGrant);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(OAuth2ScopeGrant.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return oAuth2ScopeGrantPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth2.provider.model.impl.OAuth2ScopeGrantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return oAuth2ScopeGrantPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth2.provider.model.impl.OAuth2ScopeGrantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return oAuth2ScopeGrantPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return oAuth2ScopeGrantPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return oAuth2ScopeGrantPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public OAuth2ScopeGrant fetchOAuth2ScopeGrant(long oAuth2ScopeGrantId) {
		return oAuth2ScopeGrantPersistence.fetchByPrimaryKey(oAuth2ScopeGrantId);
	}

	/**
	 * Returns the o auth2 scope grant with the primary key.
	 *
	 * @param oAuth2ScopeGrantId the primary key of the o auth2 scope grant
	 * @return the o auth2 scope grant
	 * @throws PortalException if a o auth2 scope grant with the primary key could not be found
	 */
	@Override
	public OAuth2ScopeGrant getOAuth2ScopeGrant(long oAuth2ScopeGrantId)
		throws PortalException {
		return oAuth2ScopeGrantPersistence.findByPrimaryKey(oAuth2ScopeGrantId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(oAuth2ScopeGrantLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(OAuth2ScopeGrant.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("oAuth2ScopeGrantId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(oAuth2ScopeGrantLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(OAuth2ScopeGrant.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"oAuth2ScopeGrantId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(oAuth2ScopeGrantLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(OAuth2ScopeGrant.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("oAuth2ScopeGrantId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return oAuth2ScopeGrantLocalService.deleteOAuth2ScopeGrant((OAuth2ScopeGrant)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return oAuth2ScopeGrantPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the o auth2 scope grants.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth2.provider.model.impl.OAuth2ScopeGrantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of o auth2 scope grants
	 * @param end the upper bound of the range of o auth2 scope grants (not inclusive)
	 * @return the range of o auth2 scope grants
	 */
	@Override
	public List<OAuth2ScopeGrant> getOAuth2ScopeGrants(int start, int end) {
		return oAuth2ScopeGrantPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of o auth2 scope grants.
	 *
	 * @return the number of o auth2 scope grants
	 */
	@Override
	public int getOAuth2ScopeGrantsCount() {
		return oAuth2ScopeGrantPersistence.countAll();
	}

	/**
	 * Updates the o auth2 scope grant in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param oAuth2ScopeGrant the o auth2 scope grant
	 * @return the o auth2 scope grant that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public OAuth2ScopeGrant updateOAuth2ScopeGrant(
		OAuth2ScopeGrant oAuth2ScopeGrant) {
		return oAuth2ScopeGrantPersistence.update(oAuth2ScopeGrant);
	}

	/**
	 */
	@Override
	public void addOAuth2AuthorizationOAuth2ScopeGrant(
		long oAuth2AuthorizationId, long oAuth2ScopeGrantId) {
		oAuth2AuthorizationPersistence.addOAuth2ScopeGrant(oAuth2AuthorizationId,
			oAuth2ScopeGrantId);
	}

	/**
	 */
	@Override
	public void addOAuth2AuthorizationOAuth2ScopeGrant(
		long oAuth2AuthorizationId, OAuth2ScopeGrant oAuth2ScopeGrant) {
		oAuth2AuthorizationPersistence.addOAuth2ScopeGrant(oAuth2AuthorizationId,
			oAuth2ScopeGrant);
	}

	/**
	 */
	@Override
	public void addOAuth2AuthorizationOAuth2ScopeGrants(
		long oAuth2AuthorizationId, long[] oAuth2ScopeGrantIds) {
		oAuth2AuthorizationPersistence.addOAuth2ScopeGrants(oAuth2AuthorizationId,
			oAuth2ScopeGrantIds);
	}

	/**
	 */
	@Override
	public void addOAuth2AuthorizationOAuth2ScopeGrants(
		long oAuth2AuthorizationId, List<OAuth2ScopeGrant> oAuth2ScopeGrants) {
		oAuth2AuthorizationPersistence.addOAuth2ScopeGrants(oAuth2AuthorizationId,
			oAuth2ScopeGrants);
	}

	/**
	 */
	@Override
	public void clearOAuth2AuthorizationOAuth2ScopeGrants(
		long oAuth2AuthorizationId) {
		oAuth2AuthorizationPersistence.clearOAuth2ScopeGrants(oAuth2AuthorizationId);
	}

	/**
	 */
	@Override
	public void deleteOAuth2AuthorizationOAuth2ScopeGrant(
		long oAuth2AuthorizationId, long oAuth2ScopeGrantId) {
		oAuth2AuthorizationPersistence.removeOAuth2ScopeGrant(oAuth2AuthorizationId,
			oAuth2ScopeGrantId);
	}

	/**
	 */
	@Override
	public void deleteOAuth2AuthorizationOAuth2ScopeGrant(
		long oAuth2AuthorizationId, OAuth2ScopeGrant oAuth2ScopeGrant) {
		oAuth2AuthorizationPersistence.removeOAuth2ScopeGrant(oAuth2AuthorizationId,
			oAuth2ScopeGrant);
	}

	/**
	 */
	@Override
	public void deleteOAuth2AuthorizationOAuth2ScopeGrants(
		long oAuth2AuthorizationId, long[] oAuth2ScopeGrantIds) {
		oAuth2AuthorizationPersistence.removeOAuth2ScopeGrants(oAuth2AuthorizationId,
			oAuth2ScopeGrantIds);
	}

	/**
	 */
	@Override
	public void deleteOAuth2AuthorizationOAuth2ScopeGrants(
		long oAuth2AuthorizationId, List<OAuth2ScopeGrant> oAuth2ScopeGrants) {
		oAuth2AuthorizationPersistence.removeOAuth2ScopeGrants(oAuth2AuthorizationId,
			oAuth2ScopeGrants);
	}

	/**
	 * Returns the oAuth2AuthorizationIds of the o auth2 authorizations associated with the o auth2 scope grant.
	 *
	 * @param oAuth2ScopeGrantId the oAuth2ScopeGrantId of the o auth2 scope grant
	 * @return long[] the oAuth2AuthorizationIds of o auth2 authorizations associated with the o auth2 scope grant
	 */
	@Override
	public long[] getOAuth2AuthorizationPrimaryKeys(long oAuth2ScopeGrantId) {
		return oAuth2ScopeGrantPersistence.getOAuth2AuthorizationPrimaryKeys(oAuth2ScopeGrantId);
	}

	/**
	 */
	@Override
	public List<OAuth2ScopeGrant> getOAuth2AuthorizationOAuth2ScopeGrants(
		long oAuth2AuthorizationId) {
		return oAuth2AuthorizationPersistence.getOAuth2ScopeGrants(oAuth2AuthorizationId);
	}

	/**
	 */
	@Override
	public List<OAuth2ScopeGrant> getOAuth2AuthorizationOAuth2ScopeGrants(
		long oAuth2AuthorizationId, int start, int end) {
		return oAuth2AuthorizationPersistence.getOAuth2ScopeGrants(oAuth2AuthorizationId,
			start, end);
	}

	/**
	 */
	@Override
	public List<OAuth2ScopeGrant> getOAuth2AuthorizationOAuth2ScopeGrants(
		long oAuth2AuthorizationId, int start, int end,
		OrderByComparator<OAuth2ScopeGrant> orderByComparator) {
		return oAuth2AuthorizationPersistence.getOAuth2ScopeGrants(oAuth2AuthorizationId,
			start, end, orderByComparator);
	}

	/**
	 */
	@Override
	public int getOAuth2AuthorizationOAuth2ScopeGrantsCount(
		long oAuth2AuthorizationId) {
		return oAuth2AuthorizationPersistence.getOAuth2ScopeGrantsSize(oAuth2AuthorizationId);
	}

	/**
	 */
	@Override
	public boolean hasOAuth2AuthorizationOAuth2ScopeGrant(
		long oAuth2AuthorizationId, long oAuth2ScopeGrantId) {
		return oAuth2AuthorizationPersistence.containsOAuth2ScopeGrant(oAuth2AuthorizationId,
			oAuth2ScopeGrantId);
	}

	/**
	 */
	@Override
	public boolean hasOAuth2AuthorizationOAuth2ScopeGrants(
		long oAuth2AuthorizationId) {
		return oAuth2AuthorizationPersistence.containsOAuth2ScopeGrants(oAuth2AuthorizationId);
	}

	/**
	 */
	@Override
	public void setOAuth2AuthorizationOAuth2ScopeGrants(
		long oAuth2AuthorizationId, long[] oAuth2ScopeGrantIds) {
		oAuth2AuthorizationPersistence.setOAuth2ScopeGrants(oAuth2AuthorizationId,
			oAuth2ScopeGrantIds);
	}

	/**
	 * Returns the o auth2 scope grant local service.
	 *
	 * @return the o auth2 scope grant local service
	 */
	public OAuth2ScopeGrantLocalService getOAuth2ScopeGrantLocalService() {
		return oAuth2ScopeGrantLocalService;
	}

	/**
	 * Sets the o auth2 scope grant local service.
	 *
	 * @param oAuth2ScopeGrantLocalService the o auth2 scope grant local service
	 */
	public void setOAuth2ScopeGrantLocalService(
		OAuth2ScopeGrantLocalService oAuth2ScopeGrantLocalService) {
		this.oAuth2ScopeGrantLocalService = oAuth2ScopeGrantLocalService;
	}

	/**
	 * Returns the o auth2 scope grant persistence.
	 *
	 * @return the o auth2 scope grant persistence
	 */
	public OAuth2ScopeGrantPersistence getOAuth2ScopeGrantPersistence() {
		return oAuth2ScopeGrantPersistence;
	}

	/**
	 * Sets the o auth2 scope grant persistence.
	 *
	 * @param oAuth2ScopeGrantPersistence the o auth2 scope grant persistence
	 */
	public void setOAuth2ScopeGrantPersistence(
		OAuth2ScopeGrantPersistence oAuth2ScopeGrantPersistence) {
		this.oAuth2ScopeGrantPersistence = oAuth2ScopeGrantPersistence;
	}

	/**
	 * Returns the o auth2 scope grant finder.
	 *
	 * @return the o auth2 scope grant finder
	 */
	public OAuth2ScopeGrantFinder getOAuth2ScopeGrantFinder() {
		return oAuth2ScopeGrantFinder;
	}

	/**
	 * Sets the o auth2 scope grant finder.
	 *
	 * @param oAuth2ScopeGrantFinder the o auth2 scope grant finder
	 */
	public void setOAuth2ScopeGrantFinder(
		OAuth2ScopeGrantFinder oAuth2ScopeGrantFinder) {
		this.oAuth2ScopeGrantFinder = oAuth2ScopeGrantFinder;
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
	 * Returns the o auth2 authorization local service.
	 *
	 * @return the o auth2 authorization local service
	 */
	public com.liferay.oauth2.provider.service.OAuth2AuthorizationLocalService getOAuth2AuthorizationLocalService() {
		return oAuth2AuthorizationLocalService;
	}

	/**
	 * Sets the o auth2 authorization local service.
	 *
	 * @param oAuth2AuthorizationLocalService the o auth2 authorization local service
	 */
	public void setOAuth2AuthorizationLocalService(
		com.liferay.oauth2.provider.service.OAuth2AuthorizationLocalService oAuth2AuthorizationLocalService) {
		this.oAuth2AuthorizationLocalService = oAuth2AuthorizationLocalService;
	}

	/**
	 * Returns the o auth2 authorization persistence.
	 *
	 * @return the o auth2 authorization persistence
	 */
	public OAuth2AuthorizationPersistence getOAuth2AuthorizationPersistence() {
		return oAuth2AuthorizationPersistence;
	}

	/**
	 * Sets the o auth2 authorization persistence.
	 *
	 * @param oAuth2AuthorizationPersistence the o auth2 authorization persistence
	 */
	public void setOAuth2AuthorizationPersistence(
		OAuth2AuthorizationPersistence oAuth2AuthorizationPersistence) {
		this.oAuth2AuthorizationPersistence = oAuth2AuthorizationPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.oauth2.provider.model.OAuth2ScopeGrant",
			oAuth2ScopeGrantLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.oauth2.provider.model.OAuth2ScopeGrant");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return OAuth2ScopeGrantLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return OAuth2ScopeGrant.class;
	}

	protected String getModelClassName() {
		return OAuth2ScopeGrant.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = oAuth2ScopeGrantPersistence.getDataSource();

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

	@BeanReference(type = OAuth2ScopeGrantLocalService.class)
	protected OAuth2ScopeGrantLocalService oAuth2ScopeGrantLocalService;
	@BeanReference(type = OAuth2ScopeGrantPersistence.class)
	protected OAuth2ScopeGrantPersistence oAuth2ScopeGrantPersistence;
	@BeanReference(type = OAuth2ScopeGrantFinder.class)
	protected OAuth2ScopeGrantFinder oAuth2ScopeGrantFinder;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.oauth2.provider.service.OAuth2AuthorizationLocalService.class)
	protected com.liferay.oauth2.provider.service.OAuth2AuthorizationLocalService oAuth2AuthorizationLocalService;
	@BeanReference(type = OAuth2AuthorizationPersistence.class)
	protected OAuth2AuthorizationPersistence oAuth2AuthorizationPersistence;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}