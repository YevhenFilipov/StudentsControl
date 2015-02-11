<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<nav class="navigation">
    <a href="${CONTEXT}${CURRENT_MAPPING}/home.html">На
        главную
    </a>
    <a href="${CONTEXT}${CURRENT_MAPPING}${BACK_PAGE}">Назад
    </a>
</nav>

<div class="content">
    <div class="fullWidthBlock">
        <div class="leftColumnBlock">
            <h3>Отображена успеваемость для следующего студента:</h3>
            <table>
                <tr>

                <tr>
                    <th>Фамилия</th>
                    <th>Имя</th>
                    <th>Группа</th>
                    <th>Дата поступления</th>
                </tr>
                <tr>
                    <td>${student.lastName }</td>
                    <td>${student.name }</td>
                    <td>${student.group }</td>
                    <td>${student.date }</td>
                </tr>
            </table>

            <c:if test="${studentProgressButton eq 2}">
                <span>
                     Оценки за семестр ${idFirstTerm}
                 </span>
            </c:if>
        </div>
        <div class="rightColumnBlock">
            <form action="${CONTEXT}${CURRENT_MAPPING}/studentProgress.html"
                  method="GET">
                <input type="hidden" name="id" id="id_student" value="${student.id }">

                <div class="highLightBlock">
                    <c:if test="${studentProgressButton eq 1}">
                        Выбрать
                        семестр
                        <select id="opening_list" name="selectTerm">
                            <c:forEach
                                    var="term" items="${allTerms}">
                                <c:choose>
                                    <c:when test="${term.id  eq idCurrTerm }">
                                        <option selected="selected" value="${term.id }">Семестр
                                                ${term.id }</option>
                                    </c:when>

                                    <c:otherwise>
                                        <option value="${term.id }">Семестр ${term.id }</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <input type="submit" value="Выбрать">
                        <br>
                        <c:if test="${meanMark ne 0.0}">
                            Средняя оценка за семестр:
                            ${meanMark }
                            балла
                        </c:if>
                    </c:if>

                    <div class="rightAlignInTable">

                        <c:if test="${CURRENT_ROLE eq 1 and studentProgressButton eq 1}">
                            <input type="button" id="button" name="showMarks"
                                   value="Изменить оценки" onclick="goToChangeMarks()">
                        </c:if>
                        <c:if test="${studentProgressButton eq 2}">
                            <input type="button" value="Записать оценки" onclick="verifyStudentProgressForm()">
                        </c:if>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="fullWidthBlock">
        <div class="leftColumnBlock">
            <table>
                <tr>
                    <th>Дисциплина</th>
                    <th>Оценка</th>
                </tr>
                <c:forEach var="marksByTerm" items="${marksByTerm}">
                    <tr>
                        <td>
                            <c:out value="${marksByTerm.markName }"></c:out>
                            <input type="hidden" name="disciplineId" value="${marksByTerm.idMarkName }">
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${studentProgressButton eq 1}">
                                    <c:choose>
                                        <c:when test="${marksByTerm.markValue eq 0}">
                                            Нет оценки
                                        </c:when>
                                        <c:otherwise>
                                            ${marksByTerm.markValue }
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    <input type="number" name="mark" value="${marksByTerm.markValue }"
                                           maxlength="1">
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>