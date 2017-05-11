package org.beangle.website.common.action;

import java.util.List;

import org.beangle.ems.security.User;
import org.beangle.model.query.builder.OqlBuilder;

/**
 * Chosen Action
 * @author CHII
 *
 */
public class ChosenAction extends BaseCommonAction {

	public String user(){
		String param = get("key");
		String paramValue = "%" + param + "%";
		OqlBuilder<User> query = OqlBuilder.from(User.class, "u");
		query.limit(getPageLimit());
//		query.where("(u.fullname like (:key) or u.name like (:key) or u.department.name like (:key) )", paramValue);
		query.where("(u.fullname like (:key) or u.name like (:key))", paramValue);
		query.where("enabled=true");
		List<User> users = entityDao.search(query);
		put("users", users);
		return forward();
	}
}
