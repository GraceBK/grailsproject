<div class="body">
    <div class="username" data-user-id="${user.id}">
        ${ user.username }
        <g:if test="${ user.getAuthorities()[0].authority == 'ROLE_USER' }">
            <span class="role"><g:message code="grailsproject.roles.user"/></span>
        </g:if>
        <g:elseif test="${ user.getAuthorities()[0].authority == 'ROLE_ADMIN' }">
            <span class="role"><g:message code="grailsproject.roles.admin"/></span>
        </g:elseif>
    </div>
    <div class="date">
        <g:message code="grailsproject.users.created_on" args="${[g.formatDate([date:user.dateCreated, type:"datetime", style:"MEDIUM" ])]}"/>
    </div>
</div>