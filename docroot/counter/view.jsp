<%@ include file="/counter/init.jsp" %>

<c:if test="${userId > 0}">

<c:if test="${pendingTotalTasksCount > 0}">
	<span class="site-management-tasks-counter-portlet" id="pending-total-tasks-counter" title="${pendingTotalTasksText}">${pendingTotalTasksCount}</span>
	<script type="text/javascript" charset="utf-8">
		AUI().use(function(A) {
			var displayname = A.one('#dockbar .user-fullname').get('innerHTML');
			A.one('#dockbar .user-fullname').empty();
			A.one('#dockbar .user-fullname').append(A.one("#pending-total-tasks-counter")).append(displayname);
		});
	</script>
</c:if>

<c:if test="${pendingMembershipRequestsCount > 0}">
	<span class="site-management-tasks-counter-portlet" id="pending-membership-requests-counter" title="${pendingMembershipRequestsText}">${pendingMembershipRequestsCount}</span>
	<script type="text/javascript" charset="utf-8">
		AUI().use(function(A) {
			var managesitemembershipslink = A.one('#dockbar .manage-site-memberships a').get('innerHTML');
			A.one('#dockbar .manage-site-memberships a').empty();
			A.one('#dockbar .manage-site-memberships a').append(A.one("#pending-membership-requests-counter")).append(managesitemembershipslink);
		});
	</script>
</c:if>

<c:if test="${totalSiteManagementTasksCount > 0}">
	<span class="site-management-tasks-counter-portlet" id="total-site-management-tasks-counter" title="${totalSiteManagementTasksText}">${totalSiteManagementTasksCount}</span>
	<script type="text/javascript" charset="utf-8">
		AUI().use(function(A) {
			var managecontentlink = A.one('#dockbar .manage-content a').get('innerHTML');
			A.one('#dockbar .manage-content a').empty();
			A.one('#dockbar .manage-content a').append(A.one("#total-site-management-tasks-counter")).append(managecontentlink);
		});
	</script>
</c:if>

</c:if>