<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readOnly}"> 
	<acme:input-textbox code="patron.artifact.form.label.name" path="name"/>
	<acme:input-textbox code="patron.artifact.form.label.code" path="code"/>
	<acme:input-textbox code="patron.artifact.form.label.technology" path="technology"/>
	<acme:input-textarea code="patron.artifact.form.label.description" path="description"/>
	<acme:input-money code="patron.artifact.form.label.retailprice" path="retailPrice"/>
	<acme:input-textbox code="patron.artifact.form.label.type" path="artifactType"/>
	<acme:input-textbox code="patron.artifact.form.label.link" path="link"/>

</acme:form>