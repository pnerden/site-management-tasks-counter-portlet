package org.lsp.liferay.portlet.sitemanagementtaskscounter.service;

import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowTaskManagerUtil;
import com.liferay.portal.model.MembershipRequestConstants;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.MembershipRequestLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

public class SiteManagementTasksCounterServiceImpl implements SiteManagementTasksCounterService {

	private long userId;
	private long groupId;
	private long companyId;
	private PermissionChecker permissionChecker;
	
	private static final Log log = LogFactoryUtil
			.getLog(SiteManagementTasksCounterServiceImpl.class);
	
	public SiteManagementTasksCounterServiceImpl(HttpServletRequest request) throws PortalException, SystemException {
		this.init(request);
	}
	
	public SiteManagementTasksCounterServiceImpl(long groupId, HttpServletRequest request) throws PortalException, SystemException {
		this.groupId = groupId;		
		this.init(request);
	}
	
	private void init(HttpServletRequest request) throws PortalException, SystemException {
		this.userId = PortalUtil.getUserId(request);
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		this.permissionChecker = themeDisplay.getPermissionChecker();
		ServiceContext serviceContext = ServiceContextFactory.getInstance(request);
		this.companyId = serviceContext.getCompanyId();
		log.debug("Service registered with userId: "+userId+" and companyId: "+companyId);
	}

	@Override
	public int getPendingPersonalTasks() {
		try {
			log.debug("Trying to get pending personal tasks");
			int result = WorkflowTaskManagerUtil.getWorkflowTaskCountByUser(companyId, userId, false);
			log.debug("Found "+result+" pending personal tasks");
			return result;
		} catch (WorkflowException e) {
			log.error(e.getMessage(), e);
		}
		return 0;
	}
	
	@Override
	public int getPendingUserRolesTasks() {
		try {
			log.debug("Trying to get pending group tasks");
			int result = WorkflowTaskManagerUtil.getWorkflowTaskCountByUserRoles(companyId, userId, false);
			log.debug("Found "+result+" pending group tasks");
			return result;
		} catch (WorkflowException e) {
			log.error(e.getMessage(), e);
		}
		return 0;
	}

	@Override
	public int getPendingMembershipRequests() {
		if (permissionChecker.hasPermission(groupId, "com.liferay.portal.model.Group", groupId, "ASSIGN_MEMBERS")) {
			try {
				log.debug("Trying to get pending Membership requests");
				int result = MembershipRequestLocalServiceUtil.searchCount(groupId, MembershipRequestConstants.STATUS_PENDING);
				log.debug("Found "+result+" pending Membership requests");
				return result;
			} catch (SystemException e) {
				log.error(e.getMessage(), e);
			}
		}
		return 0;
	}
	


}
