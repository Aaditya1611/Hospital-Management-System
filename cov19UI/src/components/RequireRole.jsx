import React from "react";

export const RequireRole = ({ allowedRoles, userRoles, children }) => {
    // Safety check: ensure userRoles is an array before trying to use .some()
    const currentRoles = Array.isArray(userRoles) ? userRoles : [];

    // Check if the user has at least one of the allowed roles
    const hasAccess = currentRoles.some(role => allowedRoles.includes(role));

    // If they don't have the role, render absolutely nothing
    if (!hasAccess) {
        return null; 
    }

    // If they do have the role, render whatever is inside the wrapper
    return <>{children}</>;
};