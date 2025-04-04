<html>
<%@include file="./base.jsp"%>
</head>
<body>


	<div class="container mt-3">

		<h1>Update The Book Details</h1>
		<form action="${pageContext.request.contextPath }/handleBooks"
			method="post">

			<input type="hidden" value="${book.id }" name="id" />

			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="Title">Title</label> <input type="text"
							class="form-control" id="name" name="title"
							placeholder="Enter Book title" value="${book.title }">
					</div>
				</div>
				<div class="col">
					<div class="form-group">
						<label for="author">Author</label> <input type="text"
							class="form-control" id="auth" name="author"
							placeholder="Enter Author Name" value="${book.author }">
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="Genre">Genre</label> <input type="text"
							class="form-control" id="genre" name="genre"
							placeholder="Enter genre" value="${book.genre }">
					</div>
				</div>
				<div class="col">
					<div class="form-group">
						<label for="publicationYear">publicationYear</label> <input
							type="number" class="form-control" id="publicationYear"
							name="publicationYear" placeholder="Enter publicationYear"
							value="${book.publicationYear }">
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="isbn">Isbn</label> <input type="text"
							class="form-control" id="isbn" name="isbn"
							placeholder="Enter Isbn no" value="${book.isbn }">
					</div>
				</div>
			</div>

			<a href="${pageContext.request.contextPath }/"
				class="btn btn-warning"> Back </a>
			<button type="submit" class="btn btn-primary">Update</button>
		</form>

	</div>

</body>
</html>
