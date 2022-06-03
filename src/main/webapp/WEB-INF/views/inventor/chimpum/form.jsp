<%@ page language="java" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.chimpum.form.label.title" path="title"/>
	<acme:input-moment code="inventor.chimpum.form.label.startDate" path="startDate"/>
	<acme:input-moment code="inventor.chimpum.form.label.finishDate" path="finishDate"/>
	<acme:input-money code="inventor.chimpum.form.label.budget" path="budget"/>
	<acme:input-url code="inventor.chimpum.form.label.link" path="link"/>
	
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:input-textbox code="inventor.chimpum.form.label.pattern" path="pattern" placeholder="ABC-123-A"/>
			<acme:submit code="inventor.chimpum.form.button.create" action="/inventor/chimpum/create"/>
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'update, show, delete')}">
			<acme:input-textbox code="inventor.chimpum.form.label.code" path="code" readonly="true"/>
			<acme:input-moment code="inventor.chimpum.form.label.creationMoment" path="creationMoment" readonly="true"/>
			<acme:submit code="inventor.chimpum.form.button.update" action="/inventor/chimpum/update"/>
			<acme:submit code="inventor.chimpum.form.button.delete" action="/inventor/chimpum/delete"/>
			<acme:button code="inventor.chimpum.form.button.COMPONENT" action="/any/artifact/list-published?chimpumId=${id}"/>
		</jstl:when>
	</jstl:choose>
	
</acme:form>