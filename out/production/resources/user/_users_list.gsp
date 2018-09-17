<div class="row users_list">
    <div class="col-xs-12 col-sm-12">
        <g:each in="${ users }" var="user">
            <div class="user">
                <div class="thumb img-circle">
                    <g:jmagineImage src="${user.miniatures?.filename}"/>
                </div>
                <g:include view="user/_user_body.gsp" model="[user:user]"/>
                <div class="actions">
                    <g:link controller="user" action="list" params="${[user_id:user.id]}" class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> <g:message code="grailsproject.users.button.edit"/></g:link>
                </div>
            </div>
        </g:each>
    </div>
</div>