<%@ include file="/init.jsp" %>

<c:if test="${userId > 0}">
	<%
		int pendingPersonalTasksCount = SiteManagementTasksCounterServiceUtil.getPendingPersonalTasks(request);
		int pendingUserRolesTasksCount = SiteManagementTasksCounterServiceUtil.getPendingUserRolesTasks(request);
		pageContext.setAttribute("pendingTotalTasksCount", pendingPersonalTasksCount+pendingUserRolesTasksCount);
		pageContext.setAttribute("pendingTotalTasksText", LanguageUtil.get(pageContext, "there-are-x-pending-tasks").replace("{0}", String.valueOf(pendingPersonalTasksCount+pendingUserRolesTasksCount)));
					
		int pendingMembershipRequestsCount = SiteManagementTasksCounterServiceUtil.getPendingMembershipRequests(groupId, request);
		pageContext.setAttribute("pendingMembershipRequestsCount", pendingMembershipRequestsCount);
		pageContext.setAttribute("pendingMembershipRequestsText", LanguageUtil.get(pageContext, "there-are-x-membership-requests-pending").replace("{0}", String.valueOf(pendingMembershipRequestsCount)));
		
		int totalSiteManagementTasksCount = pendingMembershipRequestsCount;
		pageContext.setAttribute("totalSiteManagementTasksCount", totalSiteManagementTasksCount);
		pageContext.setAttribute("totalSiteManagementTasksText", LanguageUtil.get(pageContext, "there-are-x-pending-tasks").replace("{0}", String.valueOf(pendingMembershipRequestsCount)));
	%>
</c:if>