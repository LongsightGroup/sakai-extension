/*
 * Copyright ©2016 Kaltura, Inc.
 */
package org.sakaiproject.kaltura.impl.dao.jdbc.sql;

/**
 * Supplies the SQL strings for site copy service queries
 * 
 * @author Robert Long (rlong @ unicon.net)
 */
public class SiteCopyBatchSql {

    public static String getFindJobsSql() {
        String sql = "SELECT " +
                        "batch_id, " +
                        "source_site_id, " +
                        "target_site_id, " +
                        "status, " +
                        "attempts, " +
                        "created_on " +
                     "FROM " +
                        "kaltura_site_copy_batch " +
                     "WHERE " +
                        "status = ? " +
                     "ORDER BY " +
                        "attempts ASC," +
                        "batch_id ASC";

        return sql;
    }

    public static String getBatchDetailsSql() {
        String sql = "SELECT " +
                        "batch_id, " +
                        "source_site_id, " +
                        "target_site_id, " +
                        "status, " +
                        "attempts, " +
                        "created_on " +
                     "FROM " +
                        "kaltura_site_copy_batch " +
                     "WHERE " +
                        "batch_id = ?";

        return sql;
    }

    public static String getNewBatchSql() {
        String sql = "INSERT INTO " +
                        "kaltura_site_copy_batch " +
                        "(" +
                            "source_site_id, " +
                            "target_site_id, " +
                            "status, " +
                            "attempts, " +
                            "created_on" +
                        ") " +
                        "VALUES (?, ?, ?, ?, NOW())";

        return sql;
    }

    public static String getUpdateBatchSql() {
        String sql = "UPDATE " +
                        "kaltura_site_copy_batch " +
                     "SET " +
                        "source_site_id = ?, " +
                        "target_site_id = ?, " +
                        "status = ?, " +
                        "attempts = ? " +
                     "WHERE " +
                        "batch_id = ?";

        return sql;
    }
}
