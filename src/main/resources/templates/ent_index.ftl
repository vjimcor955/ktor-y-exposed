<#-- @ftlvariable name="entities" type="kotlin.collections.List<com.example.models.Entity>"-->
<#import "_layout.ftl" as layout />
<@layout.header>
    <#list entities?reverse as entity>
        <div>
            <h3>
                <a href="/entities/${entity.id}">Campo: ${entity.name}</a>
            </h3>
            <p>Value: ${entity.value}</p>
            <p>Season ID: ${entity.seasonId}</p>
            <p>Order: ${entity.order}</p>
            <p>Description: ${entity.value}</p>
            <p>Section ID: ${entity.sectionId}</p>
        </div>
    </#list>
    <hr>
    <p>
        <a href="/articles/new">Create article</a>
        <a href="/entities/new">Create entity</a>
    </p>
</@layout.header>