package org.beangle.website.system.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.beangle.ems.security.User;
import org.beangle.model.pojo.LongIdObject;

/**
 * 站内通知
 * @author GOKU
 *
 */
@Entity(name="org.beangle.website.system.model.Zntz")
public class Zntz extends LongIdObject {

	/**
	 * 标题
	 */
	@Size(max=300)
	@Column(length=300)
	private String title;

	/**
	 * 内容
	 */
	@Size(max=3000)
	@Column(length=3000)
	private String content;

	/**
	 * 附件路径
	 */
	@Size(max=300)
	@Column(length=300)
	private String filePath;

	/**
	 * 发送人
	 */
	private User sender;

	/**
	 * 发送时间
	 */
	private Date time;
	
	/**
	 * 状态
	 */
	private boolean state;
	
	/**
	 * 文件名
	 */
	@Size(max=300)
	@Column(length=300)
	private String fileName;
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
}
