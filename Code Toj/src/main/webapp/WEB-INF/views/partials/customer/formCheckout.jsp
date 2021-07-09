<%@ page contentType="text/html;charset=UTF-8"%>
<div class="row">
    <div class="column-contact">
        <label>Nome <span>*</span></label>
        <input type="text" name="nome" value="<c:if test="${not empty userInfSession.nome}">${userInfSession.nome}</c:if>" autocomplete="off" required>
    </div>
    <div class="column-contact padding-right0">
        <label>Cognome  <span>*</span></label>
        <input type="text" name="cognome" value="<c:if test="${not empty userInfSession.cognome}">${userInfSession.cognome}</c:if>" autocomplete="off" required>
    </div>
</div>

<div class="row">
    <div class="column-contact2">
        <label>Indirizzo  <span>*</span></label>
        <input placeholder="Via e numero civico" type="text" name="indirizzo" value="<c:if test="${not empty userInfSession.via}">${userInfSession.via}</c:if>" autocomplete="off" required>
    </div>
    <div class="column-contact2">
        <input autocomplete="off" placeholder="Appartamento, scala, piano etc. (opzionale)" name="appartamento" type="text" value="<c:if test="${not empty userInfSession.appartamento}">${userInfSession.appartamento}</c:if>">
    </div>
    <div class="column-contact2">
        <label>CAP  <span>*</span></label>
        <input autocomplete="off" placeholder="84026" maxlength="5" name="cap" type="text" value="<c:if test="${not empty userInfSession.CAP}">${userInfSession.CAP}</c:if>" required>
    </div>
</div>

<div class="row">
    <div class="column-contact2">
        <label>Città <span>*</span></label>
        <input type="text" autocomplete="off"  name="citta" value="<c:if test="${not empty userInfSession.city}">${userInfSession.city}</c:if>" required>
    </div>
</div>

<div class="row">
    <div class="column-contact2">
        <label>Stato / Paese <span>*</span></label>
        <input type="text" autocomplete="off" name="stato" value="<c:if test="${not empty userInfSession.paese}">${userInfSession.paese}</c:if>" required>
    </div>
</div>

<div class="row">
    <div class="column-contact">
        <label>Telefono<span>*</span></label>
        <input type="tel" placeholder="3569541658" autocomplete="off" name="telefono" maxlength="14" value="<c:if test="${not empty userInfSession.numeroTelefonico}">${userInfSession.numeroTelefonico}</c:if>" required>
    </div>
    <div class="column-contact padding-right0">
        <label>Email <span>*</span></label>
        <input type="email" name="email" autocomplete="off" placeholder="example@gmail.com" value="${userSession.email}" required>
        <c:if test="${not empty msg2}">
            <small class="errMsg">${msg2}</small>
        </c:if>
    </div>
</div>



