<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"  
	session="false"  %>
	
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="pt">
<head>
<!-- 
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
-->

<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>Monitor de Tecnologias Integradas</title>
<meta name="generator" content="Bootply" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
 
<link  rel="stylesheet" href="<c:url value="/resources/bootstrap/3.3.7/css/bootstrap.min.css"/>">

<script src="<c:url value="/resources/jquery/2.2.4/jquery-2.2.4.min.js"/>"></script>
<script src="<c:url value="/resources/bootstrap/3.3.7/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/jquery/jquery.quicksearch.min.js"/>"></script>
<script src="<c:url value="/resources/js/monitortecnologia.js"/>"></script>



<!--[if IE]>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<![endif]-->

<!--[if lt IE 9]>
			<script src=<c:url value="/resources/bootstrap/html5shiv.js"/></script>		
			
<![endif]-->

<link href="<c:url value="/resources/styles/styles.css"/>" rel="stylesheet">

<link href="<c:url value="/resources/images/logo_infolog_riscos.gif"/>" rel="icon"  type="image/x-icon"/>

</head>

<body onload="doLoad(); atualizarIndicador(); alertaSonoro(); atualizarIntegracaoTelerisco();">

	<!-- CARREGA ALERTA SONORO -->
	<audio id="alertaSonoro" src="<c:url value="/resources/media/alerta_sonoro.mp3"/>"></audio>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a href="#" class="navbar-brand">Monitor de Tecnologias Integradas</a>
			</div>
			<!-- 
			<!-- Link para Sair do sistema 
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#" onclick="closeCurrentWindow();"><span class="glyphicon glyphicon-log-in"></span>Sair</a></li>
			</ul>
			-->
			<!-- Pesquisar Tecnologias -->
			<form class="navbar-form navbar-right">
				<div class="input-group">
					<input id="findTecnologia" type="text" class="form-control" placeholder="Pesquisar..." value="${tecnologia}">
					
				</div>
			</form>
			
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row row-offcanvas row-offcanvas-left">
			<div class="col-sm-12 col-md-12 main">


				<div class="row placeholders" id="indicadores" style="display: none;">
					<div class="col-xs-6 col-sm-3 placeholder text-center">
						<img
							src="//placehold.it/200/98FB98/fff&text=${monitor.totalGeral > 0 ? monitor.totalGeral : 'Sem resultados'}"
							class="center-block img-responsive img-circle " alt="total">
						<h4><b>Eventos em processamento</b></h4>


					</div>
					<div class="col-xs-6 col-sm-3 placeholder text-center">
						<img
							src="//placehold.it/200/B0E0E6/fff&text=${(monitor.totalDedicado + monitor.totalPvAtivo) > 0 ? (monitor.totalDedicado + monitor.totalPvAtivo) : 'Sem resultados'}"
							class="center-block img-responsive img-circle" alt="processando">
						<h4><b>Ve&#xed;culos ativos</b></h4>

					</div>
					<div class="col-xs-6 col-sm-3 placeholder text-center">
						<img
							src="//placehold.it/200/C0C0C0/fff&text=${monitor.totalDedicado > 0 ? monitor.totalDedicado : 'Sem resultados'}"
							class="center-block img-responsive img-circle" alt="aProcessar">
						<h4><b>Ve&#xed;culos dedicados</b></h4>

					</div>
					<div class="col-xs-6 col-sm-3 placeholder text-center">
						<img
							src="//placehold.it/200/FFDEAD/fff&text=${monitor.totalPvAtivo > 0 ? monitor.totalPvAtivo : 'Sem resultados'}"
							class="center-block img-responsive img-circle" alt="processado">
						<h4><b>Ve&#xed;culos com pv. ativo</b></h4>

					</div>
				</div>

				<div class="row" style="font-size:10px">
					<div class="col-sm-3" style="min-height: 25px;">
						<!-- <input type="checkbox" id="chkDesativarAlertaSonoro">Desativar alerta sonoro. -->

						<label class="switch" >
  							<input type="checkbox" id="chkDesativarAlertaSonoro">
  							<span class="slider round"></span>
  							<p id="tituloAlertaSonoro" style="margin-left: 40px; width: 200px;">Desativar Alerta Sonoro</p>
  							
						</label>
					</div>
			
			
			
				</div>
				
				
				
				
				

				<div class="row"  style="font-size:10px">
					<div class="col-sm-3">
						<!-- <input type="checkbox" name="exibirIndicadores" id="chkExibirIndicadores">Exibir Indicadores. -->
						
						<label class="switch" >
  							<input type="checkbox" name="exibirIndicadores" id="chkExibirIndicadores" class="switch">
  							<span class="slider round"></span>
  							<p id="tituloIndicadores" style="margin-left: 40px; width: 200px;">Exibir Indicadores</p>
  							
						</label>
					</div>


					<div class="col-sm-9">
						<!-- Link Modal -->	
						<p style="text-align: right;">
						<a data-toggle="modal" href="#modalLegenda">Legenda</a>
						<!-- Modal Legenda-->
						<div class="modal fade" id="modalLegenda" role="dialog">
								<div class="modal-dialog modal-sm">
			
									<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Legenda</h4>
										</div>
										<div class="modal-body">
			
											<div>
												<canvas id="legendaAmarela" width="10" height="10"
													style="border: 1px solid #000000; background: #ffff00">Seu browser n&#xE3;o suporta o componente CANVAS do HTML5.
										</canvas>
												Coluna <b>Delay</b> maior que <b>05</b> minutos.
											</div>
											<div>
												<canvas id="legendaLaranja" width="10" height="10"
													style="border: 1px solid #000000; background: #ffbb33">Seu browser n&#xE3;o suporta o componente CANVAS do HTML5.
										</canvas>
												Coluna <b>Delay</b> maior que <b>10</b> minutos.
			
											</div>
											<div>
												<canvas id="legendaVermelha" width="10" height="10"
													style="border: 1px solid #000000; background: #ff4444">
											Seu browser n&#xE3;o suporta o componente CANVAS do HTML5.
										</canvas>
												Coluna <b>Delay</b> maior que <b>20</b> minutos.
			
											</div>
											<div>
												<canvas id="legendaVermelhaEscura" width="10" height="10"
													style="border: 1px solid #000000; background: #cc0000">
											Seu browser n&#xE3;o suporta o componente CANVAS do HTML5.
										</canvas>
												Coluna <b>Delay</b> maior que <b>30</b> minutos.
											</div>
											<div>
												<canvas id="Preta" width="10" height="10"
													style="border: 1px solid #000000; background: #2e2e2e">
											Seu browser n&#xE3;o suporta o componente CANVAS do HTML5.
										</canvas>
												Coluna <b>Delay</b> maior que <b>60</b> minutos.
			
											</div>
											<div>
												<canvas id="Roxa" width="10" height="10"
													style="border: 1px solid #000000; background: #610b4b">
											Seu browser n&#xE3;o suporta o componente CANVAS do HTML5.
										</canvas>
												Coluna <b>Delay</b> maior que <b>24</b> horas.
			
											</div>

											
											
											
			
			
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-xs btn-primary"
												data-dismiss="modal">Fechar</button>
										</div>
									</div>
			
								</div>
							</div>	
						
					
					</div>
				</div>
				<div class="row" style="font-size:10px">
					<div class="col-sm-3">
						<!--  <input type="checkbox" id="chkDesativarAtualizacaoAutomatica">Desativar atualização automática. -->

						<label class="switch" >
  							<input type="checkbox" id="chkDesativarAtualizacaoAutomatica">
  							<span class="slider round"></span>
  							<p id="tituloAtualizacaoAutomatica" style="margin-left: 40px; width: 200px;">Desativar Atualiza&#xE7;&#xE3;o autom&#xe1;tica</p>					
  							
						</label>
					</div>
					<div class="col-sm-9">
						<p style="text-align: right;">
							&#xda;ltima atualiza&#xE7;&#xE3;o:
							<script>
							<!--
								document.write('<b>'
										+ (new Date()).toLocaleString()
										+ '</b>');
							//-->
							</script>
					</div>
				</div>
				
				
		<!-- ------ implementação telerisco -->
				<div class="row"  style="font-size:10px">
					<div class="col-sm-12">
							<label class="switch" >
	  							<input type="checkbox" name="exibirIntegracaoTelerisco" id="chkExibirIntegracaoTelerisco" class="switch">
	  							<span class="slider round"></span>
	  							<p id="tituloIntegracaoTelerisco" style="margin-left: 40px; width: 200px;">Exibir Integra&#xE7;&#xE3;o Telerisco</p>
							</label>
					</div>

				</div>
				
				<div id="exibeIntegracaoTelerisco">
					<object type="text/html" data="../monitor/telerisco"></object>
				</div>
<!-- -termino implementação telerisco -->								
				
				<br>
				
				<c:choose>
					<c:when test="${fn:length(monitor.tecnologias) > 0}">
						<div class="table-responsive" >
							<table id="tableHeadTeconologias" class="table table-hover">
								<!-- class="table table-striped"-->
								<thead>
									<tr style="background:	#4A75B5; color:#ffffff; font-size:13px;">
									 
										<th colspan="1"></th>
																	
										<th style="text-align: left;">Tecnologia</th>
										<th style="text-align: center;">Delay</th>
										<th style="text-align: center;">Proc. Evento</th>
										<th style="text-align: center;">Proc. Operadora</th>
										<th style="text-align: center;">Proc. Pamcary</th>
										<th style="text-align: center;">Processados</th>
										<th style="text-align: center;">A Processar</th>
										<th style="text-align: center;">Processando</th>
										<th style="text-align: center;">Total</th>
										<th style="text-align: center;">Dedicados</th>
										<th style="text-align: center;">PV. Ativos</th>
									</tr>
								</thead>
		
								<tbody id="tableBodyTecnologias">
									<c:forEach items="${monitor.tecnologias}" var="tecnologia">

										<c:set var="tempoExpiracao" value="${tecnologia.diff}" />

										<c:set var="colorLinha" value="" />
										<c:set var="alerta" value="false" />

										<c:if test="${(tempoExpiracao >=6 and tempoExpiracao <=10)}">
											<c:set var="colorLinha" value="background:#ffff00" />
										</c:if>

										<c:if test="${(tempoExpiracao >10 and tempoExpiracao <=20)}">
											<c:set var="colorLinha" value="background:#ffbb33" />
										</c:if>

										<c:if test="${(tempoExpiracao >20 and tempoExpiracao <=30)}">
											<c:set var="colorLinha"
												value="background:#ff4444; color:#ffffff" />
											
											<c:set var="alerta" value="true"/>
																							
										</c:if>

										<c:if test="${(tempoExpiracao >30 and tempoExpiracao <60)}">
											<c:set var="colorLinha"
												value="background:#cc0000; color:#ffffff" />
												
											<c:set var="alerta" value="true"/>	
																						
										</c:if>

										<c:if test="${tempoExpiracao >=60 and tempoExpiracao <1440}">
											<c:set var="colorLinha"
												value="background:#2e2e2e; color:#ffffff" />
											
											<c:set var="alerta" value="true"/>
											
										</c:if>

										<c:if test="${tempoExpiracao >=1440}">
											<c:set var="colorLinha"
												value="background:#610b4b; color:#ffffff" />
											
											<c:set var="alerta" value="true"/>
											
										</c:if>


										<fmt:formatDate value="${tecnologia.dhrEvento}" pattern="dd/MM/yyyy HH:mm:ss" var="dhrEventoFormat"/>													
										<fmt:formatDate value="${tecnologia.dhrProcForcedor}" pattern="dd/MM/yyyy HH:mm:ss" var="dhrProcForcedorFormat"/>
										<fmt:formatDate value="${tecnologia.dhrProcPamcary}" pattern="dd/MM/yyyy HH:mm:ss" var="dhrProcPamcaryFormat"/>

										<tr style="${colorLinha};font-size:11px">
											<td>
												<c:if test="${alerta}">
													<img src="<c:url value='/resources/images/sound-on-icon.png'/>" alt="Aviso Sonoro" style="width:20px;height:20px;"/>
												</c:if>										
												
											</td>
										
											<td style="text-align: left;"><c:out
													value="${tecnologia.provedor}" /></td>

											<td style="text-align: right;"><b><c:out
													value="${tecnologia.diffFormatado}" /></b></td>

											<td style="text-align: center;"><c:out
													value="${dhrEventoFormat}" /></td>

											<td style="text-align: center;"><c:out
													value="${dhrProcForcedorFormat}" /></td>

											<td style="text-align: center;"><c:out
													value="${dhrProcPamcaryFormat}" /></td>

											<td style="text-align: right;"><c:out
													value="${tecnologia.qtdeLimpezaProcessar}" /></td>

											<td style="text-align: right;"><c:out
													value="${tecnologia.qtdeAProcessar}" /></td>

											<td style="text-align: right;"><c:out
													value="${tecnologia.qtdeProcessando}" /></td>

											<td style="text-align: right;"><c:out
													value="${tecnologia.total}" /></td>

											<td style="text-align: right;"><c:out
													value="${tecnologia.qtdeDedicado}" /></td>

											<td style="text-align: right;"><c:out
													value="${tecnologia.qtdeVeiculo}" /></td>
										</tr>

									</c:forEach>
								</tbody>
							</table>
						</div>
					</c:when>
					<c:otherwise>
						<div class="alert alert-warning" align="center">
  							<h3>Aten&#xE7;&#xE3;o</h3>
  							<h3>N&#xE3;o existem registros para filtro selecionado!</h3>
  							
						</div>
					</c:otherwise>
				</c:choose>		
			</div>
		</div>
		<!--/row-->
	</div>
	<!--/.container-->

	<footer  style="font-size:10px">
		<p class="pull-right">GPS-Pamcary Log&#xed;stica e Gerenciamento de Riscos</p>
	</footer>

</body>
</html>