<#-- @ftlvariable name="entity" type="com.example.models.Entity" -->
<#import "_layout.ftl" as layout />
<@layout.header>
    <div>
        <h3>Edit article</h3>
        <form action="/entities/${entity.id}" method="post">
            <p>
                <input type="text" name="id" value="${entity.id}">
            </p>
            <p>
                <input type="text" name="value" value="${entity.value}">
            </p>
            <p>
                <input type="text" name="name" value="${entity.name}">
            </p>
            <p>
                <textarea name="description">${entity.description}</textarea>
            </p>
            <p>
                <input type="text" name="sectionId" value="${entity.sectionId}">
            </p>
            <p>
                <input type="text" name="order" value="${entity.order}">
            </p>
            <p>
                <input type="submit" name="_action" value="update">
            </p>
        </form>
    </div>
    <div>
        <form action="/entities/${entity.id}" method="post">
            <p>
                <input type="submit" name="_action" value="delete">
            </p>
        </form>
    </div>
</@layout.header>