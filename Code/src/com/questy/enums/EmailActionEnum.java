package com.questy.enums;

/**
 * Every link on an email that requires multiple settings to be updated
 * is recorded as an action. Actions can be at the following levels:
 *
 * - Global user actions: these update the global settings of a user
 *
 * - User to Network actions: these update the network settings of a user
 *
 * - Network actions: these update settings of a network
 */
public enum EmailActionEnum {

    /*********************
     * Global user actions
     *********************/

    /**
     * Ensure that the user does not receive any more first photo upload reminders
     */
    UNSUBSCRIBE_FROM_FIRST_PHOTO_UPLOAD_EMAILS (100),


    /*************************
     * User to network actions
     *************************/

    /**
     * Ensure that the user no longer receives new user link notifications
     * from the particular network
     */
    UNSUBSCRIBE_FROM_NEW_USER_LINK_NOTIFICATIONS (200),

    /**
     * Ensure that the user no longer receives a notification when
     * it has been auto-magically added to a smart group
     */
    UNSUBSCRIBE_FROM_NEW_SMART_GROUP_MAPPINGS (201),

    /**
     * Flags a smart group for the user
     */
    FLAG_SMART_GROUP (202),

    /**
     * Allows the user to change the digest rate it receives
     * from all the smart groups it is a member of
     */
    CHANGE_MEMBER_OR_FAVORITE_SMART_GROUP_DIGEST_RATE (202),



    ;
    private Integer id;

    private EmailActionEnum(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }


    public static EmailActionEnum getById(Integer id) {

        if (id == null)
            return null;

        for (EmailActionEnum ve : values()) {
            if (ve.getId().equals(id))
                return ve;
        }

        return null;
    }

}


