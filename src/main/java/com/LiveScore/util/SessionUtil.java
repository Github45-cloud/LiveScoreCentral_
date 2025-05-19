package com.LiveScore.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * Utility class for managing HTTP sessions in a web application.
 * Provides methods to set, get, remove session attributes and invalidate sessions.
 */
public class SessionUtil {

    /**
     * Gets the current session, optionally creating one if it doesn't exist.
     *
     * @param request the HttpServletRequest from which the session is obtained
     * @param create  true to create a session if one doesn't exist, false to return null if absent
     * @return the HttpSession object or null if not found and create is false
     */
    private static HttpSession getSession(HttpServletRequest request, boolean create) {
        return request.getSession(create);
    }

    /**
     * Sets an attribute in the session. Creates a session if one doesn't exist.
     *
     * @param request the HttpServletRequest
     * @param key     the attribute key
     * @param value   the attribute value
     */
    public static void setAttribute(HttpServletRequest request, String key, Object value) {
        HttpSession session = getSession(request, true); // ensures session is created
        session.setAttribute(key, value);
    }

    /**
     * Retrieves an attribute from the session.
     *
     * @param request the HttpServletRequest
     * @param key     the attribute key
     * @return the attribute value, or null if session or key is missing
     */
    public static Object getAttribute(HttpServletRequest request, String key) {
        HttpSession session = getSession(request, false); // don't create session
        return (session != null) ? session.getAttribute(key) : null;
    }

    /**
     * Removes an attribute from the session.
     *
     * @param request the HttpServletRequest
     * @param key     the attribute key to remove
     */
    public static void removeAttribute(HttpServletRequest request, String key) {
        HttpSession session = getSession(request, false);
        if (session != null) {
            session.removeAttribute(key);
        }
    }

    /**
     * Invalidates the current session if it exists.
     *
     * @param request the HttpServletRequest
     */
    public static void invalidateSession(HttpServletRequest request) {
        HttpSession session = getSession(request, false);
        if (session != null) {
            session.invalidate();
        }
    }
}
