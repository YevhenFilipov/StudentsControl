<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<nav class="navigation">
    <a href="${CONTEXT}${CURRENT_MAPPING}/home.html">На
        главную
    </a>
    <a href="${CONTEXT}${CURRENT_MAPPING}${BACK_PAGE}">Назад
    </a>
</nav>


<h2>
    <c:choose>
        <c:when test="${TERM_BUTTON eq 1}">
            Для создания семестра заполните следующие данные и нажмите кнопку "Создать":
        </c:when>
        <c:otherwise>
            Для модификации семестра отредактируйте данные и нажмите кнопку "Применить":
        </c:otherwise>
    </c:choose>

</h2>

<c:choose>
    <c:when test="${TERM_BUTTON eq 1}">
        <form id="termForm" class="newDiscipline" action="${CONTEXT }${CURRENT_MAPPING}/termCreating.html"
        method="POST">
    </c:when>
    <c:otherwise>
        <form id="termForm" class="newDiscipline" action="${CONTEXT }${CURRENT_MAPPING}/termModifying.html"
        method="POST">
    </c:otherwise>
</c:choose>

<input type="hidden" name="id" value="${term.id }"/>
<input type="hidden" id="id_disciplines" name="id_disciplines"/>

<div class="highLightBlock">
    <table>
        <tr>
            <td class="rightAlignInTable">
                Длительнсть в неделях
            </td>
            <td class="loginTable">
                <input id="term_duration" type="number" size="20" name="term_duration" value="${term.duration}"
                       maxlength="3">
            </td>
        </tr>
        <tr>
            <td class="rightAlignInTable">
                Дисциплины в семестре
            </td>
            <td class="loginTable">
                <select multiple size="8"
                        id="disciplines_list" multiple="multiple" name="disciplines_list">
                    <c:forEach var="discipline" items="${disciplines }">
                        <option value="${discipline.id }"
                                <c:forEach var="selectedDiscipline" items="${selectedDisciplines }">
                                    <c:choose>
                                        <c:when test="${discipline.id  eq selectedDiscipline.id }">
                                            selected="selected"
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                                >${discipline.name}</option>
                    </c:forEach>
                </select>
            </td>

        </tr>
    </table>
    <div class="rightAlignInTable">
        <c:choose>
        <c:when test="${TERM_BUTTON eq 1}">
        <input type="button" value="Создать" id="button" onclick="verifyTermForm()">
        </c:when>
        <c:otherwise>
        <input type="button" value="Применить" id="button" onclick="verifyTermForm()">
        </c:otherwise>
        </c:choose>
</form>
</div>
</div>

<jsp:include page="/WEB-INF/JSP/modules/validationMessage.jsp"/>

