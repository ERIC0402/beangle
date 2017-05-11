package org.beangle.ems.security.service;

import java.util.List;

import org.beangle.ems.security.Group;
import org.beangle.ems.security.GroupMember;
import org.beangle.ems.security.GroupMember.Ship;
import org.beangle.ems.security.User;

public interface GroupService {

	public GroupMember getGroupMember(String groupName, User user, Ship ship);

	public Group getGroup(String groupName);
	
	/**
	 * 查询所有用户组
	 * @return
	 */
	public List<Group> findAll();
}
