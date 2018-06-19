package org.andy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "command", catalog = "akeqi")
public class Command implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id; // 主键ID

	@Column(name = "device_id")
	private int device_id; // 终端设备ID

	@Column(name = "command")
	private int command; // 命令内容

	@Column(name = "status")
	private int status; // 设备状态

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDevice_id() {
		return device_id;
	}

	public void setDevice_id(int device_id) {
		this.device_id = device_id;
	}

	public int getCommand() {
		return command;
	}

	public void setCommand(int command) {
		this.command = command;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Command [id=" + id + ", device_id=" + device_id + ", command=" + command + ", status=" + status + "]";
	}

}
