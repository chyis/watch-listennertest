package org.andy.dao;

import org.andy.model.Command;
import org.springframework.stereotype.Repository;

@Repository("commandDao")
public class CommandDao extends AbstractHibernateDao<Command> {

	public CommandDao() {
		super(Command.class);
	}
}
