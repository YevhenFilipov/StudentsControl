<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<nav class="navigation">

    <a href="${CONTEXT}/${CURRENT_MAPPING}/home.html">На
        главную
    </a>
    <a href="${CONTEXT}/${CURRENT_MAPPING}${BACK_PAGE}">Назад
    </a>

</nav>

<div class="student_list_container">
    <div class="fullWidthBlock">
        <div class="leftColumnBlock">
            <h2>
                Список студентов
            </h2>
        </div>
    </div>
    <div class="fullWidthBlock">
        <div class="leftColumnBlock">

            <table class=>

                <tr>
                    <th></th>
                    <th>Фамилия</th>
                    <th>Имя</th>
                    <th>Группа</th>
                    <th>Дата поступления</th>
                </tr>

                <c:forEach var="student" items="${students }">
                    <tr>
                        <td><input class="checkbox" type="checkbox" id="${student.id }"></td>
                        <td> ${student.lastName }</td>
                        <td> ${student.name }</td>
                        <td> ${student.group }</td>
                        <td> ${ student.date }</td>
                    </tr>
                </c:forEach>

            </table>
        </div>


        <div class="rightColumnBlock">
            <input class="button" type="button" id="button" name="showMarks"
                   value="Просмотреть успеваемость выбранного студента" onclick="progressStudents()">

            <c:if test="${CURRENT_ROLE eq 1}">
                <input class="button" type="submit"
                       value="Создать студента..." id="studentCreateButton"
                       name="creating" onclick="location.href='${CONTEXT}/${CURRENT_MAPPING}/studentCreating.html'">

                <input class="button" type="submit"
                       value="Модифицировать выбранного студетна..." id="studentModifyButton"
                       name="modifying" onclick="modifyingStudent()">

                <input class="button"
                       type="button" onclick="deleteStudents()" name="studentDeleteButton"
                       value="Удалить выбранных студентов" id="button">

            </c:if>
        </div>
    </div>


</div>