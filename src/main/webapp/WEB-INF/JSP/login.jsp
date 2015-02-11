<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="content">
    <form action="${CONTEXT }/login.html" method="post">
        <h3>Вход</h3>

        <div class="highLightBlock">
            <table id="login_table">
                <tr class="loginTable">
                    <td class="rightAlignInTable">Имя:</td>
                    <td class="loginTable"><input type="text" name="username" class="text" value=""/></td>
                </tr>
                <tr>
                    <td class="rightAlignInTable">Пароль:</td>
                    <td class="loginTable"><input type="password" name="password" class="text"/></td>
                </tr>
                <tr>
                    <td class="rightAlignInTable">Роль:</td>
                    <td class="loginTable"><select name="role" id="opening_list">
                        <c:forEach var="role" items="${roles }">
                            <option value="${role.id }">${role.name }</option>
                        </c:forEach>
                    </select></td>
                </tr>
                <tr>
                    <td class="loginTable"><%--&nbsp--%></td>
                    <td class="rightAlignInTable"><input type="submit" value="войти" id="button"></td>
                </tr>
            </table>
        </div>
        <jsp:include page="modules/validationMessage.jsp"/>
    </form>
    <div>
        <p>Тестовые аккаунты: "admin / password" , "student / password"</p>

        <p>Аккаунт "admin" поддерживает обе роли</p>
    </div>
</div>