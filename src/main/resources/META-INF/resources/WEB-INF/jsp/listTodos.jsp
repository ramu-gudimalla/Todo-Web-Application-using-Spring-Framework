<%@ include file="common/header.jspf" %>
    <%@ include file="common/navigation.jspf" %>
    <div class="container">
        <h1>Your todos </h1>
        <table class="table">
            <thead>
                <tr>
                    <th>Description</th>
                    <th>Target Date</th>
                    <th>Is Done?</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${todos}" var="todos">
                    <tr>
                        <td>${todos.description}</td>
                        <td>${todos.targetDate}</td>
                        <td>${todos.done}</td>
                        <td><a href="update-todo?id=${todos.id}" class="btn btn-success rounded-pill"/>Update</td>
                        <td><a href="delete-todo?id=${todos.id}" class="btn btn-danger rounded-pill"/>Delete</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="add-todo" class="btn btn-primary rounded-pill">Add Todo</a>
    </div>
<%@ include file="common/footer.jspf" %>