<#-- @ftlvariable name="articles" type="kotlin.collections.List<com.example.models.Article>" -->
<#-- @ftlvariable name="entities" type="kotlin.collections.List<com.example.models.Entity>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Kotlin Journal</title>
</head>
<#import "_layout.ftl" as layout />
<@layout.header>
    <#list articles?reverse as article>
        <div>
            <h3>
                <a href="/articles/${article.id}">${article.title}</a>
            </h3>
            <p>
                ${article.body}
            </p>
        </div>
    </#list>
    <hr>
    <p>
        <a href="/articles/new">Create article</a>
        <br>
        <a href="/entities/new">Create entity</a>
    </p>
</@layout.header>
</html>