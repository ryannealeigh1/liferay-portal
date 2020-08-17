/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import {actionDefinition} from '../actions/app.es';

export const initialState = {
	breadcrumbs: null,
	loading: false,
	error: null,
	spritemap: null,
	basename: '/',
	basePathUrl: '/',
	history: null,
	areasEndpoint: null,
	foldersEndpoint: null,
};

export default function reducer(state = initialState, action) {
	switch (action.type) {
		case actionDefinition.SET_ERROR:
			return {
				...state,
				error: action.payload,
			};
		case actionDefinition.INITIALIZE:
			return {
				...state,
				areasEndpoint: action.payload.areasEndpoint,
				foldersEndpoint: action.payload.foldersEndpoint,
				spritemap: action.payload.spritemap,
				basename: action.payload.basename,
				basePathUrl: action.payload.basePathUrl,
				history: action.payload.history,
			};
		case actionDefinition.UPDATE_BREADCRUMBS:
			return {
				...state,
				breadcrumbs: action.payload,
			};
		case actionDefinition.SET_HISTORY:
			return {
				...state,
				history: action.payload,
			};
		case actionDefinition.SET_BASE_PATH_URL:
			return {
				...state,
				basePathUrl: action.payload,
			};
		case actionDefinition.SET_LOADING:
			return {
				...state,
				loading: action.payload,
			};
		case actionDefinition.SET_SPRITEMAP:
			return {
				...state,
				spritemap: action.payload,
			};
		case actionDefinition.SET_BASENAME:
			return {
				...state,
				basename: action.payload,
			};
		default:
			return state;
	}
}
