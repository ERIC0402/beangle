/* Copyright c 2005-2012.
 * Licensed under GNU  LESSER General Public License, Version 3.
 * http://www.gnu.org/licenses
 */
package org.beangle.emsapp.security.action;

import java.sql.Date;

import org.beangle.ems.security.Category;
import org.beangle.ems.security.Group;
import org.beangle.ems.security.User;
import org.beangle.ems.security.service.AuthorityService;
import org.beangle.ems.security.service.UserService;
import org.beangle.ems.web.action.SecurityActionSupport;
import org.beangle.emsapp.security.helper.GroupPropertyExtractor;
import org.beangle.model.Entity;
import org.beangle.model.query.builder.OqlBuilder;
import org.beangle.model.transfer.exporter.PropertyExtractor;

/**
 * 用户组信息维护响应类
 * 
 * @author chaostone 2005-9-29
 */
public class GroupAction extends SecurityActionSupport {

	private UserService userService;

	@Override
	protected String getShortName() {
		return "userGroup";
	}

	protected void indexSetting() {
		put("categories", entityDao.getAll(Category.class));
	}

	protected void editSetting(Entity<?> entity) {
		put("categories", entityDao.getAll(Category.class));
	}

	protected OqlBuilder<Group> getQueryBuilder() {
		OqlBuilder<Group> entityQuery = OqlBuilder.from(getEntityName(), "userGroup");
		if (!isAdmin()) {
			entityQuery.join("userGroup.members", "gm");
			entityQuery.where("gm.user.id=:me and gm.manager=true", getUserId());
		}
		populateConditions(entityQuery);
		entityQuery.limit(getPageLimit()).orderBy("userGroup.id");
		return entityQuery;
	}

	protected PropertyExtractor getPropertyExtractor() {
		return new GroupPropertyExtractor(getTextResource());
	}

	protected String saveAndForward(Entity<?> entity) {
		Group group = (Group) entity;
		if (entityDao.duplicate(Group.class, group.getId(), "name", group.getName())) { return redirect(
				"edit", "error.notUnique"); }
		if (!group.isPersisted()) {
			User creator = userService.get(getUserId());
			userService.createGroup(creator, group);
		} else {
			group.setUpdatedAt(new Date(System.currentTimeMillis()));
			if (!group.isPersisted()) {
				group.setCreatedAt(new Date(System.currentTimeMillis()));
			}
			entityDao.saveOrUpdate(group);
		}
		return redirect("search", "info.save.success");
	}

	/**
	 * 删除一个或多个用户组
	 * 
	 * @return
	 */
	public String remove() {
		Long[] groupIds = getEntityIds(getShortName());
		User curUser = userService.get(getUserId());
		try {
			userService.removeGroup(curUser, entityDao.get(Group.class, groupIds));
		} catch (Exception e) {
			logger.info("removeAndForwad failure", e);
			return redirect("search", "info.delete.failure");
		}
		return redirect("search", "info.remove.success");
	}

	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	protected String getEntityName() {
		return Group.class.getName();
	}

}
