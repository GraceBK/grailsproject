<g:form resource="${this.user}" method="POST" enctype="multipart/form-data">

    <div class="fieldcontain">
        <label>Profile image</label>
        <input type="file" name="featuredImageFile"/>
        -</div>

    <fieldset class="form">
        <f:all bean="user"/>
    </fieldset>

    <fieldset class="buttons">
        <g:submitButton name="create" class="save"
                        value="${message(code: 'default.button.create.label', default: 'Create')}"/>
    </fieldset>
</g:form>