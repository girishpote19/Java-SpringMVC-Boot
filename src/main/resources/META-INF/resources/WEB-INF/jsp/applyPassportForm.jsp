<!-- applyPassportForm.html -->
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Apply Passport</title>
</head>
<body>
	<h2>Passport Application Form</h2>
	<form action="/applyPassport" method="post" th:object="${passportForm}">
		<!-- Input fields for passport application details -->
		<label>User ID:</label> <input type="text" th:field="*{userId}"
			required><br>
		<!-- Other fields go here -->

		<button type="submit">Apply</button>
		<button type="reset">Reset</button>
	</form>
</body>
</html>

<!-- successPage.html -->
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Success</title>
</head>
<body>
	<h2>Application Submitted Successfully</h2>
	<p th:text="${message}"></p>
</body>
</html>
