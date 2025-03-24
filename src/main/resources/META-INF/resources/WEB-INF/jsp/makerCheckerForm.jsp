<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="makerCheckerModal" class="modal" style="display: none;">
    <div class="modal-content">
        <span class="close">&times;</span>
        <form id="makerCheckerForm" method="post" action="makerChecker.htm">
            <label for="productName">Select Product:</label>
            <select id="productName" name="productName">
                <c:forEach var="product" items="${productNames}">
                    <option value="${product}">${product}</option>
                </c:forEach>
            </select>
            <br/><br/>
            <label for="selectedLanguage">Select Language:</label>
            <select id="selectedLanguage" name="selectedLanguage">
                <c:forEach var="language" items="${languages}">
                    <option value="${language}">${language}</option>
                </c:forEach>
            </select>
            <br/><br/>
            <label for="kfsTextContent">KFS Text Content:</label>
            <textarea id="kfsTextContent" name="kfsTextContent" rows="10" cols="50"></textarea>
            <br/><br/>
            <input type="submit" value="Save Template" />
        </form>
    </div>
</div>
