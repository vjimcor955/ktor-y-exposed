<#import "_layout.ftl" as layout />
<@layout.header>
    <div>
        <h3>Create entity</h3>
        <form action="/entities" method="post">
            <p>
                Id
                <input type="text" name="id">
            </p>
            <p>
                Value
                <input type="text" name="value">
            </p>
            <p>
                Name
                <input type="text" name="name">
            </p>
            <p>
                Description
                <textarea name="description"></textarea>
            </p>
            <p>
                SectionId
                <input type="text" name="sectionId">
            </p>
            <p>
                Order
                <input type="text" name="order">
            </p>
            <p>
                <input type="submit">
            </p>
        </form>
    </div>
</@layout.header>