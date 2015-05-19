/**
 * Copyright 2014 Sakaiproject Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.sakaiproject.kaltura.models.db;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.sakaiproject.kaltura.utils.AuthCodeUtil;

import com.google.gson.annotations.Expose;

/**
 * This is a Kaltura LTI RESTful API authorization, it represents an authorization code object from the database
 * 
 * @author Robert Long (rlong @ unicon.net)
 */
public class KalturaLtiAuthCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Expose
    private Long id;
    @Expose
    private String userId;
    @Expose
    private String authCode;
    @Expose
    private Date dateCreated;
    @Expose
    private Date dateExpires;

    public KalturaLtiAuthCode() {
    }

    public KalturaLtiAuthCode(String userId, String authCode) {
        this(userId, authCode, new Date(), AuthCodeUtil.calculateExpirationDate(new Date()));
    }

    public KalturaLtiAuthCode(String userId, String authCode, Date dateCreated, Date dateExpires) {
        this(null, userId, authCode, dateCreated, dateExpires);
    }

    /**
     * Convenience constructor for partially-created {@link KalturaLtiAuthCode} objects
     * 
     * @param kalturaLtiAuthCode the partial {@link KalturaLtiAuthCode} object
     */
    public KalturaLtiAuthCode(KalturaLtiAuthCode kalturaLtiAuthCode) {
        this(
            kalturaLtiAuthCode.getId(),
            kalturaLtiAuthCode.getUserId(),
            kalturaLtiAuthCode.getAuthCode(),
            kalturaLtiAuthCode.getDateCreated(),
            kalturaLtiAuthCode.getDateExpires()
        );
    }

    /**
     * Full constructor
     * 
     * @param id
     * @param userId
     * @param authCode
     * @param dateCreated
     * @param dateExpires
     */
    public KalturaLtiAuthCode(Long id, String userId, String authCode, Date dateCreated, Date dateExpires) {
        this.id = id;
        this.userId = userId;

        if (StringUtils.isBlank(authCode)) {
            this.authCode = AuthCodeUtil.createNewAuthorizationCode();
        } else {
            this.authCode = authCode;
        }

        if (dateCreated == null) {
            this.dateCreated = new Date();
        } else {
            this.dateCreated = dateCreated;
        }

        if (dateExpires == null) {
            this.dateExpires = AuthCodeUtil.calculateExpirationDate(this.dateCreated);
        } else {
            this.dateExpires = dateExpires;
        }
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuthCode() {
        return authCode;
    }
    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public Date getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateExpires() {
        return dateExpires;
    }
    public void setDateExpires(Date dateExpires) {
        this.dateExpires = dateExpires;
    }

    public boolean isExpired() {
        return this.dateExpires.before(new Date());
    }

    /**
     * Checks that all fields have a value
     * 
     * @return true if all required fields contain a value
     */
    public boolean isValid() {
        if (StringUtils.isBlank(userId)) {
            return false;
        }
        if (StringUtils.isBlank(authCode)) {
            return false;
        }
        if (dateCreated == null) {
            return false;
        }
        if (dateExpires == null) {
            return false;
        }

        return true;
    }

}
