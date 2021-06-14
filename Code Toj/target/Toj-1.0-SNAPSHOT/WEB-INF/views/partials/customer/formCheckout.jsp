<%@ page contentType="text/html;charset=UTF-8"%>
<div class="row">
    <div class="column-contact">
        <label>Nome <span>*</span></label>
        <input type="text" name="nome" required>
    </div>
    <div class="column-contact padding-right0">
        <label>Cognome  <span>*</span></label>
        <input type="text" name="cognome" required>
    </div>
</div>

<div class="row">
    <div class="column-contact2">
        <label>Nome Compagnia</label>
        <input type="text" name="nomeCompagnia" required>
    </div>
</div>

<div class="row">
    <div class="column-contact2">
        <label>Indirizzo  <span>*</span></label>
        <input placeholder="Via e numero civico" type="text" name="indirizzo" required>
    </div>
    <div class="column-contact2">
        <input placeholder="Appartamento, scala, piano etc. (opzionale)" name="appartamento" type="text">
    </div>
</div>

<div class="row">
    <div class="column-contact2">
        <label>Città <span>*</span></label>
        <input type="text" name="citta" required>
    </div>
</div>

<div class="row">
    <div class="column-contact2">
        <label>Stato / Paese <span>*</span></label>
        <input type="text" name="stato" required>
    </div>
</div>

<div class="row">
    <div class="column-contact">
        <label>Telefono<span>*</span></label>
        <input type="tel" name="telefono" maxlength="10" required>
    </div>
    <div class="column-contact padding-right0">
        <label>Email <span>*</span></label>
        <input type="email" name="email" placeholder="example@gmail.com" required>
    </div>
</div>



