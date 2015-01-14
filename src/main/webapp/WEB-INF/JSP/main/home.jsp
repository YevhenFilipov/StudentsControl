<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="content">

    <div class="students">
        <a class="mainLink" href="${CONTEXT }${CURRENT_MAPPING}/studentsList.php">Студенты</a>
    </div>
    <div class="discipline">
        <a class="mainLink" href="${CONTEXT }${CURRENT_MAPPING}/disciplinesList.php">Дисциплины</a>
    </div>
    <div class="term">
        <a class="mainLink" href="${CONTEXT}${CURRENT_MAPPING}/termsList.php">Семестры</a>
    </div>
</div>