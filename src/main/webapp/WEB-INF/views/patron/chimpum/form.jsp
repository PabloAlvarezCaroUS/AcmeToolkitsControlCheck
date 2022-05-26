<%@ page language="java" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="patron.chimpum.form.label.title" path="title"/>
	<acme:input-moment code="patron.chimpum.form.label.startDate" path="startDate"/>
	<acme:input-moment code="patron.chimpum.form.label.finishDate" path="finishDate"/>
	<acme:input-money code="patron.chimpum.form.label.budget" path="budget"/>
	<acme:input-url code="patron.chimpum.form.label.link" path="link"/>
	
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:input-textbox code="patron.chimpum.form.label.pattern" path="pattern" placeholder="ABC-123-A"/>
			<acme:submit code="patron.chimpum.form.button.create" action="/patron/chimpum/create"/>
		</jstl:when>
		<jstl:when test="${command == 'show'}">
			<acme:input-textbox code="patron.chimpum.form.label.code" path="code"/>
			<acme:input-moment code="patron.chimpum.form.label.creationMoment" path="creationMoment"/>
			<acme:button code="patron.chimpum.form.button.COMPONENT" action="/patron/artifact/list?chimpumId=${id}"/>
		</jstl:when>
	</jstl:choose>
	
</acme:form>