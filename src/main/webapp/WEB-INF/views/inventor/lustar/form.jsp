<%@ page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.lustar.form.label.subject"
		path="subject" />
	<acme:input-textarea code="inventor.lustar.form.label.summary"
		path="summary" />
	<acme:input-moment code="inventor.lustar.form.label.startDate"
		path="startDate" />
	<acme:input-moment code="inventor.lustar.form.label.finishDate"
		path="finishDate" />
	<acme:input-money code="inventor.lustar.form.label.income"
		path="income" />
	<acme:input-url code="inventor.lustar.form.label.more-info" path="moreInfo" />

	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:input-textbox code="inventor.lustar.form.label.pattern"
				path="pattern" placeholder="00" />
			<acme:submit code="inventor.lustar.form.button.create"
				action="/inventor/lustar/create" />
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'update, show, delete')}">
			<acme:input-textbox code="inventor.lustar.form.label.code"
				path="code" readonly="true" />
			<acme:input-moment code="inventor.lustar.form.label.creationMoment"
				path="creationMoment" readonly="true" />
			<acme:submit code="inventor.lustar.form.button.update"
				action="/inventor/lustar/update" />
			<acme:submit code="inventor.lustar.form.button.delete"
				action="/inventor/lustar/delete" />
			<acme:button code="inventor.lustar.form.button.component"
				action="/any/artifact/list-published?lustarId=${id}" />
		</jstl:when>
	</jstl:choose>

</acme:form>