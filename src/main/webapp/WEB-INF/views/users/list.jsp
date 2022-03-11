<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<%@ include file="/WEB-INF/views/common/header.jsp"%>
		<!-- Left side column. contains the logo and sidebar -->
		<%@ include file="/WEB-INF/views/common/sidebar.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					Utilisateurs <a class="btn btn-primary"
						href="${pageContext.request.contextPath}/users/create">Ajouter</a>
				</h1>
				<br>
				 <div class="form-group">
				 	<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/users?id=0">
                		<label for="first_name" >Rechercher par Nom</label>
                    	<input type="text" id="nom" name="nom" placeholder="Nom">
                    	<button type="submit" href="#" class="btn btn-info">Rechercher</button>
                    	<!--  <a class="btn btn-primary" href="${pageContext.request.contextPath}/users/create">Rechercher</a> -->
                    </form>
                </div>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-12">
						<div class="box">
							<div class="box-body no-padding">
								<table class="table table-striped">
									<tr>
										<th style="width: 10px">#</th>
										<th>Nom</th>
										<th>Prenom</th>
										<th>Email</th>
										<th>Date de naissance</th>
										<th>Action</th>
									</tr>
									<c:forEach items="${ listUsers }" var="user">
										<tr>
											<td>${user.id}</td>
											<td>${user.nom}</td>
											<td>${user.prenom}</td>
											<td>${user.email}</td>
											<td>${user.naissance}</td>
											<td>
												<form class="form-horizontal" method="post"
													action="${pageContext.request.contextPath}/users?id=${user.id}">
													<a class="btn btn-primary"
														href="${pageContext.request.contextPath}/users/details?id=${user.id}">
														<i class="fa fa-play"></i>
													</a> <a class="btn btn-success"
														href="${pageContext.request.contextPath}/users/modifier?id=${user.id}">
														<i class="fa fa-edit"></i>
													</a>
													<button type="submit" class="btn btn-danger" href="#">
														<i class="fa fa-trash"></i>
													</button>
												</form>
											</td>
										</tr>
									</c:forEach>
									<!-- <tr>
                                    <td>1.</td>
                                    <td>John</td>
                                    <td>Doe</td>
                                    <td>john.doe@epf.fr</td>
                                    <td>
                                        <a class="btn btn-primary" href="${pageContext.request.contextPath}/users/details?id=1">
                                        <i class="fa fa-play"></i>
                                        </a>
                                        <a class="btn btn-success disabled" href="#">
                                            <i class="fa fa-edit"></i>
                                        </a>
                                        <a class="btn btn-danger disabled" href="#">
                                            <i class="fa fa-trash"></i>
                                        </a>
                                    </td>
                                </tr>

                                <tr>
                                    <td>2.</td>
                                    <td>Jane</td>
                                    <td>Doe</td>
                                    <td>jane.doe@epf.fr</td>
                                    <td>
                                        <a class="btn btn-primary" href="${pageContext.request.contextPath}/users/details?id=2">
                                            <i class="fa fa-play"></i>
                                        </a>
                                        <a class="btn btn-success disabled" href="#">
                                            <i class="fa fa-edit"></i>
                                        </a>
                                        <a class="btn btn-danger disabled" href="#">
                                            <i class="fa fa-trash"></i>
                                        </a>
                                    </td>
                                </tr> -->
								</table>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
					<!-- /.col -->
				</div>
			</section>
			<!-- /.content -->
		</div>

		<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	</div>
	<!-- ./wrapper -->

	<%@ include file="/WEB-INF/views/common/js_imports.jsp"%>
</body>
</html>
