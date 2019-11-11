<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<div class="p-center-discuss">

	<c:if test="${!empty commentList }">
		<section class="">
			<section class="question-list lh-bj-list pr">
				<ul class="pr10 talkhtml">
					<c:forEach items="${commentList }" var="ac">
						<li>
							<aside class="noter-pic">
								<c:if test="${empty ac.picImg }">
									<img width="50" height="50" class="picImg" src="${ctx }/static/inxweb/img/avatar-boy.gif">
								</c:if>
								<c:if test="${!empty ac.picImg }">
									<img width="50" height="50" class="picImg" src="${staticServer}${ac.picImg }">
								</c:if>
							</aside>
							<div class="of">
								<span class="fl">
								<c:if test="${user.userId!=ac.userId}">
								<font class="fsize12 c-blue">
									<c:if test="${!empty ac.displayName }">
										${ac.displayName}
									</c:if>
								</font>
								<font class="fsize12 c-999">：</font>
								</c:if>
									<c:if test="${user.userId==ac.userId}">
								<font class="fsize12 c-blue">
									我
								</font>
								<font class="fsize12 c-999">：</font>
								</c:if>
								</span>
							</div>
							<div class="noter-txt mt5">
								<p><c:out value="${ac.content}"></c:out></p>
							</div>

						</li>
					</c:forEach>
				</ul>
			</section>
		</section>
	</c:if>
	<section class="lh-bj-list p-disc-box mt20">
		<ul>
			<li class="unBr">
				<aside class="noter-pic">
					<c:if test="${empty user.picImg }">
						<img width="50" height="50" src="/static/inxweb/img/avatar-boy.gif" class="picImg">
					</c:if>
					<c:if test="${!empty user.picImg }">
						<img width="50" height="50" src="${user.picImg}" class="picImg">
					</c:if>
				</aside>
				<div class="of">
					<section class="n-reply-wrap">
						<div class="numb-box"><fieldset>
							<textarea id="talkContent" placeholder="输入您要讨论的文字" name="" style="width: 300px"></textarea>
						</fieldset></div>
						<p class="of mt5 tar pl10 pr10">
							<span class="fl "><tt style="display: none;" class="c-red commentContentmeg"></tt></span>
							<a class="lh-reply-btn" title="清屏" onclick="$('.talkhtml').html('');" href="javascript:void(0)">清屏</a>
							<a class="lh-reply-btn" title="回复" href="javascript:sendtalk();">回复</a>
						</p>
					</section>
				</div>
			</li>
		</ul>
	</section>
</div>