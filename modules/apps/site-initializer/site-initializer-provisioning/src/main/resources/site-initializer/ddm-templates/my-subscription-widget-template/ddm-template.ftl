<h1 class="pb-3 pb-lg-6">My subscription</h1>

<#if entries?has_content>
	<#assign
		commerceOrder = entries?first
		commerceOrderItems = commerceOrder.getCommerceOrderItems()
	/>

	<#if serviceLocator??>
		<#assign
			commerceSubscriptionEntryService = serviceLocator.findService("com.liferay.commerce.service.CommerceSubscriptionEntryService")

			commerceSubscriptionEntries = commerceSubscriptionEntryService.getCommerceSubscriptionEntries(commerceOrder.getCompanyId(), commerceOrder.getGroupId(), themeDisplay.getUserId(), 0, 1, null)
		/>
	</#if>

	<#if commerceOrderItems?has_content>
		<#assign
			commerceOrderItem = commerceOrderItems?first
			json = commerceOrderItem.getJson()
			cpInstance = commerceOrderItem.fetchCPInstance()
			cpDefinition = cpInstance.getCPDefinition()
			commerceSubscriptionEntry = commerceSubscriptionEntries?first

			nextIterationDate = dateUtil.getDate(commerceSubscriptionEntry.getNextIterationDate(),"dd MMM yyyy - HH:mm:ss", locale)
			startDate = dateUtil.getDate(commerceSubscriptionEntry.getStartDate(),"dd MMM yyyy - HH:mm:ss", locale)
			subscriptionStatusMap = {"-1": "inactive", "0": "active", "1": "suspended", "2": "cancelled", "3": "completed"}

			subscriptionStatus = subscriptionStatusMap[commerceSubscriptionEntry.getSubscriptionStatus()?string]
		/>

		<table class="table">
			<thead>
				<tr>
					<th>Starter kit:</th>
					<th>Domain name:</th>

					<#if serviceLocator??>
						<th>Started date:</th>
						<th>Next iteration date:</th>
						<th>Subscription status:</th>
					</#if>
				</tr>
			</thead>

			<tbody>
				<tr>
					<td class="border-0">
						<img src="${cpDefinition.getDefaultImageThumbnailSrc()}" title="${commerceOrderItem.getName(locale)}" width="80" />
					</td>
					<td class="border-0">
						${getJsonKeyValue(json, "domain")}
					</td>

					<#if serviceLocator??>
						<td class="border-0">
							${startDate}
						</td>
						<td class="border-0">
							${nextIterationDate}
						</td>
						<td class="border-0">
							${subscriptionStatus}
						</td>
					</#if>
				</tr>
			</tbody>
		</table>
	</#if>
</#if>

<#function getJsonKeyValue json key>
	<#if validator.isNotNull(json)>
		<#assign jsonArray = jsonFactoryUtil.createJSONArray(json) />

		<#list 0 ..< jsonArray.length() as i>
				<#if jsonArray.get(i).key == key>
					<#return jsonArray.get(i).value.get(0)?trim>
				</#if>
		</#list>
	</#if>

	<#return "">
</#function>