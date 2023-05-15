<#-- @ftlvariable name="entity" type="com.example.models.Entity" -->
<#import "_layout.ftl" as layout />
<@layout.header>
    <div>
        <h3>
            ${entity.id}
        </h3>
        <p>
            ${entity.value}
        </p>
        <p>
            ${entity.name}
        </p>
        <p>
            ${entity.description}
        </p>
        <p>
            ${entity.seasonId}
        </p>
        <p>
            ${entity.order}
        </p>
        <hr>
        <p>
            <a href="/entities/${entity.id}/edit">Edit entity</a>
        </p>
    </div>
</@layout.header>