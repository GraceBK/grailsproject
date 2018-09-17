<g:uploadForm name="uploadFeaturedImage" action="uploadFeaturedImage">
    <g:hiddenField name="id" value="${this.user?.id}" />
    <g:hiddenField name="version" value="${this.user?.version}" />
    <input type="file" name="featuredImageFile"/>
    <fieldset class="buttons">
        <input class="save" type="submit" value="${message(code: 'user.featuredImage.upload.label', default: 'Upload')}" />
    </fieldset>
</g:uploadForm>