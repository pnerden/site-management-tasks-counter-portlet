package org.lsp.liferay.portlet.sitemanagementtaskscounter.service;

public interface SiteManagementTasksCounterService {
	
	public int getPendingPersonalTasks();
	public int getPendingUserRolesTasks();
	public int getPendingMembershipRequests();

}
