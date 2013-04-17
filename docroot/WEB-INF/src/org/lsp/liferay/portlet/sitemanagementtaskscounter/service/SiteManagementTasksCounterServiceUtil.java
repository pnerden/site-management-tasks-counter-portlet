package org.lsp.liferay.portlet.sitemanagementtaskscounter.service;

import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;


public class SiteManagementTasksCounterServiceUtil {

	public static int getPendingPersonalTasks(HttpServletRequest request) throws PortalException, SystemException {
		SiteManagementTasksCounterService service = new SiteManagementTasksCounterServiceImpl(request);
		return service.getPendingPersonalTasks();
	}
	
	public static int getPendingUserRolesTasks(HttpServletRequest request) throws PortalException, SystemException {
		SiteManagementTasksCounterService service = new SiteManagementTasksCounterServiceImpl(request);
		return service.getPendingUserRolesTasks();
	}
	
	public static int getPendingMembershipRequests(long groupId, HttpServletRequest request) throws PortalException, SystemException {
		SiteManagementTasksCounterService service = new SiteManagementTasksCounterServiceImpl(groupId, request);
		return service.getPendingMembershipRequests();
	}
	
}
