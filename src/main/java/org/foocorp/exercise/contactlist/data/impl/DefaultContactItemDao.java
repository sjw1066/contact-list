package org.foocorp.exercise.contactlist.data.impl;

import org.foocorp.exercise.contactlist.data.api.ContactItemDao;
import org.foocorp.exercise.contactlist.model.ContactItem;
import org.foocorp.exercise.contactlist.model.Name;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Default JDBC implementation for <code>ContactItemDao</code>
 */
@Repository
public class DefaultContactItemDao extends JdbcDaoSupport implements ContactItemDao {

  @Value("${sql.contact.list}")
  private String contactListSql;

  private RowMapper<ContactItem> rowMapper = new ContactItemMapper();

  @Resource
  private DataSource dataSource;

  /**
   * Set data source (and create JdbcTemplate)
   */
  @PostConstruct
  public void init() {
    setDataSource(dataSource);
  }

  /**
   * @see ContactItemDao#getCallList()
   */
  @Override
  public List<ContactItem> getCallList() {
    return getJdbcTemplate().query(contactListSql, rowMapper);
  }

  /**
   * <code>ContactItem</code> row mapper
   */
  private static class ContactItemMapper implements RowMapper<ContactItem> {

    @Override
    public ContactItem mapRow(ResultSet rs, int i) throws SQLException {
      return new ContactItem(
        new Name(
          rs.getString("first"),
          rs.getString("middle"),
          rs.getString("last")
        ),
        rs.getString("number")
      );
    }
  }
}
